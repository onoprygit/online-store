package com.onopry.online_store_test_task.di

import com.onopry.data.datasource.remote.NetworkApi
import com.onopry.data.datasource.remote.RemoteDataSource
import com.onopry.data.datasource.remote.RemoteImplDataSource
import com.onopry.data.repository.StoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRemoteDataSource(api: NetworkApi) = RemoteImplDataSource(api = api)

    @Provides
    fun provideStoreRepository(dataSource: RemoteDataSource) =
        StoreRepositoryImpl(remoteDataSource = dataSource)
}