package com.dnevtukhova.photoeditor.api

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel (
@SerializedName("item") @field:ElementList(inline = true, name="item", required = false) var newsList: List<NewsItem>? = null,
@SerializedName("image") @field:Element(name ="image") var image: ImageUrl? = null
)
