package com.example.anew

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_findpassword.*
import kotlinx.android.synthetic.main.activity_register.*

class Find_password : AppCompatActivity() {
    fun click(v: View?) {//问号表示当前对象可以为空,!!表示当前对象不为空的情况下执行
        when (v?.id) {
            R.id.find_ok -> {
                val finded = find_username.text.toString()
                if (finded.length > 4) {
                    //查重
                    val dbHelper = MyDatabaseHelper(this, "userdata.db", 1)
                    val db2 = dbHelper.writableDatabase
                    val cursor = db2.query(
                        "DATA",
                        null,
                        "username=?",
                        arrayOf(finded),
                        2.toString(),
                        null,
                        null
                    )
                    if (cursor.moveToFirst()) {
                        val regedusername = cursor.getString(cursor.getColumnIndex("username"))
                        if (finded == regedusername) {
                            val intent = Intent(
                                this,
                                Find_password_2::class.java
                            )//将Button_1和SecondActivity.java对应的界面关联//var是一个可变变量，这是一个可以通过重新分配来更改为另一个值的变量。
                            intent.putExtra("extra_data", find_username.text.toString())
                            startActivity(intent)//启动所关联的活动项,通过startActivity()来执行这个intent
                            finish()//返回
                        } else {
                            Toast.makeText(this, "该用户不存在", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "该用户不存在", Toast.LENGTH_SHORT).show()
                    }
                    //查重
                }
                else{
                    Toast.makeText(this, "用户名不合法", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.back->{
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpassword)
    }
}