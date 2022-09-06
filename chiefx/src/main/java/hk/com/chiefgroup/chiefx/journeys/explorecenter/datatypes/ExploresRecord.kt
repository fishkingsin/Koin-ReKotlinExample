package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import com.google.gson.annotations.SerializedName

data class ExploreItem (
    @SerializedName("SeqNo"              ) var SeqNo              : Int?     = null,
    @SerializedName("Title"              ) var Title              : String?  = null,
    @SerializedName("Subtitle"           ) var Subtitle           : String?  = null,
    @SerializedName("BannerUrl"          ) var BannerUrl          : String?  = null,
    @SerializedName("WebsiteUrl"         ) var WebsiteUrl         : String?  = null,
    @SerializedName("ShareContent"       ) var ShareContent       : String?  = null,
    @SerializedName("StartTime"          ) var StartTime          : String?  = null,
    @SerializedName("EndTime"            ) var EndTime            : String?  = null,
    @SerializedName("RemainTimeText"     ) var RemainTimeText     : String?  = null,
    @SerializedName("ShowRemainTimeText" ) var ShowRemainTimeText : Boolean? = null,
    @SerializedName("Weight"             ) var Weight             : Int?     = null,
    @SerializedName("Tags"               ) var Tags               : String?  = null

)