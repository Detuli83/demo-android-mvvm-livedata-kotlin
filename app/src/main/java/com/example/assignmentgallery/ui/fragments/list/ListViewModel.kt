package com.example.assignmentgallery.ui.fragments.list

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.assignmentgallery.utils.MyApplication
import com.example.assignmentgallery.utils.model.ImageListModel
import com.example.assignmentgallery.utils.model.ImageModel

class ListViewModel() : ViewModel() {
    var isLoading = MutableLiveData<Boolean>(false)
    var isEndofList = MutableLiveData<Boolean>(false)
    var photoLiveDataArray = MutableLiveData<MutableList<ImageModel>>()

    fun loadNewImages(context: Context, page: Int?) {

        isLoading.value = true
        var photoArray = mutableListOf<ImageModel>()

        val queue = Volley.newRequestQueue(context)

        val url = MyApplication.BASE_URL + "method=flickr.galleries.getPhotos&api_key=${MyApplication.API_KEY}&gallery_id=66911286-72157647277042064&format=json&nojsoncallback=1&per_page=3&page=$page"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {response ->
            val jsonObj = response.getJSONObject("photos")
            var totalPages = jsonObj.getInt("pages")
            var currentPage = jsonObj.getInt("page")
            if (totalPages == currentPage) isEndofList.value = true
            val jsonArray = jsonObj.getJSONArray("photo")
            for (i in 0 until jsonArray.length())
            {
                val arrayObj = jsonArray.getJSONObject(i)
                val photoModel = ImageModel(
                    arrayObj.getString("id"),
                    arrayObj.getString("secret"),
                    arrayObj.getString("server"),
                    arrayObj.getInt("farm")
                )
                photoArray.add(photoModel)
            }
            photoLiveDataArray.postValue(photoArray)

            isLoading.value = false

        },Response.ErrorListener {
            isLoading.value = false
            Toast.makeText(context,"Error is $it",Toast.LENGTH_SHORT).show()
        })

        queue.add(jsonObjectRequest)
    }

}