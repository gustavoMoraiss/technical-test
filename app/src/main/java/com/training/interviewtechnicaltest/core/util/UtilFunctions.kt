package com.training.interviewtechnicaltest.core.util

import android.os.Build
import androidx.annotation.RequiresApi
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object UtilFunctions {
    fun logError(tag: String, message: String) {
        Timber.tag(tag).e("Error ->> $message")
    }

    fun logInfo(tag: String, message: String) {
        Timber.tag(tag).e("Info ->> $message")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun String.formatDateAPI(): String {
        try {
            val data = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
            val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            return data.format(dateTimeFormatter)
        } catch (e: DateTimeParseException) {
            return ""
        }
    }
}