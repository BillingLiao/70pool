package com.ant.webPage.util;

import org.apache.commons.lang.RandomStringUtils;

import java.security.MessageDigest;

/**
 * @author Billing
 * @date 2018/9/10 15:32
 */
public class Md5Util {

    public static String getMd5(String oldStr) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            // 参数oldStr表示要加密的字符串
            // 转换成字节流
            byte[] oldBytes = oldStr.getBytes();
            // 得到对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 初始化
            md.update(oldBytes);
            // 运行加密算法
            byte[] newBytes = md.digest();
            // 构造长度为2倍的字符串
            char newStr[] = new char[32];
            // 循环进行处理
            for (int i = 0; i < 16; i++) {
                byte tmp = newBytes[i];
                newStr[2 * i] = hexDigits[tmp >>> 4 & 0xf];
                newStr[2 * i + 1] = hexDigits[tmp & 0xf];
            }
            return String.valueOf(newStr);
        } catch (Exception e) {
            e.printStackTrace();
            return "md5失败";
        }
    }

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f" };

    /**
     * 盐值
     */
    private Object salt;
    /**
     * 加密方式 MD5,SHA
     */
    private String algorithm;

    public Md5Util(Object salt, String algorithm) {
        this.salt = salt;
        this.algorithm = algorithm;
    }

    public String encode(String rawPass) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            // 加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass).getBytes("utf-8")));
        } catch (Exception ex) {
        }
        return result;
    }

    public boolean isPasswordValid(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass);

        return pass1.equals(pass2);
    }

    private String mergePasswordAndSalt(String password) {
        if (password == null) {
            password = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt.toString() + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     *
     * @param b
     *            字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) {
        //String salt = RandomStringUtils.randomAlphanumeric(20);
        //System.out.println(salt);
        String salt = "l9rewDxy6Nw4rYdytb2p";
        Md5Util encoderMd5 = new Md5Util(salt, "MD5");
        String encode = encoderMd5.encode("123456");
        System.out.println(encode);
        boolean passwordValid = encoderMd5.isPasswordValid("f651b74e526731fd6ea45c995783343f", "123456");
        System.out.println(passwordValid);
        Md5Util encoderSha = new Md5Util(salt, "SHA");
        String pass2 = encoderSha.encode("test");
        System.out.println(pass2);
        boolean passwordValid2 = encoderSha.isPasswordValid("1e4346dcb54c1444e91668e04b8ca4f74f42958e", "test");
        System.out.println(passwordValid2);
    }
}
