package com.zxj.skin.skin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class SkinUtils {


    /**
     * 获取一个文件的md5值(可处理大文件)
     *
     * @return md5 value
     */
    public static String getSkinMD5(File file) {
        FileInputStream fis = null;
        BigInteger bi = null;
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[10240];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            byte[] digest = MD5.digest();
            bi = new BigInteger(1, digest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bi.toString(16);
    }

    /// 对文件进行md5
    public static String getFileMD5(File file) {
        if (null == file) {
            return null;
        }

        if (!file.isFile()) {
            return null;
        }

        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024 * 16];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            if (null != in) {
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest.digest());
        String hexString = bigInt.toString(16);

        if (0 != hexString.length() % 2) {
            hexString = "0" + hexString;
        }

        int totalLen = 32;
        if (totalLen != hexString.length()) {
            int tempLen = totalLen - hexString.length();
            String prefix = "";
            while (tempLen > 0) {
                prefix = prefix + "0";
                tempLen--;
            }

            hexString = prefix + hexString;
        }
        return hexString;
    }


}
