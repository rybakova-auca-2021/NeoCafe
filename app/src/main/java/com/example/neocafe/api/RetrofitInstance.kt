package com.example.neocafe.api

import com.example.neocafe.util.Constants.Companion.BASE_URL
import com.example.neocafe.util.Utils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(AuthorizationInterceptor())
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(ApiInterface::class.java)
        }
    }
}

private class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val newRequest = if (requiresAuthorization(request)) {
            val token = Utils.access_token
            val authHeader = "Bearer $token"
            request.newBuilder()
                .header("Authorization", authHeader)
                .build()
        } else {
            request
        }
        return chain.proceed(newRequest)
    }

    private fun requiresAuthorization(request: okhttp3.Request): Boolean {
        val path = request.url.encodedPath
        return path.endsWith("birth-date/")
    }
}
