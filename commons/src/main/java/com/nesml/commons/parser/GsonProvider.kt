package com.nesml.commons.parser

import com.google.gson.Gson

interface GsonProvider {
    fun getGson(): Gson
}