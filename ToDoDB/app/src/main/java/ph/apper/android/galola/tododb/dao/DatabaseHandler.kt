package ph.apper.android.galola.tododb.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ph.apper.android.galola.tododb.model.Todo


class DatabaseHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 7
        private val DATABASE_NAME = "ToDoDatabase"
        private val TABLE_TODO = "ToDoTable"
        private val KEY_ID = "id"
        private val KEY_NAME = "toDo"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_TODO_TABLE = ("CREATE TABLE " + TABLE_TODO + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT" + ");")
        db?.execSQL(CREATE_TODO_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO)
        onCreate(db)
    }

    //method to insert data
    fun addToDo(td: Todo):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, td.id)
        contentValues.put(KEY_NAME, td.title) // EmpModelClass Name
        // Inserting Row
        val success = db.insert(TABLE_TODO, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to delete data
    fun deleteToDo(td: Todo):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, td.id) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_TODO,"id="+td.id,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}