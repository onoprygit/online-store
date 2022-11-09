package com.onopry.data.datasource.remote

import com.onopry.domain.utils.ApiResult
import com.onopry.data.models.HomeResponse

interface RemoteDataSource {
    suspend fun getBannersAndProducts(): ApiResult<HomeResponse>
}

