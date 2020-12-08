package com.example.assignmentgallery.ui.fragments.list

import android.content.Context
import android.media.Image
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.assignmentgallery.MainActivity
import com.example.assignmentgallery.R
import com.example.assignmentgallery.utils.model.ImageModel

class ListFragment : Fragment() {

    lateinit var viewModel : ListViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var loadMoreButton: Button
    var photoArray = mutableListOf<ImageModel>()
    lateinit var recyclerAdapter : ListAdapter
    lateinit var photoArrayChangeObserver: Observer<MutableList<ImageModel>>
    var pageCount = MutableLiveData<Int>(1)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerListFragment)
        progressBar = view.findViewById<ProgressBar>(R.id.progressListFragment)
        loadMoreButton = view.findViewById<Button>(R.id.loadMoreButton)

        progressBar.visibility = View.VISIBLE

        loadMoreButton.setOnClickListener {
            pageCount.postValue(pageCount.value!! + 1)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        recyclerAdapter = ListAdapter(activity as Context, photoArray)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        val pageChangeObserver = Observer<Int> { page ->
            viewModel.loadNewImages(context as MainActivity, page)
        }

        pageCount.observe(context as MainActivity, pageChangeObserver)

        photoArrayChangeObserver = Observer<MutableList<ImageModel>> { arr ->
            progressBar.visibility = View.GONE
            photoArray.addAll(arr)
            recyclerAdapter.notifyDataSetChanged()
        }
        viewModel.photoLiveDataArray.observe(context as MainActivity, photoArrayChangeObserver)
    }


}