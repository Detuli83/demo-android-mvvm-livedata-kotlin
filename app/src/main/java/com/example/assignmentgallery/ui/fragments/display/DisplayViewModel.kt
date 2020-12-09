package com.example.assignmentgallery.ui.fragments.display

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DisplayViewModel : ViewModel() {
    var isLoading = MutableLiveData<Boolean>(false)

    fun loadPhoto(){
        isLoading.value = true
    }

    fun onSuccess() {
        isLoading.value = false
    }

    fun onError() {
        isLoading.value = false
    }
}