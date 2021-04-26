package ph.apper.android.galola.tododb.adapter

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*
import ph.apper.android.galola.tododb.R
import ph.apper.android.galola.tododb.model.Todo

class TodoAdapter(
    private val todos: ArrayList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    var toDoId = 0

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
        toDoId += 1
    }

    val doneToDos = arrayListOf<Todo>()

    fun collectDoneToDos() {
        for (todo in todos){
            if (todo.isChecked){
                doneToDos.add(todo)
            }
        }
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }

        doneToDos.removeAll() { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tv_todo: TextView, isChecked: Boolean) {
        if(isChecked) {
            tv_todo.paintFlags = tv_todo.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tv_todo.paintFlags = tv_todo.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            tv_todo.text = curTodo.title
            cb_done.isChecked = curTodo.isChecked
            toggleStrikeThrough(tv_todo, curTodo.isChecked)
            cb_done.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tv_todo, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}


















