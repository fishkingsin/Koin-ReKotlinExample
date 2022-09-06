package hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes

import com.google.gson.annotations.SerializedName


data class ExploresReposonse (

  @SerializedName("Object" ) var Object : ExploresObject? = null,
  @SerializedName("RT"     ) var RT     : String? = null,
  @SerializedName("REF"    ) var REF    : String? = null

)