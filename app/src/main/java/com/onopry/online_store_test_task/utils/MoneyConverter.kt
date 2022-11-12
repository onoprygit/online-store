package com.onopry.online_store_test_task.utils

abstract class BaseMoneyConverter() {
    abstract fun print(money: Int?): String

    class CartTotal : BaseMoneyConverter() {
        override fun print(money: Int?): String {
            if (money == null)
                return "N/A"
            return "$${money/1000},${money%1000} us"
        }
    }

    class CartItem : BaseMoneyConverter(){
        override fun print(money: Int?): String {
            if (money == null)
                return "N/A"
            return "$"+String.format("%.2f", money.toFloat())
        }
    }
}
