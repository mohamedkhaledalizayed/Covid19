package com.itgds.covid19.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.itgds.covid19.repository.RemoteRepository
import com.itgds.covid19.services.ServiceApi
import com.itgds.covid19.utils.ApiKeyInterceptor
import com.itgds.covid19.utils.BaseSchedulerProvider
import com.itgds.covid19.utils.SchedulerProvider
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient, gson: Gson, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://coronavirus-monitor.p.rapidapi.com/coronavirus/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getGson(): Gson {
        return GsonBuilder().create()
    }


    @Singleton
    @Provides
    fun providesMoviesRepository(service: ServiceApi): RemoteRepository = RemoteRepository(service)

//    @Provides
//    @Singleton
//    fun getInterceptor(): HttpLoggingInterceptor {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        return interceptor
//    }

    @Provides
    @Singleton
    fun getCache(file: File): Cache {
        return Cache(file, (10 * 1000 * 1000).toLong())  // 10 MiB cache
    }

    @Provides
    @Singleton
    fun getFile(application: Application): File {
        val file = File(application.filesDir, "cache_dir")
        if (!file.exists())
            file.mkdirs()
        return file
    }

    @Provides
    @Singleton
    internal fun provideApiKeyInterceptor() =
        ApiKeyInterceptor()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
                HttpLoggingInterceptor.Level.BODY

        return logging
    }

    @Singleton
    @Provides
    fun getAuthorizedOkHttpClient(
        cache: Cache,
        interceptor: ApiKeyInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS) // write timeout
            .readTimeout(30, TimeUnit.SECONDS) // read timeout
            .addInterceptor(object: Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request()
                val response = chain.proceed(request)
                if(request.url().url().toString().contains("authenticate",true) ||
                    request.url().url().toString().contains("MinSupportedVer",true)){
                    return response
                }
                var newRequest = request.newBuilder()
                    .header("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                    .header("x-rapidapi-key", "b6d48ab486msh3d7362845a8d9d9p1f968ejsnb3ddfc81d0a7")
                    .build()
                var newResponse = chain.proceed(newRequest)
                return newResponse
            }

        })
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthorizedServiceApi(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }


    @Singleton
    @Provides
    fun providesTaskBaseScheduler(): BaseSchedulerProvider =
        SchedulerProvider()

//    @Singleton
//    @Provides
//    fun providesMoviesRepository(service: ServiceApi, mapper: MovieMapper, personMapper: PersonMapper, tvMapper: TVMapper) =
//        RemoteRepository(service, mapper, personMapper, tvMapper)
//
//    @Singleton
//    @Provides
//    fun providesMovieUseCase(repository: RemoteRepository) = MovieUseCase(repository)

}
