* 目前的线索是：
宁波通
1.com.ztesoft.nbt.apps.bus.BusQuery_LiveBus中的519行
调用了 JSONArray M = u.a().M(str);
对获得的数据进行了解析
2.com.ztesoft.nbt.common.u中的1178行
调用了 JSONObject jSONObject = new JSONObject(O(str));
进行解密
3.O(str)在通文件中的1224行
其中1247行使用了return new String(DESUtile.a(Base64.decode(new JSONObject(str).getString("CRYPTOGRAPH").getBytes("utf-8"), 0), a.a()), "utf-8");
其中com.ztesoft.nbt.apps.util.DESUtile就是解密工具
a.a()是com.ztesoft.nbt.apps.b.a中的方法，url也都在这个文件中
4.0 com.ztesoft.nbt.apps.util.DESUtile的72行就是需要的解密方法
5.9 DESUtile中str.getBytes(e.f);里的e.f是com.umeng.common.util.e中的f("utf-8")
解密成功：
{"VEHICLE_POS":[{"TIME":1476191369956,"STRANK":22,"CAR_ID":1910,"STATION_NAME":"联丰路丽园南路口(送子鸟医院)","CUR_STATION_ID":161,"FLAG":"1","POSITION":1,"TIMEN":1476191369956},{"TIME":1476191368358,"STRANK":7,"CAR_ID":157,"STATION_NAME":"金家漕","CUR_STATION_ID":32384,"FLAG":"1","POSITION":0,"TIMEN":1476191368358},{"TIME":1476191363512,"STRANK":28,"CAR_ID":390,"STATION_NAME":"关爱小区","CUR_STATION_ID":1459,"FLAG":"1","POSITION":1,"TIMEN":1476191363512},{"TIME":1476191368433,"STRANK":16,"CAR_ID":162,"STATION_NAME":"药行街(开明街)","CUR_STATION_ID":1624,"FLAG":"1","POSITION":1,"TIMEN":1476191368433}]}

宁波无线公交
在LineSearchActivity中的第344行getLineList方法进行站点的搜索请求
使用 CipherTool.getCipherString()方法进行了加密


