package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import com.google.gson.annotations.SerializedName


data class ExploreCategory (

  @SerializedName("ExplorerType" ) var ExplorerType : String?            = null,
  @SerializedName("Name"         ) var Name         : String?            = null,
  @SerializedName("Icon"         ) var Icon         : String?            = null,
  @SerializedName("Weight"       ) var Weight       : Int?               = null,
  @SerializedName("RecordCount"  ) var RecordCount  : Int?               = null,
  @SerializedName("Records"      ) var Records      : ArrayList<ExploreItem> = arrayListOf()

)