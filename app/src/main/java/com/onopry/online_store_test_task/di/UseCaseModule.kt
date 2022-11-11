package com.onopry.online_store_test_task.di

import com.onopry.domain.repository.StoreRepository
import com.onopry.domain.usecases.GetBannersAndProductsUseCase
import com.onopry.domain.usecases.GetProductCategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun getBannersAndProductsUseCase(repository: StoreRepository) = GetBannersAndProductsUseCase(
        repository = repository
    )

    @Provides
    fun getProductCategoriesUseCase(repository: StoreRepository) =
        GetProductCategoriesUseCase(repository =  repository)
}