package com.tikt.goldenplatform.bean;

import java.util.List;

/**
 * Created by TikT on 2016/10/13.
 */

public class BusCryptographEntity {

    /**
     * TIME : 1476191369956
     * STRANK : 22
     * CAR_ID : 1910
     * STATION_NAME : 联丰路丽园南路口(送子鸟医院)
     * CUR_STATION_ID : 161
     * FLAG : 1
     * POSITION : 1
     * TIMEN : 1476191369956
     */

    private List<VEHICLEPOSBean> VEHICLE_POS;

    public List<VEHICLEPOSBean> getVEHICLE_POS() {
        return VEHICLE_POS;
    }

    public void setVEHICLE_POS(List<VEHICLEPOSBean> VEHICLE_POS) {
        this.VEHICLE_POS = VEHICLE_POS;
    }

    public static class VEHICLEPOSBean {
        private long TIME;
        private int STRANK;
        private int CAR_ID;
        private String STATION_NAME;
        private int CUR_STATION_ID;
        private String FLAG;
        private int POSITION;
        private long TIMEN;

        public long getTIME() {
            return TIME;
        }

        public void setTIME(long TIME) {
            this.TIME = TIME;
        }

        public int getSTRANK() {
            return STRANK;
        }

        public void setSTRANK(int STRANK) {
            this.STRANK = STRANK;
        }

        public int getCAR_ID() {
            return CAR_ID;
        }

        public void setCAR_ID(int CAR_ID) {
            this.CAR_ID = CAR_ID;
        }

        public String getSTATION_NAME() {
            return STATION_NAME;
        }

        public void setSTATION_NAME(String STATION_NAME) {
            this.STATION_NAME = STATION_NAME;
        }

        public int getCUR_STATION_ID() {
            return CUR_STATION_ID;
        }

        public void setCUR_STATION_ID(int CUR_STATION_ID) {
            this.CUR_STATION_ID = CUR_STATION_ID;
        }

        public String getFLAG() {
            return FLAG;
        }

        public void setFLAG(String FLAG) {
            this.FLAG = FLAG;
        }

        public int getPOSITION() {
            return POSITION;
        }

        public void setPOSITION(int POSITION) {
            this.POSITION = POSITION;
        }

        public long getTIMEN() {
            return TIMEN;
        }

        public void setTIMEN(long TIMEN) {
            this.TIMEN = TIMEN;
        }
    }
}
