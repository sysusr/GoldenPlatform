package com.tikt.goldenplatform.bean.wuxiangongjiao;

import java.util.List;

/**
 * Created by TikT on 2016/12/21.
 * 无线公交
 */

public class BusStationEntity {

    /**
     * errorMessage : 获取线路信息成功！
     * content : [{"END_BUS_STATION_NAME":"公交清水桥站","START_FIRST_TIME":"06:20","END_FIRST_TIME":"06:00","BUS_PATH_NAME":"509路","START_LAST_TIME":"18:50","END_LAST_TIME":"19:00","BUS_CORP_ID":22336,"START_BUS_STATION_NAME":"公交清水桥站","BUS_PATH_ID":26846100,"TYPE_ATTR":0},
     * {"END_BUS_STATION_NAME":"公交清水桥站","START_FIRST_TIME":"06:20","END_FIRST_TIME":"06:00","BUS_PATH_NAME":"509路","START_LAST_TIME":"18:50","END_LAST_TIME":"19:00","BUS_CORP_ID":22336,"START_BUS_STATION_NAME":"公交清水桥站","BUS_PATH_ID":26846100,"TYPE_ATTR":1}]
     * errorCode : 0
     */

    private String errorMessage;
    private int errorCode;
    private List<ContentBean> content;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * END_BUS_STATION_NAME : 公交清水桥站
         * START_FIRST_TIME : 06:20
         * END_FIRST_TIME : 06:00
         * BUS_PATH_NAME : 509路
         * START_LAST_TIME : 18:50
         * END_LAST_TIME : 19:00
         * BUS_CORP_ID : 22336
         * START_BUS_STATION_NAME : 公交清水桥站
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
}
