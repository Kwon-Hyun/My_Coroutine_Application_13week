package com.example.mycoroutineapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class PostsRepository {
    private val topURLStr = "https://jsonplaceholder.typicode.com/"

    suspend fun readPosts() : JSONArray? {
        val reqUrl = URL(topURLStr + "posts")
        return withContext(Dispatchers.IO) {
            (reqUrl.openConnection() as HttpURLConnection)?.run {
                requestMethod = "GET"
                connect()
                val reader = inputStream.bufferedReader()
                val strBuilder = StringBuilder()
                while (true) {
                    strBuilder.append(reader.readLine() ?: break)
                }
                JSONArray(strBuilder.toString())
            }
        }
    }
}