package com.tikt.goldenplatform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.tikt.tools.DEScoder;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class MainActivity extends AppCompatActivity {

    String TAG = "TAG";
    String code = "jmH2nIwTc2uWfs2RpzmajmnahkZxCK8Bmp08QuACszWDR6VcQ3mxRlvQ6X" +
            "HYqEjmFr02V+huHyrTUzkvSAZvX8efy//lZzvMfuPKyzUACXs7eUSU1b1zvrbd" +
            "w91n4ePri1I76YaKNhy3Z/xYaSgE30o9AY6UKHg17efyWIBSW1y7lhKvAPet8I" +
            "Qcgt64K7xyWjUI3gIzeNK8j2xhOPfOAUXsDqXphTBuZf2bXW5HALNW3ChGXo0GC" +
            "djeHRTRLd8RpgoMea2jJlo7XGgVF6v70H/ul+/g9n3flqVxchZRPegwBqiQoZSL" +
            "xT9nudzM0RNYTJgqbtaUW8bhvrk+8qxOA8TWiTMZ5kUsEbi2AZyDOe26l9VwhxL0" +
            "NIfxaBctcxa04LuYHuotTqL72N2lHKBL1DZHdQnv/eyW2FFxQ2Sri1UxXFxoYHV3" +
            "+Ccq5aY9FvDZ6x/ghoRx+Rw/1jglr16P5wwyBG8JVucUIFHoAHZUgag1Xocg/5ct" +
            "ielAblG7LcS1H4L8XtZTKWudiFVErT1dnHWzZh2g9UQ/5YG2L6iIStPGueW+4sYo" +
            "sjgbuNlZwqVv3lFw32WCKCOb+dWn2Pv+sKm3mCgOY21YI2gsm46jNZVD5xhr6EUJ" +
            "JGiBXv6iT+arDmu+LOLmhdlgL8K/krEwNHaRQphMOb16CxiLEyZ6WEwWfq0eppeDX" +
            "24F81ihDO8Nfmv6Y+4qmMhk2vLgmsmeuzxc3NHcq4Fw31+7kvvI5QRc3D25i+Gnu+" +
            "d3h5sRmWDrbxntLU8zjqkbR0IPzP2nvkm3LI2Ncjhdw/GaO4jC2KvnuS+wMgItlzL" +
            "AV6SjxJ1eghYO7OSmodS834xczrUyfw==";

    private static byte[] a = new byte[8];
    //nbt_ztesoft_!@#$%^&==
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DEScoder scoder = new DEScoder("nbt_ztesoft_!@#$%^&==");

//        Log.i(TAG, "onCreate: =="+scoder.encrypt("code"));
//        Log.i(TAG, "onCreate: =="+scoder.decrypt(scoder.encrypt("code")));
        try {
            Log.i(TAG, "onCreate: =="+ new String(a(Base64.decode(code.getBytes("utf-8"),0),"nbt_ztesoft_!@#$%^&=="),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Base64.decode(code,0).toString());
    }

    public static byte[] a(byte[] bArr, String str) throws Exception {
        Key key = a(b(str));
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        a = a("12345678");
        instance.init(2, key, new IvParameterSpec(a));
        return instance.doFinal(bArr);
    }

    private static Key a(byte[] bArr) throws Exception {
        return SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(bArr));
    }
    public static byte[] a(String str) throws Exception {
        return str.getBytes("UTF-8");
    }
    public static byte[] b(String str) throws Exception {
        return str.getBytes();
    }
}
