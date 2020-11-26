package com.example.anew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

var flag=0

class login : AppCompatActivity() {
    fun click(v: View?) {//问号表示当前对象可以为空,!!表示当前对象不为空的情况下执行
        when (v?.id) {
            R.id.button4 -> {
                val intent =Intent("com.example.anew.FORCE_OFFLINE")
                sendBroadcast(intent)
//                Toast.makeText(this, "已注销", Toast.LENGTH_SHORT).show()

                val intent2=Intent(this , MainActivity::class.java)//将Button_1和SecondActivity.java对应的界面关联//var是一个可变变量，这是一个可以通过重新分配来更改为另一个值的变量。
                  startActivity(intent2)//启动所关联的活动项,通过startActivity()来执行这个intent
                finish()//返回
            }
            R.id.changing -> {
                flag = if(flag ==0){
                    changing.setImageResource(R.drawable.light_on)
                    1
                } else {
                    changing.setImageResource(R.drawable.light)
                    0
                }
//                Toast.makeText(this, "已改变", Toast.LENGTH_SHORT).show()
//                Log.d("灯泡: ", "改变了")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        Log.d("Main_activity", "Task id is $taskId")
    }
}