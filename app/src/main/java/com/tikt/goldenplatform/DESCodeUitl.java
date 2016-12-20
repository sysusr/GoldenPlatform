package com.tikt.goldenplatform;

import java.io.UnsupportedEncodingException;

/**
 * Created by TikT on 2016/10/13.
 * 用于http://Weixin1.nbbus.com:8080/NingboBusWebservice/resources/接口的
 * des解密
 */

 public class DESCodeUitl {

    /**
     * 加密
     * @param source
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getCipherString(String source) throws UnsupportedEncodingException {
        if (source.trim().equals("")) {
            return "";
        }
        byte[] sb = new String(source.getBytes("UTF-8"), "UTF-8").getBytes("UTF-8");
        byte[] sbNew = new byte[sb.length];
        StringBuilder sbb = new StringBuilder();
        for (int i = 0; i < sb.length; i++) {
            byte t = byteEncryption(sb[i]);
            sbNew[i] = t;
            sbb.append((char) t);
        }
        return sbb.toString();
    }
    public static byte byteEncryption(byte nSrc) {
        byte nDst = (byte) 0;
        for (int i = 0; i < 8; i++) {
            if ((nSrc & ((byte) (1 << EN_KEY[i]))) != 0) {
                nDst = (byte) ((1 << i) | nDst);
            }
        }
        return nDst;
    }

    /**
     * 解密
     * @param cipherString
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getOriginString(String cipherString) throws UnsupportedEncodingException {
        if (cipherString.trim().equals("")) {
            return "";
        }
        String drr = cipherString;
        byte[] drrByte = new byte[drr.length()];
        for (int i = 0; i < drrByte.length; i++) {
            drrByte[i] = byteDecryption(Byte.valueOf((byte) drr.charAt(i)).byteValue());
        }
        return new String(drrByte, "UTF-8");
    }


    public static byte byteDecryption(byte nSrc) {
        byte nDst = (byte) 0;
        for (int i = 0; i < 8; i++) {
            if ((nSrc & ((byte) (1 << DE_KEY[i]))) != 0) {
                nDst = (byte) ((1 << i) | nDst);
            }
        }
        return nDst;
    }

    public static int[] DE_KEY;
    public static int[] EN_KEY;

    static {
        int[] arrayOfInt1 = new int[8];
        arrayOfInt1[0] = 7;
        arrayOfInt1[1] = 2;
        arrayOfInt1[2] = 5;
        arrayOfInt1[3] = 4;
        arrayOfInt1[5] = 1;
        arrayOfInt1[6] = 3;
        arrayOfInt1[7] = 6;
        EN_KEY = arrayOfInt1;
        int[] arrayOfInt2 = new int[8];
        arrayOfInt2[0] = 4;
        arrayOfInt2[1] = 5;
        arrayOfInt2[2] = 1;
        arrayOfInt2[3] = 6;
        arrayOfInt2[4] = 3;
        arrayOfInt2[5] = 2;
        arrayOfInt2[6] = 7;
        DE_KEY = arrayOfInt2;
    }
}
