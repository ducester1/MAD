package com.example.madlevel4task1

data class Product(
    var amount: Int,
    var name: String,
) {
    companion object {
        var AMOUNT = arrayOf(
                2,
                2,
                5,
                2
        )
        var NAMES = arrayOf(
                "Beef",
                "Carrots",
                "Hay",
                "Goat milk"
        )
    }
}