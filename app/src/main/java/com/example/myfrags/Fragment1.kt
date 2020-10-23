package com.example.myfrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
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

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}