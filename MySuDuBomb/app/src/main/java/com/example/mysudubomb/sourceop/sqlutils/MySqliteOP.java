package com.example.mysudubomb.sourceop.sqlutils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mysudubomb.adapter.FoodAdapter;
import com.example.mysudubomb.bean.FoodInfo;
import com.example.mysudubomb.properties.AppProperties;
import java.util.ArrayList;
import java.util.List;

public class MySqliteOP {

   private String[] shicai ={ "马铃薯", "紫薯", "红薯" ,"米饭","面条","全麦面包","玉米","大米粥","小米粥","粗粮馒头",
            "绿豆","红豆","鸡胸肉","去皮鸡腿","鸭胸肉","牛腱子肉","瘦羊肉","瘦猪肉","猪里脊","培根","火腿肠","叉烧肉",
            "猪小排","炸鸡","蛤蜊","章鱼","生蚝","扇贝","虾仁","螃蟹","巴沙鱼","鳕鱼","豆浆","豆腐脑","豆腐","豆腐干",
            "豆腐皮","牛奶","蛋清","鸡蛋","蛋白粉","酸奶","奶酪","奶油","瓜子","杏仁","板栗","芝麻","腰果","花生",
            "核桃","开心果","榛子仁","苦菊","娃娃菜","生菜","西红柿","黄瓜","油麦菜","白萝卜","卷心菜","紫甘蓝",
            "南瓜","茄子","杏鲍菇","蘑菇","茼蒿","菠菜"};
    private String[] kcal={ "77.0Kcal", "80.0Kcal", "83.4Kcal" ,"116.0Kcal","110.0Kcal","235.0Kcal","112.0Kcal","47.0Kcal","46.0Kcal","223.0Kcal","329.0Kcal","324.0Kcal","101.0Kcal","99.0Kcal","90.0Kcal"
            ,"142.0Kcal","118.0Kcal","143.0Kcal","155.0Kcal","181.0Kcal","212.0Kcal","279.0Kcal","278.0Kcal","279.0Kcal","31.0Kcal","52.0Kcal","57.0Kcal","66.0Kcal","68.0Kcal","62.0Kcal"
            ,"72.0Kcal","88.0Kcal","31.0Kcal","15.0Kcal","82.0Kcal","197.0Kcal","447.0Kcal","60.0Kcal","60.0Kcal","156.0Kcal","367.0Kcal","72.0Kcal","328.0Kcal","720.0Kcal"
            ,"582.0Kcal","514.0Kcal","188.0Kcal","559.0Kcal","556.0Kcal","567.0Kcal","336.0Kcal","614.0Kcal","561.0Kcal","13.0Kcal","13.0Kcal","14.0Kcal","14.0Kcal","15.0Kcal"
            ,"15.0Kcal","16.0Kcal","22.0Kcal","22.0Kcal","23.0Kcal","23.0Kcal","23.0Kcal","24.0Kcal","24.0Kcal","28.0Kcal"};
    private String[] tanshui={"16.5g","17.7g","15.3g","25.6g","24.2g","45.1g","19.9g","9.8g","8.4g","48.0g","55.6g","55.7g","2.5g","0.0g","4.0g","4.0g","0.2g","1.5g","0.7g","2.6g",
            "15.6g","7.9g","0.7g","10.5g","1.1g","1.4g","0.0g","2.6g","2.3g","1.1g","0.0g","0.5g","1.2g","0.0g","3.8g","9.6g","12.5g","5.1g","3.1g","1.3g","11.0g","9.3g","3.5g","0.7g",
            "3.8g","2.9g","40.5g","10.0g","41.6g","7.6g","1.8g","21.9g","14.7g","1.3g","0.1g","1.3g","2.4g","2.4g","1.5g","4.0g","3.6g","3.6g","4.5g","3.5g","2.7g","2.0g","2.7g","2.8g"};
    private String[] danbai={"2.0g","2.4g","0.7g","2.6g","2.7g","9.8g","4.0g","1.1g","1.4g","5.1g","21.6g","20.2g","19.4g","18.0g","15.0g","18.7g","20.5g","20.3g","20.2g","22.2g",
            "14.0g","23.8g" ,"16.7g","20.3g","5.8g","10.6g","10.9g","11.1g","15.4g","11.6g","10.8g","20.4g","3.0g","1.9g","8.1g","14.9g","51.6g","3.1g","11.6g","12.8g","70.6g","2.5g",
            "25.7g","2.5g","36.0g","24.7g","4.2g","19.1g","17.3g","25.8g","12.8g","20.6g","20.0g","1.3g","1.9g","1.3g","0.6g","0.8g","1.4g","0.7g","1.5g","1.5g","0.7g","1.1g","2.7g","2.7g","1.9g","2.6g"};
    private String[] zhifang={"0.2g","0.0g","0.2g","0.3g","0.2g","1.7g","1.2g","0.3g","0.7g","1.2g","0.8g","0.6g","1.5g","3.0g","1.5g","5.8g","3.9g","6.2g","7.9g","9.0g",
            "10.4g","16.9g","23.1g","17.9g","0.4g","0.4g","1.5g","0.6g","0.7g","1.2g","3.2g","0.5g","1.6g","0.8g","3.7g","11.3g","23.0g","3.0g","0.1g","11.1g","4.5g","2.7g","23.5g","78.6g","46.1g","44.8g",
            "0.7g","46.1g","36.7g","49.2g","29.9g","53.0g","44.8g","0.3g","0.0g","0.3g","0.1g","0.2g","0.4g","0.1g","0.2g","0.2g","0.1g","0.2g","0.2g","0.1g","0.3g","0.3g"};
    private String[] description={"日常生活中常常被当做蔬菜，如酸辣土豆丝、干锅土豆，这样会使一餐中碳水摄入过多，推荐直接蒸煮替代米饭做主食。等量替换时热量、蛋白质摄入略少，需注意适当增加摄入量。","紫薯属于薯类，是常见的杂粮主食选择，富含碳水化合物，同时含有丰富的花青素，饱腹感强，推荐作为主食，搭配优质蛋白的食物一起食用。",
            "红薯属于薯类，是常见的杂粮主食选择，富含碳水化合物，和紫薯不同，红薯含有丰富的胡萝卜素，推荐食用。","精细碳水，血糖反应较高，作为主食可以适量食用，但用各类粗粮代替更合适。","属于精白米面，维生素矿物质损失较大，GI值较高，饱腹感不强，可适量食用。",
            "由全麦粉制成，膳食纤维含量高，饱腹感强于普通吐司面包，推荐食用。","中GI粗粮，蛋白、脂肪、膳食纤维略高于大米，推荐替代米饭作为主食食用。","属于精白米面，维生素矿物质损失较大，GI值较高，饱腹感不强，可适量食用，建议用部分杂粮薯类替代。","高GI粗粮，蛋白、脂肪、膳食纤维略高于大米，推荐食用。",
            "与白面馒头相比，粗粮馒头含有更多的维生素、矿物质和膳食纤维，推荐食用。","低GI淀粉豆，煮熟后蛋白含量高于米饭，推荐作为主食。搭配巴西坚果、芝麻等坚果或谷物可以提升蛋白质量。","低GI淀粉豆，煮熟后蛋白含量高于米饭，推荐作为主食。搭配巴西坚果、芝麻等坚果或谷物可以提升蛋白质量。",
            "高蛋白低脂肪白肉，无论增肌或减脂都非常适合作为主要膳食蛋白质来源。","鸡肉属于白肉，是优质蛋白质来源，鸡腿去皮后脂肪含量低，推荐食用。","高蛋白低脂肪禽肉，是优质蛋白的来源，富含铁，推荐食用。","牛肉中蛋白含量高，脂肪含量较低的部分，推荐减脂时作为蛋白来源摄入。",
            "高蛋白红肉，是优质蛋白质来源，富含维生素B1、B2、血红素铁、锌，可适量食用。","高蛋白红肉，是优质蛋白质来源，富含维生素B1、B2、血红素铁、锌，可适量食用。","高蛋白红肉，是优质蛋白质来源，富含维生素B1、锌，可适量食用。","加工肉制品，脂肪、钠含量高，吃一口解解馋就好了。",
            "加工肉制品，加入淀粉和脂肪，吃一口解解馋就好了。","脂肪、精制糖含量高，不适合在减脂期摄入，吃一口解解馋就好。","高蛋白超高脂肪红肉，是优质蛋白的来源，吃一口解解馋就好了。","高蛋白高脂肪禽肉制品，吃一口解解馋就好了。","较高蛋白低脂肪低热量水产品，是优质蛋白来源，富含维生素B12、铁、硒，推荐食用。",
            "较高蛋白低脂肪低热量水产品，是优质蛋白来源，富含铁、硒，推荐食用。","高蛋白低脂肪水产品，是优质蛋白质的来源，富含铁、锌、硒，推荐食用。","高蛋白低脂肪水产品，是优质蛋白来源，富含维生素E、维生素B2、钙、铁、锌、硒，推荐食用。","高蛋白低脂肪水产品，是优质蛋白来源，富含ω-3脂肪酸、维生素D、硒，推荐食用。推荐食用。",
            "较高蛋白低脂肪水产品，是优质蛋白的来源，富含维生素B12、钙、硒，推荐食用。","巴沙鱼又被成为越南鲶鱼，属于芒鲶属的淡水鱼，刺少，是优质的蛋白质来源，推荐食用。","高蛋白低脂肪鱼类，是优质蛋白的来源，富含硒，推荐食用。","低热量豆制品，热量随黄豆和水的比例不同差异较大。豆浆富含卵磷脂、大豆异黄酮，不建议加糖，推荐饮用。",
            "低热量大豆制品，配合低卡卤子食用有利于减脂，推荐食用。","豆腐是素食者重要的蛋白来源，蛋白质质量在植物性食品中较高，富含钙，推荐食用。","高蛋白大豆制品，是优质植物蛋白质来源，富含钙、铁，推荐食用。豆腐干种类众多，有菜干、臭干、酱油干、卤干、香干、熏干等，不同豆腐干间热量和营养成分存在一定差异。质地越硬，口感越油腻，热量越高。",
            "高蛋白大豆制品，富含铁，但生物利用率较低，推荐食用。","蛋白含量高，消化吸收率高，是优质蛋白的来源，钙的生物利用率高，推荐食用。","高蛋白食品，是优质蛋白的来源，富含维生素B2、硒。蛋黄虽然脂肪含量高，但营养素密度也很高，食用量小于50g时建议连蛋黄一同食用。","高蛋白食品，是优质蛋白的来源，富含维生素B2、硒。蛋黄虽然脂肪含量高，但营养素密度也很高，食用量小于50g时建议连蛋黄一同食用。",
            "蛋白粉大部分是分离自牛乳中的乳清蛋白，是优质的膳食蛋白质来源，推荐食用。","与牛奶相比，适合乳糖不耐受人群食用，含有一定发酵产物和益生菌，能在一定程度上改善肠道菌群，但市售产品一般加入较多糖，建议选择无添加糖的酸奶。","高蛋白高脂肪奶制品，是优质蛋白的来源，富含钙，但能量密度较大，可适量食用。食用时建议选择脱脂和不加糖的产品",
            "脂肪含量极高，且多为饱和脂肪酸，通常还会加入大量糖，吃一口解解馋就好了。","高蛋白油脂类坚果，富含铁、锌。脂肪酸以ω-6多不饱和脂肪酸为主，推荐摄入总量不超过35g。","巴旦木蛋白质含量较高但质量略低，富含多不饱和脂肪酸，其中ω-6脂肪酸较多，富含维生素E、维生素B2、膳食纤维，适合作为加餐提供能量和营养，推荐摄入总量不超过35g。",
            "淀粉类坚果，富含维生素C，推荐替代米饭做主食，但要注意增加蛋白质摄入，不要因为好吃停不下来呦。","高蛋白油脂类坚果，富含膳食纤维、维生素B1、钙、铁、锌。脂肪酸以ω-6多不饱和脂肪酸为主，可搭配肉类食用提高蛋白质质量，推荐摄入总量不超过35g。","高蛋白坚果，碳水化合物和脂肪含量均较高，富含铁、锌。脂肪以单不饱和脂肪酸为主，推荐摄入总量不超过35g。",
            "高蛋白油脂类坚果，富含膳食纤维、维生素E、叶酸，营养价值高于干花生。脂肪以单不饱和脂肪酸为主，可搭配肉类食用提高蛋白质质量，推荐摄入总量不超过35g。","高蛋白油脂类坚果，膳食纤维含量较高，营养价值高于干核桃。搭配肉类食用可提高蛋白质量，每天食用2个即可满足ω-3脂肪酸推荐摄入量，摄入总量不超过35g。","高蛋白油脂类坚果，富含膳食纤维、维生素B1。脂肪以单不饱和脂肪酸为主，可搭配肉类食用提高蛋白质质量，推荐摄入总量不超过35g。",
            "高蛋白油脂类坚果，富含膳食纤维、维生素B1。脂肪以单不饱和脂肪酸为主，可搭配肉类食用提高蛋白质质量，推荐摄入总量不超过35g。","绿色蔬菜，富含膳食纤维、维生素C、E，维生素B1、B2、钙、铁、锌，营养价值高，略带苦味，常见于蔬菜沙拉，推荐食用。","浅黄绿色蔬菜，富含膳食纤维、维生素C、E，维生素B2、钙、铁、锌，营养价值高，饱腹感强，推荐食用。","绿色蔬菜，富含膳食纤维、维生素A、C、E、维生素B2、钙、铁、锌，营养价值高，饱腹感强，推荐食用。",
            "红色蔬菜，富含维生素A、C、E、铁、番茄红素，营养价值高，饱腹感极强，推荐食用。","浅色瓜类蔬菜，富含膳食纤维、维生素A、C、E、钙、铁，营养价值高，饱腹感强，推荐食用。","深绿色蔬菜，富含膳食纤维、维生素A、C、维生素B2、钙、铁、锌，营养价值高，饱腹感强，推荐食用。","浅色根茎类蔬菜，富含膳食纤维、维生素C、维生素E、钙、铁、锌，营养价值高，饱腹感强，推荐食用。",
            "绿色叶菜，富含膳食纤维、维生素C、E、钙、铁，营养价值高，饱腹感强，推荐食用。","紫甘蓝属于深紫色蔬菜，富含膳食纤维、维生素C、铁、花青素，营养素密度高，饱腹感强，推荐作为沙拉生食，有助于减脂。","橙色瓜类蔬菜，富含膳食纤维、维生素A、C、E、铁，营养价值高，饱腹感强，推荐食用。不同品种间碳水含量差异较大，面质的可部分替代主食食用。",
            "茄科蔬菜，富含膳食纤维、维生素C、E、钙、铁，营养价值高，饱腹感强，推荐食用。","浅色菌菇类蔬菜，富含膳食纤维、维生素E、维生素B2、铁，营养价值高，饱腹感强，丰富的呈味氨基酸使其滋味鲜美，推荐食用。","菌菇类蔬菜，富含膳食纤维、维生素E、维生素B1、B2、铁、锌，营养价值高，饱腹感强，推荐食用。","深绿色蔬菜，富含膳食纤维、维生素A、C、E、钙、铁、锌，营养价值高，饱腹感强，推荐食用。","深绿色蔬菜，富含富含膳食纤维、维生素A、C、E、维生素B2、钙、铁、锌，营养价值高，饱腹感强，但草酸含量稍高，建议先焯水再食用。"};
  /*  private int[] foodImageId ={R.mipmap.food_mls,R.mipmap.food_zishu,R.mipmap.food_hongshu,R.mipmap.food_mifan,R.mipmap.food_miantiao,R.mipmap.food_mianbao,R.mipmap.food_yumi,R.mipmap.food_damizhou,R.mipmap.food_xiaomizhou,R.mipmap.food_mantou,R.mipmap.food_lvdou,R.mipmap.food_hongdou,R.mipmap.food_jixiong,R.mipmap.food_jitui,R.mipmap.food_taxiong,R.mipmap.food_niujianzi,R.mipmap.food_yang,R.mipmap.food_shouzhu,R.mipmap.food_liji,
    R.mipmap.food_peigen,R.mipmap.food_chang,R.mipmap.food_chashao,R.mipmap.food_zhupai,R.mipmap.food_zhaji,R.mipmap.food_hali,R.mipmap.food_zhangyu,R.mipmap.food_shenghao,R.mipmap.food_shanbei,R.mipmap.food_xiaren,R.mipmap.food_pangxie,R.mipmap.food_basha,R.mipmap.food_longli,R.mipmap.food_doujiang,R.mipmap.food_dounao,R.mipmap.food_doufu,R.mipmap.food_dougan,R.mipmap.food_doupi,R.mipmap.food_niunai,R.mipmap.food_danqing,
    R.mipmap.food_jidan,R.mipmap.food_danbaifen,R.mipmap.food_suannai,R.mipmap.food_nailao,R.mipmap.food_naiyou,R.mipmap.food_guazi,R.mipmap.food_xingren,R.mipmap.food_banli,R.mipmap.food_zhima,R.mipmap.food_yaoguo,R.mipmap.food_huasheng,R.mipmap.food_hetao,R.mipmap.food_kaixinguo,R.mipmap.food_zhenzi,R.mipmap.food_kuju,R.mipmap.food_wawacai,R.mipmap.food_juanxincai,R.mipmap.food_xihongshi,R.mipmap.food_huangga,R.mipmap.food_youmaicai,
    R.mipmap.food_bailuobo,R.mipmap.food_juanxin,R.mipmap.food_jiganlan,R.mipmap.food_nangua,R.mipmap.food_qiezi,R.mipmap.food_mogu,R.mipmap.food_mougu,R.mipmap.food_tonghao,R.mipmap.food_bocai};
    */

