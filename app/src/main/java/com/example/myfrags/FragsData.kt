package com.example.myfrags

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class FragsData : ViewModel() {
    val counter = MutableLiveData(0)

    fun increment(){
        counter.value?.let{a -> counter.value = a + 1}
    }

    fun decrement(){
        counter.value?.let{a -> counter.value = a - 1}
    }

}
