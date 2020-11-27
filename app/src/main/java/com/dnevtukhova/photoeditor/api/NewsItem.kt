package com.dnevtukhova.photoeditor.api

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class NewsItem constructor(

    @SerializedName("title") @field:Element(name = "title")var title: String = "",
    @SerializedName("description")  @field:Element(name = "description")var description: String = ""

)
