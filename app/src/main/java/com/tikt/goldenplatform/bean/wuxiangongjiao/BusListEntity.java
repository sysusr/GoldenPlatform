package com.tikt.goldenplatform.bean.wuxiangongjiao;

import java.util.List;

/**
 * Created by TikT on 2016/12/22.
 * 查询某辆车的行驶位置
 */

public class BusListEntity {

    /**
     * errorMessage : 获取成功！
     * busInfoMapList : [{"LAT":29.891355,"NEXT_DISTANCE":684,"TASK_STATUS":0,"LON":121.612331666667,"SPEED":0,"BUS_CORP_ID":22336,"RN":1,"NEXT_SN":30,"BUS_PATH_ID":26846100,"FLAG":0,"VEHICLE_ID":583},{"LAT":29.8880683333333,"NEXT_DISTANCE":83,"TASK_STATUS":0,"LON":121.612711666667,"SPEED":19.8,"BUS_CORP_ID":22336,"RN":2,"NEXT_SN":7,"BUS_PATH_ID":26846100,"FLAG":0,"VEHICLE_ID":3350}]
     * errorCode : 0
     * leaveTimeMap : {"TASK_STATUS":"0","START_TIME":"11:05","BUS_CORP_ID":"22336","BUS_PATH_ID":"26846100"}
     */

    private String errorMessage;
    private int errorCode;
    private LeaveTimeMapBean leaveTimeMap;
    private List<BusInfoMapListBean> busInfoMapList;

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

    public LeaveTimeMapBean getLeaveTimeMap() {
        return leaveTimeMap;
    }

    public void setLeaveTimeMap(LeaveTimeMapBean leaveTimeMap) {
        this.leaveTimeMap = leaveTimeMap;
    }

    public List<BusInfoMapListBean> getBusInfoMapList() {
        return busInfoMapList;
    }

    public void setBusInfoMapList(List<BusInfoMapListBean> busInfoMapList) {
        this.busInfoMapList = busInfoMapList;
    }

    public static class LeaveTimeMapBean {
        /**
         * TASK_STATUS : 0
         * START_TIME : 11:05
         * BUS_CORP_ID : 22336
         * BUS_PATH_ID : 26846100
         */

        private String TASK_STATUS;
        private String START_TIME;
        private String BUS_CORP_ID;
        private String BUS_PATH_ID;

        public String getTASK_STATUS() {
            return TASK_STATUS;
        }

        public void setTASK_STATUS(String TASK_STATUS) {
            this.TASK_STATUS = TASK_STATUS;
        }

        public String getSTART_TIME() {
            return START_TIME;
        }

        public void setSTART_TIME(String START_TIME) {
            this.START_TIME = START_TIME;
        }

        public String getBUS_CORP_ID() {
            return BUS_CORP_ID;
        }

        public void setBUS_CORP_ID(String BUS_CORP_ID) {
            this.BUS_CORP_ID = BUS_CORP_ID;
        }

        public String getBUS_PATH_ID() {
            return BUS_PATH_ID;
        }

        public void setBUS_PATH_ID(String BUS_PATH_ID) {
            this.BUS_PATH_ID = BUS_PATH_ID;
        }
    }

    public static class BusInfoMapListBean {
        /**
         * LAT : 29.891355
         * NEXT_DISTANCE : 684
         * TASK_STATUS : 0
         * LON : 121.612331666667
         * SPEED : 0
         * BUS_CORP_ID : 22336
         * RN : 1
         * NEXT_SN : 30
         * BUS_PATH_ID : 26846100
         * FLAG : 0
         * VEHICLE_ID : 583
         */

        private double LAT;
        private int NEXT_DISTANCE;
        private int TASK_STATUS;
        private double LON;
        private int SPEED;
        private int BUS_CORP_ID;
        private int RN;
        private int NEXT_SN;
        private int BUS_PATH_ID;
        private int FLAG;
        private int VEHICLE_ID;

        public double getLAT() {
            return LAT;
        }

        public void setLAT(double LAT) {
            this.LAT = LAT;
        }

        public int getNEXT_DISTANCE() {
            return NEXT_DISTANCE;
        }

        public void setNEXT_DISTANCE(int NEXT_DISTANCE) {
            this.NEXT_DISTANCE = NEXT_DISTANCE;
        }

        public int getTASK_STATUS() {
            return TASK_STATUS;
        }

        public void setTASK_STATUS(int TASK_STATUS) {
            this.TASK_STATUS = TASK_STATUS;
        }

        public double getLON() {
            return LON;
        }

        public void setLON(double LON) {
            this.LON = LON;
        }

        public int getSPEED() {
            return SPEED;
        }

        public void setSPEED(int SPEED) {
            this.SPEED = SPEED;
        }

        public int getBUS_CORP_ID() {
            return BUS_CORP_ID;
        }

        public void setBUS_CORP_ID(int BUS_CORP_ID) {
            this.BUS_CORP_ID = BUS_CORP_ID;
        }

        public int getRN() {
            return RN;
        }

        public void setRN(int RN) {
            this.RN = RN;
        }

        public int getNEXT_SN() {
            return NEXT_SN;
        }

        public void setNEXT_SN(int NEXT_SN) {
            this.NEXT_SN = NEXT_SN;
        }

        public int getBUS_PATH_ID() {
            return BUS_PATH_ID;
        }

        public void setBUS_PATH_ID(int BUS_PATH_ID) {
            this.BUS_PATH_ID = BUS_PATH_ID;
        }

        public int getFLAG() {
            return FLAG;
        }

        public void setFLAG(int FLAG) {
            this.FLAG = FLAG;
        }

        public int getVEHICLE_ID() {
            return VEHICLE_ID;
        }

        public void setVEHICLE_ID(int VEHICLE_ID) {
            this.VEHICLE_ID = VEHICLE_ID;
        }
    }
}
