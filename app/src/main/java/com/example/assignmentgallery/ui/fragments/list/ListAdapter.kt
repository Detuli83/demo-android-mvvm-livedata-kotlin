package com.example.assignmentgallery.ui.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentgallery.R
import com.example.assignmentgallery.utils.model.ImageModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ListAdapter(var context: Context,var listPhotos: MutableList<ImageModel>) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    var selectedImageUrl = MutableLiveData<String>()
    class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.imageViewSingleItem)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressSingleItem)
        val relativeLayout = view.findViewById<RelativeLayout>(R.id.relativeLayoutSingleItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.image_single_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.progressBar.visibility = View.VISIBLE
        val image = listPhotos.get(position)
        val url = "https://farm${image.farm}.staticflickr.com/${image.server}/${image.id}_${image.secret}.jpg"
        Picasso.get().load(url).placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground).into(
            holder.imageView,
            object :
                Callback {
                override fun onSuccess() {
                    holder.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                }
            })

        holder.relativeLayout.setOnClickListener {
            selectedImageUrl.postValue(url)
        }
    }

    override fun getItemCount(): Int {
        return listPhotos.size
    }
}