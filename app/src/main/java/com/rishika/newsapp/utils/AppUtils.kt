package com.rishika.newsapp.utils

/**
 * Created by Rishika on 23/05/20.
 */
object AppUtils {

    fun contains(string: String?): Boolean {
        if (string == null || string.trim().isEmpty()) {
            return false
        }
        return true
    }
}