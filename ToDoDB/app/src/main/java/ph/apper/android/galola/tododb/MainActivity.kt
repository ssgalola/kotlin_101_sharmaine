package ph.apper.android.galola.tododb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ph.apper.android.galola.tododb.dao.DatabaseHandler
import kotlinx.android.synthetic.main.activity_main.*
import ph.apper.android.galola.tododb.adapter.TodoAdapter
import ph.apper.android.galola.tododb.model.Todo

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(arrayListOf())

        rv_todo.adapter = todoAdapter
        rv_todo.layoutManager = LinearLayoutManager(this)

        button_add_todo.setOnClickListener {
            val todoTitle = et_todo.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(
                    id = todoAdapter.toDoId,
                    title = todoTitle.toString()
                )
                todoAdapter.addTodo(todo)
                saveToDoDB(todo)
                et_todo.text.clear()
            } else {
                Toast.makeText(
                    applicationContext,
                    "To-do cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        button_delete_done.setOnClickListener {
            todoAdapter.collectDoneToDos()
            for (todo in todoAdapter.doneToDos){
                deleteToDoDB(todo)
            }
            todoAdapter.deleteDoneTodos()
        }
    }

    fun saveToDoDB(todo: Todo) {
        val id = todo.id.toString()
        val title = todo.title.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (id.trim() != "" && title.trim() != "") {
            val status = databaseHandler.addToDo(Todo(Integer.parseInt(id), title))
            if (status > -1) {
                Toast.makeText(applicationContext, "Saved to-do", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //method for deleting records based on id
    fun deleteToDoDB(todo: Todo) {
        val deleteId = todo.id.toString()
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (deleteId.trim() != "") {
            //calling the deleteEmployee method of DatabaseHandler class to delete record
            val status =
                databaseHandler.deleteToDo(Todo(Integer.parseInt(deleteId), title.toString()))
            if (status > -1) {
                Toast.makeText(applicationContext, "Deleted to-do", Toast.LENGTH_SHORT).show()
            }
        }
    }
}