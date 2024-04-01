package com.basma.mealsapp.di_modules

import com.basma.homepage.data.remote.HomePageRemoteServices
import com.basma.meal_details.data.remote.MealDetailsRemoteServices
import com.basma.meals_list.data.remote.MealsListRemoteServices
import com.basma.mealsapp.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /**
     * Provides [HttpLoggingInterceptor] instance
     */
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    /**
     * Provides [OkHttpClient] instance
     */
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .retryOnConnectionFailure(true)
            .build()
    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    @Named("homepageRetrofit")
    fun providesRetrofitForHomePage(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOMEPAGE_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("mealsRetrofit")
    fun providesRetrofitForMeals(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MEALS_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideHomePageService(@Named("homepageRetrofit") retrofit: Retrofit): HomePageRemoteServices {
        return retrofit.create(HomePageRemoteServices::class.java)
    }

    @Singleton
    @Provides
    fun provideMealsListService(@Named("mealsRetrofit") retrofit: Retrofit): MealsListRemoteServices {
        val modifiedClient = (retrofit.callFactory() as OkHttpClient).newBuilder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val modifiedRequest = originalRequest.newBuilder().build()
                val response = chain.proceed(modifiedRequest)
                println("Request URL: ${response.request.url}")
                response
            }
            .build()

        val modifiedRetrofit = retrofit.newBuilder()
            .client(modifiedClient)
            .build()

        return modifiedRetrofit.create(MealsListRemoteServices::class.java)
    }

    @Singleton
    @Provides
    fun provideMealDetailsService(@Named("mealsRetrofit") retrofit: Retrofit): MealDetailsRemoteServices {
        val modifiedClient = (retrofit.callFactory() as OkHttpClient).newBuilder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val modifiedRequest = originalRequest.newBuilder().build()
                val response = chain.proceed(modifiedRequest)
                println("Request URL: ${response.request.url}")
                response
            }
            .build()

        val modifiedRetrofit = retrofit.newBuilder()
            .client(modifiedClient)
            .build()

        return modifiedRetrofit.create(MealDetailsRemoteServices::class.java)
    }
}