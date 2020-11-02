package com.example.myfrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class Fragment2 : Fragment() {

    private var fragsData: FragsData? = null
    private var numberObserver: Observer<Int>? = null
    private var text: TextView? = null
    private var button: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(com.example.myfrags.R.layout.fragment_2, container, false)

        text = view.findViewById<View>(com.example.myfrags.R.id.current) as TextView
        button = view.findViewById<View>(com.example.myfrags.R.id.button_plus) as Button

        fragsData = ViewModelProvider(requireActivity()).get(FragsData::class.java)

        numberObserver = Observer { newInteger -> text!!.text = newInteger.toString() }

        fragsData!!.counter.observe(viewLifecycleOwner, numberObserver!!)

        button!!.setOnClickListener(View.OnClickListener {
            fragsData!!.increment()
        })
        return view
    }
}