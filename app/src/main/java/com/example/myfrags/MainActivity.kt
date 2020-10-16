package com.example.myfrags

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment1 = Fragment1()
        val fragment2 = Fragment2()
        val fragment3 = Fragment3()
        val fragment4 = Fragment4()

        supportFragmentManager.inTransaction {add(R.id.frame1, fragment1)}
        supportFragmentManager.inTransaction {add(R.id.frame2, fragment2)}
        supportFragmentManager.inTransaction {add(R.id.frame3, fragment3)}
        supportFragmentManager.inTransaction {add(R.id.frame4, fragment4)}
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}