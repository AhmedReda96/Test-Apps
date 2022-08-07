package ar.test_apps.dataLayer.di

import ar.test_apps.BuildConfig
import ar.test_apps.dataLayer.remote.ApiService
import ar.test_apps.utils.AppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val baseUrl = BuildConfig.POKEMON_APP_BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(appPreferences: AppPreferences): ApiService {
        return Retrofit.Builder()
            .baseUrl(appPreferences.getUrlType())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


}