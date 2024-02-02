package com.example.dummyproject

import android.util.Log
import testrail.APIClient

fun main() {
    val apiClient = APIClient("https://enhanceit.testrail.com/")
    apiClient.user = "1992bharatkumar@gmail.com"
    apiClient.password = "Bharat@1992"
    val c = apiClient.sendGet("get_case/1")
    Log.d("test", ""+c)
}