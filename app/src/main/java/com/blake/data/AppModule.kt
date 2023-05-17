package com.blake.data

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.blake.data.api.ServiceApi
import com.blake.data.storage.DriverDataRepository
import com.blake.data.storage.DriverDataRepositoryImpl
import com.blake.data.storage.DriverDataStorage
import com.blake.data.storage.DriverDataStorageImpl
import com.google.gson.Gson
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

    private const val BASE_URL = "https://d49c3a78-a4f2-437d-bf72-569334dea17c.mock.pstmn.io/"

    @Provides
    @Singleton
    fun provideServiceApi(): ServiceApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDriverDataRepository(
        api: ServiceApi, storage: DriverDataStorage
    ): DriverDataRepository {
        return DriverDataRepositoryImpl(storage, api)
    }

    @Provides
    @Singleton
    fun provideDriverDataStorage(gson: Gson, preferences: SharedPreferences): DriverDataStorage {
        return DriverDataStorageImpl(gson, preferences)
    }

    @Provides
    @Singleton
    fun providePreferences(app: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}