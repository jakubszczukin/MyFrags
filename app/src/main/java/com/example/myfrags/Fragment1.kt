package com.example.myfrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class Fragment1 : Fragment() {

    interface OnButtonClickListener {
        fun onButtonClickShuffle()
        fun onButtonClickClockwise()
        fun onButtonClickHide()
        fun onButtonClickRestore()
    }

    private var callback: OnButtonClickListener? = null

    fun setOnButtonClickListener(callback: OnButtonClickListener?) {
        this.callback = callback
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v: View = inflater.inflate(com.example.myfrags.R.layout.fragment_1, container, false)

        val buttonShuffle: Button = v.findViewById(com.example.myfrags.R.id.button_shuffle) as Button
        val buttonClockwise: Button = v.findViewById(com.example.myfrags.R.id.button_clockwise) as Button
        val buttonHide: Button = v.findViewById(com.example.myfrags.R.id.button_hide) as Button
        val buttonRestore: Button = v.findViewById(com.example.myfrags.R.id.button_restore) as Button

        buttonShuffle.setOnClickListener { callback?.onButtonClickShuffle() }

        buttonClockwise.setOnClickListener { callback?.onButtonClickClockwise() }

        buttonHide.setOnClickListener { callback?.onButtonClickHide() }

        buttonRestore.setOnClickListener { callback?.onButtonClickRestore() }

        // Inflate the layout for this fragment
        return v
    }

}