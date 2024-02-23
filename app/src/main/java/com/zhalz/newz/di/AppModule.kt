package com.zhalz.newz.di

import com.crocodic.core.helper.NetworkHelper
import com.zhalz.newz.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return NetworkHelper.provideApiService(
            baseUrl = "https://newsdata.io/api/1/",
            okHttpClient = NetworkHelper.provideOkHttpClient(),
            converterFactory = listOf(GsonConverterFactory.create())
        )
    }
}