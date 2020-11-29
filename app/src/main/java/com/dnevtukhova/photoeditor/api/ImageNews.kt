package com.dnevtukhova.photoeditor.api

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "image", strict = false)
class ImageUrl {
    @SerializedName("url")  @field:Element(name = "url")var url: String = ""
}
