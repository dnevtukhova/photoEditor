package com.dnevtukhova.photoeditor.api

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class News constructor(
     @SerializedName ("channel") @field:Element(name ="channel") var channel: Channel? = null
)
