package com.tikt.goldenplatform.bean;

import java.util.List;

/**
 * Created by TikT on 2016/10/14.
 */

public class Ent {

    /**
     * BUS_LINE_ID : 3230
     * STATIONS : [{"GEO_LAT":"29.901073","STRANK":"0","STATION_ID":"2155","GEO_LON":"121.606493","STATION_NAME":"公交清水桥站","BC_STATION_ID":"41152414","BC_ID":"22336"},{"GEO_LAT":"29.89865","STRANK":"1","STATION_ID":"9","GEO_LON":"121.606433","STATION_NAME":"宾果公寓","BC_STATION_ID":"950","BC_ID":"22336"},{"GEO_LAT":"29.895972","STRANK":"2","STATION_ID":"10","GEO_LON":"121.606343","STATION_NAME":"清水桥菜市场","BC_STATION_ID":"951","BC_ID":"22336"},{"GEO_LAT":"29.894366","STRANK":"3","STATION_ID":"11","GEO_LON":"121.608173","STATION_NAME":"丹桂路丁香路口","BC_STATION_ID":"952","BC_ID":"22336"},{"GEO_LAT":"29.893817","STRANK":"4","STATION_ID":"6","GEO_LON":"121.611345","STATION_NAME":"明辰紫月","BC_STATION_ID":"947","BC_ID":"22336"},{"GEO_LAT":"29.890273","STRANK":"5","STATION_ID":"1436","GEO_LON":"121.61211","STATION_NAME":"杨木碶路江南路口","BC_STATION_ID":"26848028","BC_ID":"22336"}]
     */

    private BUSLINEBean BUS_LINE;

    public BUSLINEBean getBUS_LINE() {
        return BUS_LINE;
    }

    public void setBUS_LINE(BUSLINEBean BUS_LINE) {
        this.BUS_LINE = BUS_LINE;
    }

    public static class BUSLINEBean {
        private String BUS_LINE_ID;
        /**
         * GEO_LAT : 29.901073
         * STRANK : 0
         * STATION_ID : 2155
         * GEO_LON : 121.606493
         * STATION_NAME : 公交清水桥站
         * BC_STATION_ID : 41152414
         * BC_ID : 22336
         */

        private List<STATIONSBean> STATIONS;

        public String getBUS_LINE_ID() {
            return BUS_LINE_ID;
        }

        public void setBUS_LINE_ID(String BUS_LINE_ID) {
            this.BUS_LINE_ID = BUS_LINE_ID;
        }

        public List<STATIONSBean> getSTATIONS() {
            return STATIONS;
        }

        public void setSTATIONS(List<STATIONSBean> STATIONS) {
            this.STATIONS = STATIONS;
        }

        public static class STATIONSBean {
            private String GEO_LAT;
            private String STRANK;
            private String STATION_ID;
            private String GEO_LON;
            private String STATION_NAME;
            private String BC_STATION_ID;
            private String BC_ID;

            public String getGEO_LAT() {
                return GEO_LAT;
            }

            public void setGEO_LAT(String GEO_LAT) {
                this.GEO_LAT = GEO_LAT;
            }

            public String getSTRANK() {
                return STRANK;
            }

            public void setSTRANK(String STRANK) {
                this.STRANK = STRANK;
            }

            public String getSTATION_ID() {
                return STATION_ID;
            }

            public void setSTATION_ID(String STATION_ID) {
                this.STATION_ID = STATION_ID;
            }

            public String getGEO_LON() {
                return GEO_LON;
            }

            public void setGEO_LON(String GEO_LON) {
                this.GEO_LON = GEO_LON;
            }

            public String getSTATION_NAME() {
                return STATION_NAME;
            }

            public void setSTATION_NAME(String STATION_NAME) {
                this.STATION_NAME = STATION_NAME;
            }

            public String getBC_STATION_ID() {
                return BC_STATION_ID;
            }

            public void setBC_STATION_ID(String BC_STATION_ID) {
                this.BC_STATION_ID = BC_STATION_ID;
            }

            public String getBC_ID() {
                return BC_ID;
            }

            public void setBC_ID(String BC_ID) {
                this.BC_ID = BC_ID;
            }
        }
    }
}
