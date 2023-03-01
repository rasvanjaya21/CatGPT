package io.wvd.catgpt.util

import io.wvd.catgpt.R

sealed class TopNavBarItem (var title: String, var subTitle: String, var icon: Int){

    object Default : TopNavBarItem(title = "CatGPT", subTitle = "What if ChatGPT were a cat?", icon = R.drawable.filled_info_app)

}