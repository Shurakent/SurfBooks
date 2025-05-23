package com.example.surfbook.domain.model

data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String?,
    val authors: List<String>?,
    val publishedDate: String?,
    val description: String?,
    val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String?
)

data class BooksResponse(
    val items: List<Book>?
)