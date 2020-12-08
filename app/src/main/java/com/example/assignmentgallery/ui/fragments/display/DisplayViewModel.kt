package com.example.assignmentgallery.ui.fragments.display

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import com.example.assignmentgallery.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DisplayViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    fun displayPhoto(url : String,imageView: ImageView,progressBar: ProgressBar){
        progressBar.visibility = View.VISIBLE
        Picasso.get().load(url).error(R.drawable.ic_launcher_foreground).into(
            imageView,
            object :
                Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                }
            })
    }
}