package com.example.anew

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class register : AppCompatActivity() {
    private val TAG="regActivity"
    fun click(v: View?){//问号表示当前对象可以为空,!!表示当前对象不为空的情况下执行
        when(v?.id){
            R.id.OK -> {
                val intent = Intent()
                intent.putExtra("data_return", "Hello MainActivity")
                setResult(RESULT_OK, intent)
                val dbHelper = MyDatabaseHelper(this, "userdata.db", 1)
                val inputTextzh = username.text.toString()
                val inputTextpw = password.text.toString()
                val inputTextpw2 = password2.text.toString()
                val inputTextem = email.text.toString()
                //注册检查

                if (regcheck(username.text, "账号为空") || regcheck(password.text, "密码为空") || regcheck(password2.text, "重复密码为空") || regcheck(email.text, "邮箱为空"))
                {}
                else {
                    //查重
                    val db2 = dbHelper.writableDatabase
                    val cursor = db2.query("DATA", null, "username=?", arrayOf(inputTextzh), 2.toString(), null, null)
                    if(cursor.moveToFirst()) {
                        val regedusername = cursor.getString(cursor.getColumnIndex("username"))
                        if(inputTextzh != regedusername){
                            //查重
                            if (inputTextpw == inputTextpw2) {
                                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
                                //写入
                                val db = dbHelper.writableDatabase
                                val values1 = ContentValues().apply {
                                    put("username", inputTextzh)
                                    put("password", inputTextpw)
                                    put("email", inputTextem)
                                    put("remember_password", 0)
                                }
                                db.insert("DATA", null, values1)
                                //写入
                                finish()
                            } else {
                                Toast.makeText(this, "重复密码不匹配", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this, "该用户已注册", Toast.LENGTH_SHORT).show()
                        }
                        //查重
                    }
                    else{
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
                        //写入
                        val db = dbHelper.writableDatabase
                        val values1 = ContentValues().apply {
                            put("username", inputTextzh)
                            put("password", inputTextpw)
                            put("email", inputTextem)
                            put("remember_password", false)
                        }
                        db.insert("DATA", null, values1)
                        //写入
                        finish()
                    }
                    cursor.close()
                }
            }
            R.id.back->{
                finish()
            }
        }
    }
    private fun regcheck(u: Editable, warn:String) :Boolean{
            if(u.isEmpty()){
                Toast.makeText(this,warn,Toast.LENGTH_SHORT).show()
                return true
            }
        return false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Log.d("Main_activity", "Task id is $taskId")
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);//第一个参数用于指定我们通过哪一个资源文件来创建菜单,第二个参数用于指定我们的菜单项将添加到哪一个Menu对象当中.
        return true;
    }

    override fun onBackPressed() {
        val intent=Intent()
        intent.putExtra("data_return","Hello MainActivity")
        setResult(RESULT_OK,intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "注册进程已销毁")
    }
    //    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item?.itemId){
//            R.id.
//
//        }
//    }
}