package com.example.fitnesssensor
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class PedoSensor1 : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var steps: Int = 0
    private lateinit var textViewSteps: TextView

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
                if (isGranted) {
                    // Разрешение предоставлено, можно продолжить работу с датчиком
                    startStepCounter()
                } else {
                    // Разрешение отклонено, выведите сообщение пользователю или обработайте это как нужно
                    textViewSteps.text = "Разрешение на доступ к датчикам отклонено."
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedo_sensor) // Убедитесь, что layout activity_pedo_sensor.xml существует

        textViewSteps = findViewById(R.id.tv_stepsTaken) // Замените R.id.tv_stepsTaken на id вашего TextView

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Запрос разрешений только для API 23 и выше
            requestPermissions()
        } else {
            // Для API ниже 23 разрешения не требуются
            startStepCounter()
        }
    }

    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.ACTIVITY_RECOGNITION,
            Manifest.permission.BODY_SENSORS // Если необходимо, добавьте другие разрешения
        )
        val neededPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (neededPermissions.isNotEmpty()) {
            requestPermissionLauncher.launch(neededPermissions.toTypedArray())
        } else {
            startStepCounter()
        }
    }

    private fun startStepCounter() {
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            textViewSteps.text = "Датчик шагов недоступен."
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            steps = event.values[0].toInt()
            textViewSteps.text = "Шаги: $steps"
            Log.d("StepCounter", "Шаги: $steps")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Не используется в данном примере
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
}