  private  String[] foodImagePath ={"http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX*vLk5vruF2b.*4bPV*X.aNBzqsMKKnXHriAJpU1AYTiRTdpLqVEmEX8nOk6sk1vl4k43xk2xwR4E.oPqCKFNAk!/r",
  "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX0Z54kIhH2xMFOQZqL14KFNJ0*p8EAduVggCBuRZcsqCLx0LVEZxuuC89i0R50L.lPSgyZcJaOE0aCEo4OHUekg!/r",
  "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX01OgG0zMb*ZbyhvJVXWsGg83YNZSFRt6.damp0YO8Kd8Pob3K5CW07LoVigo4wAMZTA0hYi8hkUPOBKJy.8fPA!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX2bZrq4HQgfX*K8R29Qgka.yyAjPD8Qwn31DVaUJVKHVviwR21uAcu5cnqpZterDsVW13V.cuBe6zKGfqkiY6AA!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX2Bi3EZBqoDqIS4ETqiW9td94TbfiTr*raHJ9nXq2lepQLwlJ2i8AsTFyRzAig5zQSqktIw0djl0EUDjtLOVlz8!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX.JVUzHwyJ1PNnkAAyZ51hKhoLr54.BQYCIrEv4dLro3DmJ6DdHGCebnf47HOYsBEjL5hzx*uJbbn3s9G07PSYc!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXyZjrAAXlLdaGY.kXUCJhtnfUtlI5fMohsE8IrAxmj1bWsvnxM31m0z1VV76Ix3.GmkhVQ5BQ5oa0tzoMM10jk4!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX.pjR0E4OCyKefBhVdJyxK4lssrzJFnlJIvD3iWXEd6ofv7faY1NHPWeYdbn8NKKP3BMV8lyTQk83d0QSSVHYZM!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX6RKX1cONifLMTMLdl0ATIFt88c37dxQulIU*G69DfKEm0wScH845GLA7oh8PK5w8nYL5ujjoCIt.NFhDojjWPM!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX7Wzcl9vaUmkpYn4qDFFYpyAC68NVzw3niF5duKFIXffRzi*95pVe79c*VnCefJW9nye5eP*MAXMM0NGGyOMDnk!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX0h5nuBhEuuQfawGbZOpnn0pTGhKUXg2cAbMYzOi.sITXyP0fp1dxTF6sbs6u6w1b.TKChc6zyySkQJU5JAUVBI!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX62kpuh0ZUo9GxO7qTGAS8gId4w10jjo*BBPWly18ybg0IUcTdL.e5rRvYkV7edFb6m5H0DihFCQvWZfHjsZroE!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX9RBOOVz1DWTOHC27aOXLcnK0PXRtpUoINIFt9RXGQR2jCtpGeE.eKCCEbEdAt5mQeostbBelMiUDlt12GBcPww!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX8PCaBgNVvFCCFNBTmriOqH7WOJl2BxaUKxUgu.LemBFEmSPv5K6.eI2bklVvye0ABAk95wJyWmPsTZcE3pxhhE!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXxOzIaxKQNBOGSMcELWAJ7Bne*V1QPRxwvQ9hm5XDNkpZmZXqFqXvrIWBoA5sK3Tc3ZkoF9llf6rqeLo4jqTrv4!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX*YCdoeMZGKpNKKlihIlEXbuP0JuUvlccwf6BRFtAmi6riQsSAQFnVnMDLHMq*ZXLjP05zk0jo8z6sGK5A0X79U!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX3vCuSbKLFUd6ZO76x2ef2rzcASRnXAvq8ycrqgT6HsgMzkuxEimuk5LpnO0lu8RFLeUc5V423TUxhYoFVsrtBs!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX0Kx0UGGltvPit2JjccF2rCtC9HRGaxDJswwKm*b0iRwmNzQadmHDdz*KCkTgCR8rkPiVwUA.ZwmoUEDjYb4QFw!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX5Zl5szNOAcVfTNLP9YdSfOAbAymBGhY5eHuA39UKn8Yv4xWlyXgQnz2x*7qe4vOwXalL5KeR3Irnv1rOfDp8Pw!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX60PhrpcGiIKyBRzD*RDA4MNitEbtzqTJHylszSRzzNGMhRFeJhGtT*BWs9GqIBNgH4lDv1seobuznxfVbbCOY4!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX2w1vuZzFH0Dpm99h48KwpagWZA1*AeGiMSdGwIv1MZjdfZTzDtayh9s.ydEa0zPwQu*JMjB1f0UPm70cTUowFo!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX54nMjdJsnxi258O*bw5tzhhRn288mPBzqOb*MQhSdfLL4emRTuF4jfbDhSbenN7DmVhgR2UW4h1S5ZFY9mjSAo!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX8lk973lfhbAXsmOYxm*xp.rN5naxExgQL86sfYa2gTlWv8Y.Ceqszdig5Y8qBoaoXXU4yZG8P1UMFHGJ.O5Tqw!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX5bU7ECX2iZFGA0uki12XpCGXwfyQi6TgqtsyjyVvg6*sQUHomnzHZZJ.U84lU5GJoFx4khis3WWcTqhL774sio!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXyqdczDzdL*ioqnlvTOSNG6JWpK596ovDSBGRE1otY*87*qSAkHXp60Pfhb4u4oll6GL8M6IDkTpPiZEaCJsNlo!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX7BvfRC*OUEeJ7Ttoj7WT74SP9QbuK3Zbl5xgYAi7iV8gfPJVWndBqw8rLZu*LUv*X6pFxXKe6xP5ebtlVeXy1E!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX87ISpj9wdKzOB0coK7qbXl8xLAHiKryYeYnJpn6YIDngIwHomMNG6tevVtxLcJ8PTPTwDHneNsTjm0rPI2pm4o!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXzDVLUkTKkrmzb8liTnGHooF34STEw5DxHyiXZhNpn.ifNq9yWmZmO27f9nZfGQm8nlqhxi4eX2dmhLLl21Dsqw!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX4FUhwE2PBmU*IiRKJWLBA.g*21RYJnzkA2aMZnZjOuI9bFc9Fh.SLHkleIHLbNar*uiBuzC6jtN*QZA98D.uiA!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX1n8hMtrID6sc5h5JkNJfn2vosaZTFQGOvhHXsAuMCFWVF9NtljLn2e5d4GrXVn7xwXZh16xZBcJyGjKKcC3OaI!/r",
          "http://m.qpic.cn/psc?/V12fRLIz1E8vqI/jKwCg1C7YRUNu7w0Ty5ry.fKjvlAZY.qj4rab7mA1FFAp3u.C6oT1h9JG3QG3.c.TY6XCSmwapB8fE6O0AGUJw!!/b&bo=VgJXAlYCVwIRCT4!&rf=viewer_4",
          "http://m.qpic.cn/psc?/V12fRLIz1E8vqI/jKwCg1C7YRUNu7w0Ty5ryxWU9SFLrdjfERDNrx2e*wMI2FHvi3r5RrdsCzFwDAIyJEXk5Mb*TEhksaSIH3TtwQ!!/b&bo=9AH0AfQB9AERCT4!&rf=viewer_4",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX29em9wBYppM*r.bq3ttDWyuemePcF6Vysvvrmgi75bO*1mdR8uAFIvz.MV0tPgE87cZsMko2L.xvBr85BUm.5A!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX5xpiMAb*dqMGQDKrYJn7lUgmvJ8f312KA.68OxlGN92HdWQBP8ZzbDY2Hh3XBUiO617.L78ehpZuPOyuMy6oLc!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX2lE5BRUrcv4J71vE*ExS.TpJasqgH5Cf0Fd9Qf2IayzyuOjDCJU5lb5kXMPjyXzDj7TDbAG3gmKYTrTkbU3wY4!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXwjDK.4Mb.0GsvjdWHx5Z090SS0sdlh2GkBk37fMkveJ7wRaUxDDft8FthmAofft1Fro5AHDvAV15CGycqRYoHg!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX2V9c9Fr1weRIsrCnmYgKVVFd5JasJJkthqdtGD9DLbBhZ1.SwQH.q9uHZPl*ZA*poG48FEvklq7LvelC4Lu8ME!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX1eu2ZY62Np7oQmJ69wzPMnZoWoyphXBR4s7nQcXO1.xB0CdOEbSPaNVr4moPiOLCGNGoxDWHbR6AQmfXULJeDg!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX1VaDeXRbd3aoTVwRhqOMjiU9rcCF8I931kJXb8.hagF*E4P21jy3ZvzWCQMajGwjTtQ0gYJ89oV2*mSFCb6X*M!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX9EPpsdSA.QtSt0Dx5oP*gmOA*Tg9lqXP*wHCpzJdLHlevFXm*ugXBJ6zlkBkSIWxQerljY72jY83nk8twbRuLE!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX6IrsR0tdin148RN2EUyiRucbTzOJxDCpoG4ylzncre0Z2jF8Xn9CBD.6cn9xkq2giXtiR4WQ23FDcSYikcZpTE!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX76qn1Gz3Ojy.lduCFHj30HKP.EQKV.YtEENYx6wTAs0GgbWtvOgVJWODaVDdHsgOhptMZ*XNymRq.Y*JUkHtqA!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX7Yh.8B7gN12Uo2O4E7TPNW3OQNB3jG**1kO2R78WQDTOnfaqQ.xUO7wNZ6IvzYCzrfn51RNr4Wr9NkfcvOJ6wA!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX9amxBioJ*A37.g4xCsUomGGMfP*BVR.wdtXjnk.N9p0BCmT2jJ8.l*Kfcw.ik5PQ8Y7CwN8yrOT2bq.mrI2Y1k!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXzA6eiiBOhtZTMWiW2Iz2GcXD7ZCRrBQiOi1defnQ*YWIqWLXRlKLPrmxpKy36w3YG3HQC3rMgpTd2pkkQPlBJI!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX6hzlmLPvI8LFkKznx2uYxdwFFkEMQziJ2FT1Nzny6y2oarme9RUmn6H4.dsamfQJeumYycOq2KROlfDe1Q8n.I!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX4WON1ep0igVVK4oewsnOHf0gnxYV6sXINAC12KhiQDd4mALnyGJkboeHVRTsBAFljHGIvVfwR98ehRcb7fqXdM!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXwbof1XB1Th9OPatTO2AMJW7*B8CX4c5n7A9VbizzG8Erd*072zXEwHxaTml.NcjLXO7blJdcmNHFHPQYp3cy7U!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX4wRqdbCLGJPH*HWQvnmO8hW0tRlxK22twtuRBWi40ViGuH5IdMDS3fHpD*D17AW5.60v7k5GJWrChM38GZEvjk!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX58yPucMzSpS6ve6Zkx3uNIW7BJwsniybae8FFwRtaJ12Uky4ZrdFDnjLWhcQ6tk4bQrQaCO.k6wf85.0Pp.15U!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX1Dz1UTCpI1D.jZB4KikhTvZcqCH8cHDCUceukArGd4cKrQpsPZ*OWqMj5*K8u3UhhKGdrjfiyCYb8v4*3AtDWc!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXzuSCFO8JzHPcUm1Dtfv*7F87Fdx0yX*F5qy0yBW40t1DXAbrrnloONBjpWo788ZKnPZFrnwuis8NR*0gMlcWEo!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX6JAyVzuKi9PuD8lbjMImHyT99QthhwY.nablWuBF1mDVdh4cEqKbqHwf5btIA2sJvhDkHLCHQ2.fGiLXJ0MO18!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX0zv3C4j5dcy*.xWm.F4XY0P2tFzvE5QH5VNchIEr7q1.iVXP0CTRFubKc5vM6WuduVKT.E9wH.e7jj8SG4afSU!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXzGyGgJWBw87VK9AnOeIqAhJLNbKiSO8NWxZ5v.ZvpihP*9L8MYTyKFl.hlgeR7IqvAXgsOUS6L.QX03A9JkouM!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXzuZkLlOeu7PQCr870uSh*eLdj.9m1UQUQXhWyHsOLizRPUG9nJElIL1iNm4Hmk1qvjFSTz5ZYCEBP3phcLv3jk!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX9V2hJGKplJwzEla42ysJAuw2nfbIBCwPy7cufFEjoFgBsvdmLj2widNPlm*Np7ATK.RPt*7TR7*AbOEXsLp6HI!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX4o3CJV9EC9aAgCGIWZoX3.blob4LtEZYb0BHXMFOydRmiO4OUAX76367jm1cLoCL3aa2uveOpikapXOKHsduNs!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX9jFr1VG9jYIgWAF5lvdgoY2oBLQyEMsI3rjIEUSB5Xy.8MnQVExcAQtmFxS*WAkXJPep.7Dtss7nZCndEOb6sk!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX3sUImbaaTsKSeu4bL5zFMFwS010xzGZQpxULw*VX.JuJNB*ws.KYrIkQBHE5UgeCQvjWjMzIhaksv*Ya*MDvls!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX2uC9aEC.reDpiM3c81cE60T.zx*s2RQjqNsVJFMLV94BPXGJuLYujcFu3gpJSME221QzVsP9.hJGju9qmhx*C8!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX*m0uFcYyUBK6zOOPqShvwIN03FrIhx0v7CvvHAR*xQDuXCHTMoaMnrz8M9h94wOJ8jrJv0Wkr31TnEW3ONqkFM!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX*Fi1HyPmgV0r3KaGyjBXY0PkGVGj0*ZWSGXsD5GOVSqmTY3TsX7biJpTHb0UANl52C9r8NcRd4nDdYcoZcsNeA!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX.QSplbStq2Ha58b2IZy5SXFn9n2NE8gZwMTLYMP8jA0RRgDO6JYBqTsCpOHb2af6wkoXlew.4fYxbnx8Jgm0Bw!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX5VVioToMmXoG0zOBxaJ9579FHunPWMj9DRx9CxvFTc85DecZgJbhUjhvh.eSlP4a60EEeIpu.IK*R6uydhdUnc!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANXx4DJ*ut4pF2CRIiMjnOmbj2OoDJBtBy1JYtcEQNXqENKuOSVuo1RUttzbrFA.allfjYD*RFWJ.9KwWA6zvzdLE!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX5ahEsiMlGG4lu6YhPCElbAjxFgs0tpatsizO*jSt3XVuV5vitGFdieeKS79SHsspugEzik3vvFf*7sZVMRnOM8!/r",
          "http://r.photo.store.qq.com/psc?/V12fRLIz1E8vqI/TCfiP1YaPeRT4Jil9RANX3ypfu.a5HWtanxUKyd47gvSDTEw5Y2gwvMot4p2yef*ZduAFCSfv9KZa1uFbepEHXUeW52iE3D29nBsf5mLTF8!/r"
  };
    private  MySqliteHelper helper;

