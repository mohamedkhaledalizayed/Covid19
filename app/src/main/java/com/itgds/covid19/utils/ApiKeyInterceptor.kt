package com.itgds.covid19.utils

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
            .addQueryParameter("x-rapidapi-key", "b6d48ab486msh3d7362845a8d9d9p1f968ejsnb3ddfc81d0a7")
            .build()

        val requestBuilder = original.newBuilder().url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}