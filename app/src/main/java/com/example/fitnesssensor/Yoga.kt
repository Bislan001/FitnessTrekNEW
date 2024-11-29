package com.example.fitnesssensor
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Yoga : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yoga)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val exercises = listOf(
            Exercise("Собака мордой вниз", "Описание: Упражнение для растяжки спины и ног."),
            Exercise("Поза дерева", "Описание: Укрепляет ноги и улучшает баланс."),
            Exercise("Поза лотоса", "Описание: Расслабляющая поза для медитации.")
        )

        exerciseAdapter = ExerciseAdapter(exercises,this)
        recyclerView.adapter = exerciseAdapter
    }
}

data class Exercise(val name: String, val description: String)

class ExerciseAdapter(
    private val exercises: List<Exercise>, private val context: Context
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.exercise_name)
        val descriptionTextView: TextView = itemView.findViewById(R.id.exercise_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.nameTextView.text = exercise.name
        holder.descriptionTextView.text = exercise.description

        // Установка клика на элемент списка
        holder.itemView.setOnClickListener {
            val intent = Intent(context, Sobaka::class.java).apply {
                putExtra("EXTRA_EXERCISE_NAME", exercise.name)
                putExtra("EXTRA_EXERCISE_DESCRIPTION", exercise.description)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = exercises.size
}