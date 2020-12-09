package com.example.assignmentgallery.ui.fragments.display

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.assignmentgallery.MainActivity
import com.example.assignmentgallery.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class DisplayFragment : Fragment() {

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

            viewModel.isLoading.observe(activity as MainActivity, {
                if(it) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
            })

            viewModel.loadPhoto()
            Picasso.get().load(url).error(R.drawable.ic_launcher_foreground).into(
                    imageView,
                    object :
                            Callback {
                        override fun onSuccess() {
                            viewModel.onSuccess()
                        }

                        override fun onError(e: Exception?) {
                            viewModel.onError()
                        }
                    })

        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DisplayViewModel::class.java)
    }

}