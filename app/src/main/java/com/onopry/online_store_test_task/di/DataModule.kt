package com.onopry.online_store_test_task.di


import com.onopry.data.datasource.local.LocalDataSource
import com.onopry.data.datasource.local.LocalDataSourceImpl
import com.onopry.data.datasource.remote.RemoteDataSource
import com.onopry.data.datasource.remote.RemoteDataSourceImpl
import com.onopry.data.network.StoreApi
import com.onopry.data.repository.StoreRepositoryImpl
import com.onopry.domain.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: StoreApi): RemoteDataSource = RemoteDataSourceImpl(api = api)

    @Singleton
    @Provides
    fun provideLocalDataSource(): LocalDataSource = LocalDataSourceImpl()

    @Singleton
    @Provides
    fun provideStoreRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): StoreRepository =
        StoreRepositoryImpl(remoteDataSource = remoteDataSource, localDataSource = localDataSource)
}