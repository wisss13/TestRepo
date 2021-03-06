package ca.wihraiech.mytestapp.api

import com.google.gson.GsonBuilder
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * [Retrofit] instance builder.
 */
class RetrofitBuilder {

    companion object {
//        const val BASE_URL = "https://official-joke-api.appspot.com"
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    /**
     * Build a [Retrofit] instance.
l     */
    fun build(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor)

        // Moshi conf
        val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}
