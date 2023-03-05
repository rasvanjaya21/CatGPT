package io.wvd.catgpt.util

import io.wvd.catgpt.R

sealed class TopBarItem (var title: String, var subTitle: String, var icon: Int){

    object Default : TopBarItem(title = "CatGPT", subTitle = "What if ChatGPT were a cat?", icon = R.drawable.filled_info_app)

}