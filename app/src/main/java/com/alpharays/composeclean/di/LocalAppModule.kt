package com.alpharays.composeclean.di

import android.content.Context
import androidx.room.Room
import com.alpharays.composeclean.data.local.StudentDataBase
import com.alpharays.composeclean.data.remote.ApiServices
import com.alpharays.composeclean.utils.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): StudentDataBase {
        return Room.databaseBuilder(
            context,
            StudentDataBase::class.java, "my-swipe-product-room-db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideStudentDao(appDatabase: StudentDataBase) = appDatabase.studentDao()

}