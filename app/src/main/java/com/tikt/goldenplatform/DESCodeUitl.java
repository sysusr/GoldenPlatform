package com.tikt.goldenplatform;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by TikT on 2016/10/13.
 * des解密
 */

public  class DESCodeUitl {

    private static byte[] spec = new byte[8];


    /**
     * 解密
     * @param bArr
     * @param str
     * @return
     * @throws Exception
     */
    public static byte[] decode(byte[] bArr, String str) throws Exception {
        Key key = getKey(stringToBytes(str));
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        spec = stringToBytesWithChar("12345678");
        instance.init(2, key, new IvParameterSpec(spec));
        return instance.doFinal(bArr);
    }

    /**
     * 生成解密用的KEY
     *
     * @param bArr
     * @return
     * @throws Exception
     */
    private static Key getKey(byte[] bArr) throws Exception {
        return SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr));
    }

    /**
     * string转bytes utf-8
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static byte[] stringToBytesWithChar(String str) throws Exception {
        return str.getBytes("UTF-8");
    }

    /**
     * string转bytes
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static byte[] stringToBytes(String str) throws Exception {
        return str.getBytes();
    }
}
