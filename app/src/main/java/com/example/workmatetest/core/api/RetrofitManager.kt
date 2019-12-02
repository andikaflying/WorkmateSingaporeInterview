package com.example.workmatetest.core.api

import com.example.workmatetest.utilities.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {
    val TAG: String = RetrofitManager::class.java.getName()

    lateinit private var mRetrofit: Retrofit
    private val httpLoggingInterceptor: HttpLoggingInterceptor
    private var okHttpClient: OkHttpClient
    var customHttpInterceptors: CustomHttpInterceptors? = null

    //To provide Retrofit service
    val service: APIList get() = mRetrofit.create(APIList::class.java)

    //Initialize needed properties like headers, base url, etc...
    init {
        httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        //Build ok http client
        okHttpClient = initOkHttp(CustomHttpInterceptors())

        mRetrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        initRetrofit()
    }

    public fun setHeaders(token: String) {
        val customHttpInterceptors = CustomHttpInterceptors.Builder()
            .addHeaderParams("Authorization", "Token " + token)
            .addHeaderParams("Content-Type", "application/json")
            .build()
        okHttpClient = initOkHttp(customHttpInterceptors)
        initRetrofit()
    }

    public fun addCustomHeaders(map: Map<String, String>) {
        var customHttpBuilder: CustomHttpInterceptors.Builder = CustomHttpInterceptors.Builder()

        map.forEach {
            customHttpBuilder.addHeaderParams(it.key, it.value)
        }

        okHttpClient = initOkHttp(customHttpBuilder.build())
        initRetrofit()
    }

    public fun initRetrofit() {
        mRetrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    public fun initOkHttp(customHttpInterceptors: CustomHttpInterceptors?) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(customHttpInterceptors)
            .build()
    }
}