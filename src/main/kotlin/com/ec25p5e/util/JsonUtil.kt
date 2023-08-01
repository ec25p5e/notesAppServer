package com.ec25p5e.util

import com.google.gson.Gson

fun <T> Gson.fromJsonOrNull(json: String, clazz: Class<T>): T? {
    return try {
        fromJson(json, clazz)
    } catch(e: Exception) {
        null
    }
}