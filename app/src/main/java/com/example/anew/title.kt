package com.example.anew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast

class title : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(
            R.menu.main_menu,
            menu
        );//第一个参数用于指定我们通过哪一个资源文件来创建菜单,第二个参数用于指定我们的菜单项将添加到哪一个Menu对象当中.
        return true;
    }

    fun click(v: View?) {//问号表示当前对象可以为空,!!表示当前对象不为空的情况下执行
        when (v?.id) {
            R.id.tv_back -> {
                finish();
            }
        }
    }
}