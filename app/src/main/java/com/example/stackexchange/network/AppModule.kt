package com.example.stackexchange.network

import android.app.Application
import androidx.room.Room
import com.example.stackexchange.model.StackDatabase
import com.example.stackexchange.utils.RoomConvertors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(StackApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCarListAPI(retrofit: Retrofit): StackApi =
        retrofit.create(StackApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): StackDatabase =
        Room.databaseBuilder(app, StackDatabase::class.java, "stack_database")
            .addTypeConverter(RoomConvertors())
            .build()
}