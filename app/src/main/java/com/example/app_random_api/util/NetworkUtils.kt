package com.example.rk_application.util


import android.net.Uri
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.UnknownHostException
import java.util.*

class NetworkUtils {
    companion object{
        private const val API_BASE_URL:String = "https://www.boredapi.com"
        private const val TYPE:String = "/api"
        private const val NAME:String = "/activity/"
        public fun generateURL(): URL{
            var builtUri: Uri? = Uri.parse(API_BASE_URL + TYPE + NAME)
                .buildUpon()
                .build()

            var url: URL? = null
            url = URL(builtUri.toString())

            println(url)

            return url
        }

        @Throws(IOException::class)
        fun getResponseFromURL(url: URL): String? {
            val urlConnection = url.openConnection() as HttpURLConnection
            return try {
                val input : InputStream = urlConnection.inputStream
                val scanner = Scanner(input)
                scanner.useDelimiter("\\A")
                val hasInput = scanner.hasNext()
                if (hasInput) {
                    scanner.next()
                } else {
                    null
                }
            } catch (e: UnknownHostException){
                null
            } finally {
                urlConnection.disconnect()
            }
        }
    }

}