package com.example.assignmentgallery.ui.fragments.display

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.assignmentgallery.R

class DisplayFragment : Fragment() {

    companion object {
        fun newInstance() = DisplayFragment()
    }

    private var viewModel = DisplayViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.display_fragment, container, false)
        val imageView = view.findViewById<ImageView>(R.id.imageViewFragmentPhotoDisplay)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressFragmentPhotoDisplay)
        var url = ""
        if (arguments != null)
        {
            url = arguments!!.getString("url","")
            viewModel.displayPhoto(url,imageView, progressBar)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DisplayViewModel::class.java)
    }

}