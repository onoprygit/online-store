package com.onopry.data.models

import com.onopry.domain.models.home.BestSeller
import com.onopry.domain.models.home.HomeStore

data class HomeResponse(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)