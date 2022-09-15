package hk.com.chiefgroup.chiefx.journeys.explorecenter.redux

import com.google.gson.Gson
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploresReposonse

public abstract class ExploreCenterRepository {
    abstract suspend fun getExplores(): Result<ExploresReposonse>
    abstract fun getExplores(callback: (Result<ExploresReposonse>) -> Unit)
}


open class ExploreCenterRepositoryImplementation: ExploreCenterRepository() {
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

    override fun getExplores(callback: (Result<ExploresReposonse>) -> Unit) {
        val myJson = exploreCenterJSONString().trimIndent()
        val gson = Gson()
        try {
            val response = gson.fromJson(myJson, ExploresReposonse::class.java)
            callback(Result.success(response))
        } catch (exception: Exception) {
            callback(Result.failure(exception))
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
        				"ExplorerType": "wide",
        				"Name": "Test1En",
        				"Icon": "",
        				"Weight": 1,
        				"RecordCount": 4,
        				"Records": [
        					{
        						"SeqNo": 64741,
        						"Title": "bat pine hit paper market",
        						"Subtitle": "11",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://ihiise.er/odi",
        						"ShareContent": "1",
        						"StartTime": "202208230000",
        						"EndTime": "202209300000",
        						"RemainTimeText": "",
        						"ShowRemainTimeText": false,
        						"Weight": 0,
        						"Tags": "asd"
        					},
        					{
        						"SeqNo": 40143,
        						"Title": "mix third feathers remark",
        						"Subtitle": "1",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://abme.dk/fupgufej",
        						"ShareContent": "1",
        						"StartTime": "202208230000",
        						"EndTime": "202208310000",
        						"RemainTimeText": "7days10hours31mins",
        						"ShowRemainTimeText": true,
        						"Weight": 1,
        						"Tags": "1"
        					},
        					{
        						"SeqNo": 64693,
        						"Title": "press corn watch angle ba",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://punlu.ci/ze",
        						"ShareContent": "",
        						"StartTime": "202207010000",
        						"EndTime": "202207310000",
        						"RemainTimeText": "",
        						"ShowRemainTimeText": false,
        						"Weight": 7,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 5324,
        						"Title": "interior white raw metal ",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://lirkil.ga/luzaptoz",
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
        						"SeqNo": 47686,
        						"Title": "tie nature bean opinion b",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://januk.in/kes",
        						"ShareContent": "En",
        						"StartTime": "202208180000",
        						"EndTime": "202208310000",
        						"RemainTimeText": "7days10hours31mins",
        						"ShowRemainTimeText": true,
        						"Weight": 4,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 855,
        						"Title": "uncle large higher tank c",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://vozi.gs/bojez",
        						"ShareContent": "eng",
        						"StartTime": "000101010000",
        						"EndTime": "000101010000",
        						"RemainTimeText": "",
        						"ShowRemainTimeText": false,
        						"Weight": 10,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 35,
        						"Title": "hung tip picture ship som",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://ca.sx/etkonhi",
        						"ShareContent": "eng",
        						"StartTime": "000101010000",
        						"EndTime": "000101010000",
        						"RemainTimeText": "",
        						"ShowRemainTimeText": false,
        						"Weight": 10,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 85,
        						"Title": "push gain try design trun",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://ve.bt/uciwofer",
        						"ShareContent": "eng",
        						"StartTime": "000101010000",
        						"EndTime": "000101010000",
        						"RemainTimeText": "",
        						"ShowRemainTimeText": false,
        						"Weight": 10,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 115,
        						"Title": "moving go let secret sold",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://dizsuoma.cz/uvubu",
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
        						"SeqNo": 72378,
        						"Title": "bread full position healt",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://kej.kw/race",
        						"ShareContent": "en",
        						"StartTime": "202207140000",
        						"EndTime": "202207310000",
        						"RemainTimeText": "",
        						"ShowRemainTimeText": false,
        						"Weight": 5,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 76434,
        						"Title": "driving sharp close succe",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://ju.bd/cutaemi",
        						"ShareContent": "en",
        						"StartTime": "202208100000",
        						"EndTime": "202208310000",
        						"RemainTimeText": "7days10hours31mins",
        						"ShowRemainTimeText": true,
        						"Weight": 6,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 71434,
        						"Title": "herself three felt plane ",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://azu.co/jo",
        						"ShareContent": "en",
        						"StartTime": "202208100000",
        						"EndTime": "202208310000",
        						"RemainTimeText": "7days10hours31mins",
        						"ShowRemainTimeText": true,
        						"Weight": 6,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 14434,
        						"Title": "long sister given persona",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://gocno.ga/biudere",
        						"ShareContent": "en",
        						"StartTime": "202208100000",
        						"EndTime": "202208310000",
        						"RemainTimeText": "7days10hours31mins",
        						"ShowRemainTimeText": true,
        						"Weight": 6,
        						"Tags": ""
        					},
        					{
        						"SeqNo": 3434,
        						"Title": "layers doubt every carry ",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "http://dofhekolo.ci/dibigan",
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
