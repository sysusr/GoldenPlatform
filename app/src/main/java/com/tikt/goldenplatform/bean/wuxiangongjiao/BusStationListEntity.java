package com.tikt.goldenplatform.bean.wuxiangongjiao;

import java.util.List;

/**
 * Created by TikT on 2016/12/22.
 * 查询某条公交的线路上经过的站
 */

public class BusStationListEntity {

    /**
     * errorMessage : 获取线路站点信息成功！
     * content : {"lineInfoMap":{"END_BUS_STATION_NAME":"公交清�\u0014�桥站","START_FIRST_TIME":"06:20","END_FIRST_TIME":"06:00","BUS_PATH_NAME":"509路","START_LAST_TIME":"18:50","END_LAST_TIME":"19:00","BUS_CORP_ID":22336,"START_BUS_STATION_NAME":"公交清�\u0014�桥站","BUS_PATH_ID":26846100,"TYPE_ATTR":0},"lineDepartureTimeInfoMap":{"START_FIRST_TIME":"06:20","END_FIRST_TIME":"06:00","START_LAST_TIME":"18:50","END_LAST_TIME":"19:00","BUS_CORP_ID":22336,"BUS_PATH_ID":26846100},"stationList":[{"CENTER_LAT":29.901073,"CENTER_LON":121.606493,"BUS_STATION_NAME":"公交清�\u0014�桥站","SN":1,"BUS_CORP_ID":22336,"BUS_STATION_ID":41152414,"STATION_FLAG":1},{"CENTER_LAT":29.89865,"CENTER_LON":121.606433,"BUS_STATION_NAME":"宾果公寓","SN":2,"BUS_CORP_ID":22336,"BUS_STATION_ID":950,"STATION_FLAG":1},{"CENTER_LAT":29.895972,"CENTER_LON":121.606343,"BUS_STATION_NAME":"清�\u0014�桥菜市场","SN":3,"BUS_CORP_ID":22336,"BUS_STATION_ID":951,"STATION_FLAG":1},{"CENTER_LAT":29.894366,"CENTER_LON":121.608173,"BUS_STATION_NAME":"丹桂路丁香路口","SN":4,"BUS_CORP_ID":22336,"BUS_STATION_ID":952,"STATION_FLAG":1},{"CENTER_LAT":29.893817,"CENTER_LON":121.611345,"BUS_STATION_NAME":"明�\u0014紫月","SN":5,"BUS_CORP_ID":22336,"BUS_STATION_ID":947,"STATION_FLAG":1},{"CENTER_LAT":29.890273,"CENTER_LON":121.61211,"BUS_STATION_NAME":"杨木碶路江南路口","SN":6,"BUS_CORP_ID":22336,"BUS_STATION_ID":26848028,"STATION_FLAG":1},{"CENTER_LAT":29.887335,"CENTER_LON":121.612908,"BUS_STATION_NAME":"科兴路","SN":7,"BUS_CORP_ID":22336,"BUS_STATION_ID":1931,"STATION_FLAG":1},{"CENTER_LAT":29.883662,"CENTER_LON":121.614613,"BUS_STATION_NAME":"�\u0014晖路","SN":8,"BUS_CORP_ID":22336,"BUS_STATION_ID":906,"STATION_FLAG":1},{"CENTER_LAT":29.881372,"CENTER_LON":121.617,"BUS_STATION_NAME":"三维技术公司","SN":9,"BUS_CORP_ID":22336,"BUS_STATION_ID":905,"STATION_FLAG":1},{"CENTER_LAT":29.880102,"CENTER_LON":121.62074,"BUS_STATION_NAME":"扬帆路","SN":10,"BUS_CORP_ID":22336,"BUS_STATION_ID":909,"STATION_FLAG":1},{"CENTER_LAT":29.87996,"CENTER_LON":121.626069,"BUS_STATION_NAME":"科技公园","SN":11,"BUS_CORP_ID":22336,"BUS_STATION_ID":2632,"STATION_FLAG":1},{"CENTER_LAT":29.877523,"CENTER_LON":121.629742,"BUS_STATION_NAME":"创业大厦","SN":12,"BUS_CORP_ID":22336,"BUS_STATION_ID":902,"STATION_FLAG":1},{"CENTER_LAT":29.879317,"CENTER_LON":121.635529,"BUS_STATION_NAME":"兵科院","SN":13,"BUS_CORP_ID":22336,"BUS_STATION_ID":912,"STATION_FLAG":1},{"CENTER_LAT":29.886298,"CENTER_LON":121.63472,"BUS_STATION_NAME":"宁波研发园北","SN":14,"BUS_CORP_ID":22336,"BUS_STATION_ID":26846140,"STATION_FLAG":1},{"CENTER_LAT":29.885875,"CENTER_LON":121.640297,"BUS_STATION_NAME":"冬青路","SN":15,"BUS_CORP_ID":22336,"BUS_STATION_ID":26846138,"STATION_FLAG":1},{"CENTER_LAT":29.886359,"CENTER_LON":121.651729,"BUS_STATION_NAME":"�\u0014梅路","SN":16,"BUS_CORP_ID":22336,"BUS_STATION_ID":2641,"STATION_FLAG":1},{"CENTER_LAT":29.888663,"CENTER_LON":121.654875,"BUS_STATION_NAME":"信懋学校","SN":17,"BUS_CORP_ID":22336,"BUS_STATION_ID":2642,"STATION_FLAG":1},{"CENTER_LAT":29.891928,"CENTER_LON":121.656283,"BUS_STATION_NAME":"梅景路","SN":18,"BUS_CORP_ID":22336,"BUS_STATION_ID":2643,"STATION_FLAG":1},{"CENTER_LAT":29.89659,"CENTER_LON":121.65656,"BUS_STATION_NAME":"梅墟�\u0014城","SN":19,"BUS_CORP_ID":22336,"BUS_STATION_ID":40868478,"STATION_FLAG":1},{"CENTER_LAT":29.899637,"CENTER_LON":121.649813,"BUS_STATION_NAME":"梅墟路","SN":20,"BUS_CORP_ID":22336,"BUS_STATION_ID":41009882,"STATION_FLAG":1},{"CENTER_LAT":29.90161,"CENTER_LON":121.648603,"BUS_STATION_NAME":"梅墟街道","SN":21,"BUS_CORP_ID":22336,"BUS_STATION_ID":2225,"STATION_FLAG":1},{"CENTER_LAT":29.895927,"CENTER_LON":121.649673,"BUS_STATION_NAME":"梅墟","SN":22,"BUS_CORP_ID":22336,"BUS_STATION_ID":2682,"STATION_FLAG":1},{"CENTER_LAT":29.893538,"CENTER_LON":121.6449,"BUS_STATION_NAME":"江南路�\u0014梅路口","SN":23,"BUS_CORP_ID":22336,"BUS_STATION_ID":865,"STATION_FLAG":1},{"CENTER_LAT":29.891502,"CENTER_LON":121.639983,"BUS_STATION_NAME":"江南路剑�\u0014路口","SN":24,"BUS_CORP_ID":22336,"BUS_STATION_ID":895,"STATION_FLAG":1},{"CENTER_LAT":29.891208,"CENTER_LON":121.63637,"BUS_STATION_NAME":"江南路冬青路口","SN":25,"BUS_CORP_ID":22336,"BUS_STATION_ID":896,"STATION_FLAG":1},{"CENTER_LAT":29.891203,"CENTER_LON":121.628387,"BUS_STATION_NAME":"江南路聚贤路口","SN":26,"BUS_CORP_ID":22336,"BUS_STATION_ID":2692,"STATION_FLAG":1},{"CENTER_LAT":29.88919,"CENTER_LON":121.62387,"BUS_STATION_NAME":"皇冠花园","SN":27,"BUS_CORP_ID":22336,"BUS_STATION_ID":26877366,"STATION_FLAG":1},{"CENTER_LAT":29.887793,"CENTER_LON":121.61882,"BUS_STATION_NAME":"中石化宁波工程公司","SN":28,"BUS_CORP_ID":22336,"BUS_STATION_ID":26846136,"STATION_FLAG":1},{"CENTER_LAT":29.89131,"CENTER_LON":121.615558,"BUS_STATION_NAME":"颐乐园","SN":29,"BUS_CORP_ID":22336,"BUS_STATION_ID":2693,"STATION_FLAG":1},{"CENTER_LAT":29.897343,"CENTER_LON":121.610592,"BUS_STATION_NAME":"中宁花园(锦诚花园)","SN":30,"BUS_CORP_ID":22336,"BUS_STATION_ID":961,"STATION_FLAG":1},{"CENTER_LAT":29.900337,"CENTER_LON":121.60968,"BUS_STATION_NAME":"滨江花苑","SN":31,"BUS_CORP_ID":22336,"BUS_STATION_ID":960,"STATION_FLAG":1},{"CENTER_LAT":29.901073,"CENTER_LON":121.606493,"BUS_STATION_NAME":"公交清�\u0014�桥站","SN":32,"BUS_CORP_ID":22336,"BUS_STATION_ID":41152414,"STATION_FLAG":1}]}
     * errorCode : 0
     */

