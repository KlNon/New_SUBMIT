package com.example.anew

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_findpassword2.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.password2
import kotlinx.android.synthetic.main.activity_register.username

class Find_password_2 : AppCompatActivity() {
    fun click(v: View?) {//问号表示当前对象可以为空,!!表示当前对象不为空的情况下执行
        when (v?.id) {
            R.id.OK -> {
                //查重
                val finded = find2_password.text.toString()
                val dbHelper = MyDatabaseHelper(this, "userdata.db", 1)
                val db2 = dbHelper.writableDatabase
                val cursor = db2.query("DATA", null, "password=?", arrayOf(finded), 3.toString(), null, null
                )
                if (cursor.moveToFirst()) {
                    val regedpassword = cursor.getString(cursor.getColumnIndex("password"))
                    if (finded == regedpassword) {
                        if (password2.text.toString().equals(password3.text.toString())){
                            Toast.makeText(this, "更改成功", Toast.LENGTH_SHORT).show()
                            //写入
                            val dbHelper =MyDatabaseHelper(this,"userdata.db",1)
                            val db=dbHelper.writableDatabase
                            val values =ContentValues()
                            values.put("password",password2.text.toString())
                            db.update("DATA",values,"username=?", arrayOf(intent.getStringExtra("extra_data")))
                            //写入
                            finish()
                        }
                        else {
                            Toast.makeText(this, "重复密码不匹配", Toast .LENGTH_SHORT).show()
                        }
                        finish()//返回
                    } else {
                        Toast.makeText(this, "密码不正确", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "密码不正确", Toast.LENGTH_SHORT).show()
                }
                //查重
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_findpassword2)
        val extraData=intent.getStringExtra("extra_data")
        username.setText(extraData)
        username.isEnabled = false
    }
}