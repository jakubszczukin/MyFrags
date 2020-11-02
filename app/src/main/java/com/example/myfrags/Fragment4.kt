package com.example.myfrags

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class Fragment4 : Fragment() {

    //1.
    private var fragsData: FragsData? = null
    private var numberObserver: Observer<Int>? = null
    var keyDel = false

    //2.
    private var edit: EditText? = null
    private var textWatcher: TextWatcher? = null
    private var turnOffWatcher = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(com.example.myfrags.R.layout.fragment_4, container, false)

        //1.
        edit = view.findViewById(com.example.myfrags.R.id.editTextNumber)
        edit!!.setSelection(edit!!.text.length)

        edit!!.setOnKeyListener { v, keyCode, event ->
            keyDel = keyCode == KeyEvent.KEYCODE_DEL
            false
        }

        //2.
        fragsData = ViewModelProvider(requireActivity()).get(FragsData::class.java)

        //3.
        numberObserver = Observer { newInteger ->
            turnOffWatcher = true
            edit!!.setText(newInteger.toString())
        }

        //4.
        fragsData!!.counter.observe(viewLifecycleOwner, numberObserver!!)

        //5.
        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (!turnOffWatcher) {
                    val i: Int? = try {
                        s.toString().toInt()
                    } catch (e: NumberFormatException) {
                        fragsData!!.counter.value
                    }
                    if(fragsData!!.counter.value in 1..9 && keyDel)
                        fragsData!!.counter.value = 0
                    else
                        fragsData!!.counter.value = i
                } else {
                    turnOffWatcher = !turnOffWatcher
                }
                edit!!.setSelection(edit!!.text.length)
            }
        }

        //6.
        edit!!.addTextChangedListener(textWatcher)
        return view
    }
}