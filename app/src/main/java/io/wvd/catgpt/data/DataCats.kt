package io.wvd.catgpt.data

import androidx.annotation.DrawableRes

data class Cat(
    val name: String,
    @DrawableRes val catImage: Int,
)

private val catName = listOf(
    "Meow Meow Meow Meow Meow",
    "Meow Meow Meow Meow",
    "Meow Meow Meow",
    "Meow",
)

private fun cats(): List<Cat> {
    //    for (cat in catName) {
//        cats.add(Cat(name = cat, catImage = R.drawable.avatar_meow))
//    }
    return mutableListOf()
}

object CatsRepo {
    fun getCats(): List<Cat> = cats()
}