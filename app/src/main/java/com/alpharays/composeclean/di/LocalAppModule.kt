package com.alpharays.composeclean.di

import com.alpharays.composeclean.data.remote.ApiServices
import com.alpharays.composeclean.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalAppModule {

    @Singleton
    @Provides
    fun providesApiServices(
        retrofit: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): ApiServices {
        return retrofit.client(okHttpClient).build().create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl("http://demo3272470.mockable.io")
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptor: AuthInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder =
            OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(loggingInterceptor)
        return builder.build()
    }
}