    public MySqliteOP(Context context) {
        helper = new MySqliteHelper(context);
    }
    public MySqliteOP() {
    }
    public void insert(SQLiteDatabase db){
        
        int counter= 0;
        while (!insertFoodWithException(db)){

            System.out.println("--------failed");
            if (counter++ == 3)
                break;
        }
        if (counter!=4)
            System.out.println("success");
    }
    public boolean insertFoodWithException(SQLiteDatabase db){


        db.beginTransaction();
        try {
            for (int i=0;i<shicai.length;i++) {
                ContentValues values = new ContentValues();
                values.put("shicai",shicai[i] );
                values.put("kcal",kcal[i] );
                values.put("tanshui",tanshui[i] );
                values.put("danbai", danbai[i]);
                values.put("zhifang", zhifang[i]);
                values.put("foodimage", foodImagePath[i]);
                values.put("description", description[i]);
                db.insert(AppProperties.FOOD_TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            db.endTransaction();

        }
        return true;
    }

    public List<FoodInfo> queryFoodList(String table) {
        List<FoodInfo> foodList = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(table, null, null, null, null, null, null, null);
        if (cursor!= null && cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                FoodInfo foodInfo = new FoodInfo();
                foodInfo.setShicai(cursor.getString(1));
                foodInfo.setKcal(cursor.getString(2));
                foodInfo.setTanshui(cursor.getString(3));
                foodInfo.setDanbai(cursor.getString(4));
                foodInfo.setZhifang(cursor.getString(5));
                foodInfo.setFoodImagePath(cursor.getString(6));
                foodInfo.setDescription(cursor.getString(7));
                foodList.add(foodInfo);
            }
        }
        cursor.close();
        db.close();
        return foodList;
    }


}
