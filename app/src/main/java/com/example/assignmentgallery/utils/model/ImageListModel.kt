package com.example.assignmentgallery.utils.model

data class ImageListModel(

    var page : Int,
    var pages : Int,
    var perpage : Int,
    var total : Int,
    var photo : ArrayList<ImageModel>,
)
