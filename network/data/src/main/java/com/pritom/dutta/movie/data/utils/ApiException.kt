package com.pritom.dutta.movie.data.utils

import okhttp3.ResponseBody

/**
 * Created by Pritom Dutta on 11/5/24.
 */
class RequestException(
    override var message: String = "",
    var statusCode: Int = 0
) : Exception(message)

class ApiException(
    val statusCode: Int,
    val errorBody: ResponseBody?,
    override val message: String
) : Exception(message)