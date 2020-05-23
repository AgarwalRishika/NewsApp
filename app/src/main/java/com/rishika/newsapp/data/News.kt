package com.rishika.newsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

/**
 * Created by Rishika on 23/05/20.
 */
@Entity
data class News(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: Date?,
    val content: String?
) : Serializable