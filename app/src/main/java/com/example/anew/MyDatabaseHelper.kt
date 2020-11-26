package com.example.anew

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context:Context, name :String,version:Int):SQLiteOpenHelper(context,name,null,version) {
    private  val createBook ="create table DATA ("+
            "id integer primary key autoincrement,"+
            "username text,"+
            "password text,"+
            "email text,"+
            "remember_password blob)"
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBook)
        Toast.makeText(context,"注册/写入成功",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}