    private String errorMessage;
    private ContentBean content;
    private int errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public static class ContentBean {
        /**
         * lineInfoMap : {"END_BUS_STATION_NAME":"公交清�\u0014�桥站","START_FIRST_TIME":"06:20","END_FIRST_TIME":"06:00","BUS_PATH_NAME":"509路","START_LAST_TIME":"18:50","END_LAST_TIME":"19:00","BUS_CORP_ID":22336,"START_BUS_STATION_NAME":"公交清�\u0014�桥站","BUS_PATH_ID":26846100,"TYPE_ATTR":0}
         * lineDepartureTimeInfoMap : {"START_FIRST_TIME":"06:20","END_FIRST_TIME":"06:00","START_LAST_TIME":"18:50","END_LAST_TIME":"19:00","BUS_CORP_ID":22336,"BUS_PATH_ID":26846100}
         * stationList : [{"CENTER_LAT":29.901073,"CENTER_LON":121.606493,"BUS_STATION_NAME":"公交清�\u0014�桥站","SN":1,"BUS_CORP_ID":22336,"BUS_STATION_ID":41152414,"STATION_FLAG":1},{"CENTER_LAT":29.89865,"CENTER_LON":121.606433,"BUS_STATION_NAME":"宾果公寓","SN":2,"BUS_CORP_ID":22336,"BUS_STATION_ID":950,"STATION_FLAG":1},{"CENTER_LAT":29.895972,"CENTER_LON":121.606343,"BUS_STATION_NAME":"清�\u0014�桥菜市场","SN":3,"BUS_CORP_ID":22336,"BUS_STATION_ID":951,"STATION_FLAG":1},{"CENTER_LAT":29.894366,"CENTER_LON":121.608173,"BUS_STATION_NAME":"丹桂路丁香路口","SN":4,"BUS_CORP_ID":22336,"BUS_STATION_ID":952,"STATION_FLAG":1},{"CENTER_LAT":29.893817,"CENTER_LON":121.611345,"BUS_STATION_NAME":"明�\u0014紫月","SN":5,"BUS_CORP_ID":22336,"BUS_STATION_ID":947,"STATION_FLAG":1},{"CENTER_LAT":29.890273,"CENTER_LON":121.61211,"BUS_STATION_NAME":"杨木碶路江南路口","SN":6,"BUS_CORP_ID":22336,"BUS_STATION_ID":26848028,"STATION_FLAG":1},{"CENTER_LAT":29.887335,"CENTER_LON":121.612908,"BUS_STATION_NAME":"科兴路","SN":7,"BUS_CORP_ID":22336,"BUS_STATION_ID":1931,"STATION_FLAG":1},{"CENTER_LAT":29.883662,"CENTER_LON":121.614613,"BUS_STATION_NAME":"�\u0014晖路","SN":8,"BUS_CORP_ID":22336,"BUS_STATION_ID":906,"STATION_FLAG":1},{"CENTER_LAT":29.881372,"CENTER_LON":121.617,"BUS_STATION_NAME":"三维技术公司","SN":9,"BUS_CORP_ID":22336,"BUS_STATION_ID":905,"STATION_FLAG":1},{"CENTER_LAT":29.880102,"CENTER_LON":121.62074,"BUS_STATION_NAME":"扬帆路","SN":10,"BUS_CORP_ID":22336,"BUS_STATION_ID":909,"STATION_FLAG":1},{"CENTER_LAT":29.87996,"CENTER_LON":121.626069,"BUS_STATION_NAME":"科技公园","SN":11,"BUS_CORP_ID":22336,"BUS_STATION_ID":2632,"STATION_FLAG":1},{"CENTER_LAT":29.877523,"CENTER_LON":121.629742,"BUS_STATION_NAME":"创业大厦","SN":12,"BUS_CORP_ID":22336,"BUS_STATION_ID":902,"STATION_FLAG":1},{"CENTER_LAT":29.879317,"CENTER_LON":121.635529,"BUS_STATION_NAME":"兵科院","SN":13,"BUS_CORP_ID":22336,"BUS_STATION_ID":912,"STATION_FLAG":1},{"CENTER_LAT":29.886298,"CENTER_LON":121.63472,"BUS_STATION_NAME":"宁波研发园北","SN":14,"BUS_CORP_ID":22336,"BUS_STATION_ID":26846140,"STATION_FLAG":1},{"CENTER_LAT":29.885875,"CENTER_LON":121.640297,"BUS_STATION_NAME":"冬青路","SN":15,"BUS_CORP_ID":22336,"BUS_STATION_ID":26846138,"STATION_FLAG":1},{"CENTER_LAT":29.886359,"CENTER_LON":121.651729,"BUS_STATION_NAME":"�\u0014梅路","SN":16,"BUS_CORP_ID":22336,"BUS_STATION_ID":2641,"STATION_FLAG":1},{"CENTER_LAT":29.888663,"CENTER_LON":121.654875,"BUS_STATION_NAME":"信懋学校","SN":17,"BUS_CORP_ID":22336,"BUS_STATION_ID":2642,"STATION_FLAG":1},{"CENTER_LAT":29.891928,"CENTER_LON":121.656283,"BUS_STATION_NAME":"梅景路","SN":18,"BUS_CORP_ID":22336,"BUS_STATION_ID":2643,"STATION_FLAG":1},{"CENTER_LAT":29.89659,"CENTER_LON":121.65656,"BUS_STATION_NAME":"梅墟�\u0014城","SN":19,"BUS_CORP_ID":22336,"BUS_STATION_ID":40868478,"STATION_FLAG":1},{"CENTER_LAT":29.899637,"CENTER_LON":121.649813,"BUS_STATION_NAME":"梅墟路","SN":20,"BUS_CORP_ID":22336,"BUS_STATION_ID":41009882,"STATION_FLAG":1},{"CENTER_LAT":29.90161,"CENTER_LON":121.648603,"BUS_STATION_NAME":"梅墟街道","SN":21,"BUS_CORP_ID":22336,"BUS_STATION_ID":2225,"STATION_FLAG":1},{"CENTER_LAT":29.895927,"CENTER_LON":121.649673,"BUS_STATION_NAME":"梅墟","SN":22,"BUS_CORP_ID":22336,"BUS_STATION_ID":2682,"STATION_FLAG":1},{"CENTER_LAT":29.893538,"CENTER_LON":121.6449,"BUS_STATION_NAME":"江南路�\u0014梅路口","SN":23,"BUS_CORP_ID":22336,"BUS_STATION_ID":865,"STATION_FLAG":1},{"CENTER_LAT":29.891502,"CENTER_LON":121.639983,"BUS_STATION_NAME":"江南路剑�\u0014路口","SN":24,"BUS_CORP_ID":22336,"BUS_STATION_ID":895,"STATION_FLAG":1},{"CENTER_LAT":29.891208,"CENTER_LON":121.63637,"BUS_STATION_NAME":"江南路冬青路口","SN":25,"BUS_CORP_ID":22336,"BUS_STATION_ID":896,"STATION_FLAG":1},{"CENTER_LAT":29.891203,"CENTER_LON":121.628387,"BUS_STATION_NAME":"江南路聚贤路口","SN":26,"BUS_CORP_ID":22336,"BUS_STATION_ID":2692,"STATION_FLAG":1},{"CENTER_LAT":29.88919,"CENTER_LON":121.62387,"BUS_STATION_NAME":"皇冠花园","SN":27,"BUS_CORP_ID":22336,"BUS_STATION_ID":26877366,"STATION_FLAG":1},{"CENTER_LAT":29.887793,"CENTER_LON":121.61882,"BUS_STATION_NAME":"中石化宁波工程公司","SN":28,"BUS_CORP_ID":22336,"BUS_STATION_ID":26846136,"STATION_FLAG":1},{"CENTER_LAT":29.89131,"CENTER_LON":121.615558,"BUS_STATION_NAME":"颐乐园","SN":29,"BUS_CORP_ID":22336,"BUS_STATION_ID":2693,"STATION_FLAG":1},{"CENTER_LAT":29.897343,"CENTER_LON":121.610592,"BUS_STATION_NAME":"中宁花园(锦诚花园)","SN":30,"BUS_CORP_ID":22336,"BUS_STATION_ID":961,"STATION_FLAG":1},{"CENTER_LAT":29.900337,"CENTER_LON":121.60968,"BUS_STATION_NAME":"滨江花苑","SN":31,"BUS_CORP_ID":22336,"BUS_STATION_ID":960,"STATION_FLAG":1},{"CENTER_LAT":29.901073,"CENTER_LON":121.606493,"BUS_STATION_NAME":"公交清�\u0014�桥站","SN":32,"BUS_CORP_ID":22336,"BUS_STATION_ID":41152414,"STATION_FLAG":1}]
         */

        private LineInfoMapBean lineInfoMap;
        private LineDepartureTimeInfoMapBean lineDepartureTimeInfoMap;
        private List<StationListBean> stationList;

        public LineInfoMapBean getLineInfoMap() {
            return lineInfoMap;
        }

        public void setLineInfoMap(LineInfoMapBean lineInfoMap) {
            this.lineInfoMap = lineInfoMap;
        }

        public LineDepartureTimeInfoMapBean getLineDepartureTimeInfoMap() {
            return lineDepartureTimeInfoMap;
        }

        public void setLineDepartureTimeInfoMap(LineDepartureTimeInfoMapBean lineDepartureTimeInfoMap) {
            this.lineDepartureTimeInfoMap = lineDepartureTimeInfoMap;
        }

        public List<StationListBean> getStationList() {
            return stationList;
        }

        public void setStationList(List<StationListBean> stationList) {
            this.stationList = stationList;
        }

        public static class LineInfoMapBean {
            /**
             * END_BUS_STATION_NAME : 公交清��桥站
             * START_FIRST_TIME : 06:20
             * END_FIRST_TIME : 06:00
             * BUS_PATH_NAME : 509路
             * START_LAST_TIME : 18:50
             * END_LAST_TIME : 19:00
             * BUS_CORP_ID : 22336
             * START_BUS_STATION_NAME : 公交清��桥站
             * BUS_PATH_ID : 26846100
             * TYPE_ATTR : 0
             */

            private String END_BUS_STATION_NAME;
            private String START_FIRST_TIME;
            private String END_FIRST_TIME;
            private String BUS_PATH_NAME;
            private String START_LAST_TIME;
            private String END_LAST_TIME;
            private int BUS_CORP_ID;
            private String START_BUS_STATION_NAME;
            private int BUS_PATH_ID;
            private int TYPE_ATTR;

            public String getEND_BUS_STATION_NAME() {
                return END_BUS_STATION_NAME;
            }

            public void setEND_BUS_STATION_NAME(String END_BUS_STATION_NAME) {
                this.END_BUS_STATION_NAME = END_BUS_STATION_NAME;
            }

            public String getSTART_FIRST_TIME() {
                return START_FIRST_TIME;
            }

            public void setSTART_FIRST_TIME(String START_FIRST_TIME) {
                this.START_FIRST_TIME = START_FIRST_TIME;
            }

            public String getEND_FIRST_TIME() {
                return END_FIRST_TIME;
            }

            public void setEND_FIRST_TIME(String END_FIRST_TIME) {
                this.END_FIRST_TIME = END_FIRST_TIME;
            }

            public String getBUS_PATH_NAME() {
                return BUS_PATH_NAME;
            }

            public void setBUS_PATH_NAME(String BUS_PATH_NAME) {
                this.BUS_PATH_NAME = BUS_PATH_NAME;
            }

            public String getSTART_LAST_TIME() {
                return START_LAST_TIME;
            }

            public void setSTART_LAST_TIME(String START_LAST_TIME) {
                this.START_LAST_TIME = START_LAST_TIME;
            }

            public String getEND_LAST_TIME() {
                return END_LAST_TIME;
            }

            public void setEND_LAST_TIME(String END_LAST_TIME) {
                this.END_LAST_TIME = END_LAST_TIME;
            }

            public int getBUS_CORP_ID() {
                return BUS_CORP_ID;
            }

            public void setBUS_CORP_ID(int BUS_CORP_ID) {
                this.BUS_CORP_ID = BUS_CORP_ID;
            }

            public String getSTART_BUS_STATION_NAME() {
                return START_BUS_STATION_NAME;
            }

            public void setSTART_BUS_STATION_NAME(String START_BUS_STATION_NAME) {
                this.START_BUS_STATION_NAME = START_BUS_STATION_NAME;
            }

            public int getBUS_PATH_ID() {
                return BUS_PATH_ID;
            }

            public void setBUS_PATH_ID(int BUS_PATH_ID) {
                this.BUS_PATH_ID = BUS_PATH_ID;
            }

            public int getTYPE_ATTR() {
                return TYPE_ATTR;
            }

            public void setTYPE_ATTR(int TYPE_ATTR) {
                this.TYPE_ATTR = TYPE_ATTR;
            }
        }

        public static class LineDepartureTimeInfoMapBean {
            /**
             * START_FIRST_TIME : 06:20
             * END_FIRST_TIME : 06:00
             * START_LAST_TIME : 18:50
             * END_LAST_TIME : 19:00
             * BUS_CORP_ID : 22336
             * BUS_PATH_ID : 26846100
             */

            private String START_FIRST_TIME;
            private String END_FIRST_TIME;
            private String START_LAST_TIME;
            private String END_LAST_TIME;
            private int BUS_CORP_ID;
            private int BUS_PATH_ID;

            public String getSTART_FIRST_TIME() {
                return START_FIRST_TIME;
            }

            public void setSTART_FIRST_TIME(String START_FIRST_TIME) {
                this.START_FIRST_TIME = START_FIRST_TIME;
            }

            public String getEND_FIRST_TIME() {
                return END_FIRST_TIME;
            }

            public void setEND_FIRST_TIME(String END_FIRST_TIME) {
                this.END_FIRST_TIME = END_FIRST_TIME;
            }

            public String getSTART_LAST_TIME() {
                return START_LAST_TIME;
            }

            public void setSTART_LAST_TIME(String START_LAST_TIME) {
                this.START_LAST_TIME = START_LAST_TIME;
            }

            public String getEND_LAST_TIME() {
                return END_LAST_TIME;
            }

            public void setEND_LAST_TIME(String END_LAST_TIME) {
                this.END_LAST_TIME = END_LAST_TIME;
            }

            public int getBUS_CORP_ID() {
                return BUS_CORP_ID;
            }

            public void setBUS_CORP_ID(int BUS_CORP_ID) {
                this.BUS_CORP_ID = BUS_CORP_ID;
            }

            public int getBUS_PATH_ID() {
                return BUS_PATH_ID;
            }

            public void setBUS_PATH_ID(int BUS_PATH_ID) {
                this.BUS_PATH_ID = BUS_PATH_ID;
            }
        }

        public static class StationListBean {
            /**
             * CENTER_LAT : 29.901073
             * CENTER_LON : 121.606493
             * BUS_STATION_NAME : 公交清��桥站
             * SN : 1
             * BUS_CORP_ID : 22336
             * BUS_STATION_ID : 41152414
             * STATION_FLAG : 1
             */

            private double CENTER_LAT;
            private double CENTER_LON;
            private String BUS_STATION_NAME;
            private int SN;
            private int BUS_CORP_ID;
            private int BUS_STATION_ID;
            private int STATION_FLAG;

            public double getCENTER_LAT() {
                return CENTER_LAT;
            }

            public void setCENTER_LAT(double CENTER_LAT) {
                this.CENTER_LAT = CENTER_LAT;
            }

            public double getCENTER_LON() {
                return CENTER_LON;
            }

            public void setCENTER_LON(double CENTER_LON) {
                this.CENTER_LON = CENTER_LON;
            }

            public String getBUS_STATION_NAME() {
                return BUS_STATION_NAME;
            }

            public void setBUS_STATION_NAME(String BUS_STATION_NAME) {
                this.BUS_STATION_NAME = BUS_STATION_NAME;
            }

            public int getSN() {
                return SN;
            }

            public void setSN(int SN) {
                this.SN = SN;
            }

            public int getBUS_CORP_ID() {
                return BUS_CORP_ID;
            }

            public void setBUS_CORP_ID(int BUS_CORP_ID) {
                this.BUS_CORP_ID = BUS_CORP_ID;
            }

            public int getBUS_STATION_ID() {
                return BUS_STATION_ID;
            }

            public void setBUS_STATION_ID(int BUS_STATION_ID) {
                this.BUS_STATION_ID = BUS_STATION_ID;
            }

            public int getSTATION_FLAG() {
                return STATION_FLAG;
            }

            public void setSTATION_FLAG(int STATION_FLAG) {
                this.STATION_FLAG = STATION_FLAG;
            }
        }
    }
}
