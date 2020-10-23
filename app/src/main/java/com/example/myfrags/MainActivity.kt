package com.example.myfrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : FragmentActivity(), Fragment1.OnButtonClickListener {

    private var frames: IntArray = intArrayOf()
    private var hidden = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.myfrags.R.layout.activity_main)
        if (savedInstanceState == null) {
            frames = intArrayOf(
                com.example.myfrags.R.id.frame1,
                com.example.myfrags.R.id.frame2,
                com.example.myfrags.R.id.frame3,
                com.example.myfrags.R.id.frame4
            )
            hidden = false
            val fragments: Array<Fragment> =
                arrayOf(Fragment1(), Fragment2(), Fragment3(), Fragment4())
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            for (i in 0..3) {
                transaction.add(frames[i], fragments[i])
            }
            transaction.addToBackStack(null)
            transaction.commit()
        } else {
            frames = savedInstanceState.getIntArray("FRAMES")!!
            hidden = savedInstanceState.getBoolean("HIDEN")
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    override fun onButtonClickShuffle() {
        val list: List<Int> = ArrayList<Int>(
            Arrays.asList(
                frames[0],
                frames[1], frames[2], frames[3]
            )
        )
        Collections.shuffle(list)
        for (i in 0..3) frames[i] = list[i]
        newFragments()
    }

    override fun onButtonClickClockwise() {
        val t = frames[0]
        frames[0] = frames[1]
        frames[1] = frames[2]
        frames[2] = frames[3]
        frames[3] = t
        newFragments()
    }

    override fun onButtonClickHide() {
        if (hidden) return
        val fragmentManager = supportFragmentManager
        for (f in fragmentManager.fragments) {
            if (f is Fragment1) continue
            val transaction = fragmentManager.beginTransaction()
            transaction.hide(f!!)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        hidden = true
    }

    override fun onButtonClickRestore() {
        if (!hidden) return
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        for (f in fragmentManager.fragments) {
            if (f is Fragment1) continue
            transaction.show(f!!)
        }
        transaction.addToBackStack(null)
        transaction.commit()
        hidden = false
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is Fragment1) {
            fragment.setOnButtonClickListener(this)
        }
    }

    private fun newFragments() {
        val newFragments = arrayOf(Fragment1(), Fragment2(), Fragment3(), Fragment4())
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        for (i in 0..3) {
            transaction.replace(frames[i], newFragments[i])
            if (hidden && newFragments[i] !is Fragment1) transaction.hide(newFragments[i])
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

}