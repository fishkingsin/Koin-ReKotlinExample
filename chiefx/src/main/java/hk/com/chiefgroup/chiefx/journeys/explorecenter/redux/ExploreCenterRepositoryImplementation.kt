package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

import com.google.gson.Gson
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploresReposonse

class ExploreCenterRepositoryImplementation: ExploreCenterRepository() {
    override suspend fun getExplores(): Result<ExploresReposonse> {
        val myJson = exploreCenterJSONString().trimIndent()
        val gson = Gson()
        return try {
            val response = gson.fromJson(myJson, ExploresReposonse::class.java)
            Result.success(response)
        } catch (exception: Exception) {
            Result.failure(exception)
        }

    }

}

internal fun exploreCenterJSONString(): String {
    return """
        {
        	"Object": {
        		"Result": "1",
        		"Count": 3,
        		"Records": [
        			{
        				"ExplorerType": "Test1",
        				"Name": "Test1En",
        				"Icon": "",
        				"Weight": 1,
        				"RecordCount": 4,
        				"Records": [
        					{
        						"SeqNo": 11,
        						"Title": "1",
        						"Subtitle": "11",
        						"BannerUrl": "https://www.chiefgroup.com.hk/hk/financial/GetRecordFile?fileNo=140",
        						"WebsiteUrl": "1",
        						"ShareContent": "1",
        						"StartTime": "202208230000",
        						"EndTime": "202209300000",
        						"RemainTimeText": "",
        						"ShowRemainTimeText": false,
        						"Weight": 0,
        						"Tags": "asd"
        					},
        					{
        						"SeqNo": 10,
        						"Title": "1",
        						"Subtitle": "1",
        						"BannerUrl": "https://www.chiefgroup.com.hk/hk/financial/GetRecordFile?fileNo=9",
        						"WebsiteUrl": "1",
        						"ShareContent": "1",
        						"StartTime": "202208230000",
        						"EndTime": "202208310000",
        						"RemainTimeText": "7days10hours31mins",
        						"ShowRemainTimeText": true,
        						"Weight": 1,
        						"Tags": "1"
        					},
        					{
        						"SeqNo": 7,
        						"Title": "test7en",
        						"Subtitle": "testen",
        						"BannerUrl": "",
        						"WebsiteUrl": "",
        						"ShareContent": "",
        						"StartTime": "202207010000",
        						"EndTime": "202207310000",
        						"RemainTimeText": "",
        						"ShowRemainTimeText": false,
        						"Weight": 7,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 3,
        						"Title": "test3En",
        						"Subtitle": "testen",
        						"BannerUrl": "https://www.chiefgroup.com.hk/hk/financial/GetRecordFile?fileNo=3",
        						"WebsiteUrl": "en",
        						"ShareContent": "en",
        						"StartTime": "202207010000",
        						"EndTime": "202207310000",
        						"RemainTimeText": "0days0hours0mins",
        						"ShowRemainTimeText": true,
        						"Weight": 8,
        						"Tags": ""
        					}
        				]
        			},
        			{
        				"ExplorerType": "Test2",
        				"Name": "Test2En",
        				"Icon": "",
        				"Weight": 2,
        				"RecordCount": 2,
        				"Records": [
        					{
        						"SeqNo": 4,
        						"Title": "test4En",
        						"Subtitle": "testen",
        						"BannerUrl": "",
        						"WebsiteUrl": "En",
        						"ShareContent": "En",
        						"StartTime": "202208180000",
        						"EndTime": "202208310000",
        						"RemainTimeText": "7days10hours31mins",
        						"ShowRemainTimeText": true,
        						"Weight": 4,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 8,
        						"Title": "test8eng",
        						"Subtitle": "testen",
        						"BannerUrl": "",
        						"WebsiteUrl": "eng",
        						"ShareContent": "eng",
        						"StartTime": "000101010000",
        						"EndTime": "000101010000",
        						"RemainTimeText": "",
        						"ShowRemainTimeText": false,
        						"Weight": 10,
        						"Tags": ""
        					}
        				]
        			},
        			{
        				"ExplorerType": "Test3",
        				"Name": "Test3En",
        				"Icon": "",
        				"Weight": 3,
        				"RecordCount": 2,
        				"Records": [
        					{
        						"SeqNo": 5,
        						"Title": "test5en",
        						"Subtitle": "testen",
        						"BannerUrl": "",
        						"WebsiteUrl": "en",
        						"ShareContent": "en",
        						"StartTime": "202207140000",
        						"EndTime": "202207310000",
        						"RemainTimeText": "",
        						"ShowRemainTimeText": false,
        						"Weight": 5,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 6,
        						"Title": "test6en",
        						"Subtitle": "testen",
        						"BannerUrl": "",
        						"WebsiteUrl": "en",
        						"ShareContent": "en",
        						"StartTime": "202208100000",
        						"EndTime": "202208310000",
        						"RemainTimeText": "7days10hours31mins",
        						"ShowRemainTimeText": true,
        						"Weight": 6,
        						"Tags": ""
        					}
        				]
        			}
        		]
        	},
        	"RT": "S",
        	"REF": ""
        }
    """.trimIndent()
}
