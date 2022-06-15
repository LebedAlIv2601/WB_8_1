package com.example.wb_8_1.di

import android.content.Context
import com.example.wb_8_1.data.local.FileCacheManager
import com.example.wb_8_1.data.repository.DotaRepositoryImpl
import com.example.wb_8_1.domain.repository.DotaRepository
import com.example.wb_8_1.presentation.main.MainFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Component(modules = [DataModule::class, DomainBinds::class])
interface AppComponent{
    fun inject(mainFragment: MainFragment)
}

@Module
class DataModule (private val context: Context){

    @Provides
    fun provideContext(): Context{
        return context
    }


    @Provides
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Provides
    fun getFileCacheManager(context: Context): FileCacheManager{
        return FileCacheManager(context)
    }
}

@Module
interface DomainBinds{

    @ExperimentalStdlibApi
    @Binds
    fun doaRepositoryImplToInterface(
        repositoryImpl: DotaRepositoryImpl
    ): DotaRepository

}
