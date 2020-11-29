package com.dnevtukhova.photoeditor.api

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "enclosure", strict = false)
data class Enclosure (
    @SerializedName("url") @field:Attribute(name = "url") var url: String ="",
    @SerializedName("type") @field:Attribute(name = "type") var type: String ="",
    @SerializedName("length") @field:Attribute(name = "length") var length: String =""

)
