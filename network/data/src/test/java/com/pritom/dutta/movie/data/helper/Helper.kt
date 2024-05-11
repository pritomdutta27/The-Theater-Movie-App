package com.pritom.dutta.movie.data.helper

import java.io.InputStreamReader

/**
 * Created by Pritom Dutta on 11/5/24.
 */
object Helper {
    fun readFileResource(fileName: String): String {
        val inputStream = Helper::class.java.getResourceAsStream(fileName)
        val stringBuilder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            stringBuilder.append(it)
        }
        return stringBuilder.toString()
    }
}