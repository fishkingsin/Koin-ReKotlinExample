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
        						"Title": "titlebat pine hit paper market salmon person branch flower widely frame week heart cutting call settle amount sometime old due orbit near now zero",
        						"Subtitle": "11",
        						"BannerUrl": "https://picsum.photos/600",
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
        						"SeqNo": 40143,
        						"Title": "titlemix third feathers remarkable class quite occasionally dust divide rise oldest dollar support journey baby time cold skill birth serve bear nervous donkey stop",
        						"Subtitle": "1",
        						"BannerUrl": "https://picsum.photos/600",
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
        						"SeqNo": 64693,
        						"Title": "titlepress corn watch angle basis rule voyage move market place voice courage forty prove knife shadow smoke football flow rock jar driving quietly ear",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
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
        						"SeqNo": 5324,
        						"Title": "titleinterior white raw metal produce sail glass flew silence rapidly reader post went tight wire warn score chapter planning drove taken brick shorter morning",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
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
        						"SeqNo": 47686,
        						"Title": "titletie nature bean opinion butter color keep frame ring sleep easier strike element exactly case object power had breath satellites meal dug court bigger",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
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
        						"SeqNo": 855,
        						"Title": "titleuncle large higher tank came word did size hand does steel consider though refer therefore pencil skin fallen dust rabbit cook slave castle flag",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "eng",
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
        						"Title": "titlehung tip picture ship sometime somehow fierce outside jar heat during but entirely of direct surprise desk look fully continued change hospital individual enemy",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "eng",
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
        						"Title": "titlepush gain try design trunk if space captured sudden characteristic turn coast shinning under master favorite changing sweet been rhythm pot birds exact office",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "eng",
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
        						"Title": "titlemoving go let secret sold pattern border bread captain zero flame naturally break vowel answer inch frame everywhere hand softly present animal whenever forgot",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
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
        						"SeqNo": 72378,
        						"Title": "titlebread full position health double not root next tea threw wealth exercise shop cheese sleep your careful recall record buffalo leather somehow light impossible",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
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
        						"SeqNo": 76434,
        						"Title": "titledriving sharp close success dirt war everyone face avoid whose tired consist sudden guide danger along future after machinery never tea blank chose slowly",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "en",
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
        						"Title": "titleherself three felt plane flew missing tall calm official boat teeth equal step enemy equipment parallel would feel depth fresh quiet president reach date",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "en",
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
        						"Title": "titlelong sister given personal instrument loud country compound cloth success touch safe crack thumb scale warn replied fallen spent no got situation arrow solve",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
        						"WebsiteUrl": "en",
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
        						"Title": "titlelayers doubt every carry shut beneath hung serve cream thread yard hole especially balance final believed find cup meal syllable compound nodded impossible done",
        						"Subtitle": "testen",
        						"BannerUrl": "https://picsum.photos/600",
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
