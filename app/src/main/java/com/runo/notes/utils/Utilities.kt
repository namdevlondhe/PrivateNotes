package com.runo.notes.utils

import java.text.SimpleDateFormat
import java.util.*

class Utilities {
    companion object {
        fun getCurrentDateAndTime(): String {
            val today = Date()
            val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a")
            val dateToStr: String = format.format(today)
            return dateToStr
        }
    }
}