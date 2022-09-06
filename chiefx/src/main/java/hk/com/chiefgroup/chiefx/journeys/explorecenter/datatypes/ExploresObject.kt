package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import com.google.gson.annotations.SerializedName


data class ExploresObject (

  @SerializedName("Result"  ) var Result  : String?            = null,
  @SerializedName("Count"   ) var Count   : Int?               = null,
  @SerializedName("Records" ) var Records : ArrayList<ExploreCategory> = arrayListOf()

)