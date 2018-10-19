package com.ant.webPage.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

public class AESUtil {

    //private static Logger logger = LoggerFactory.getLogger(AESUtil.class);


    /**
     * 加密
     * * 1.构造密钥生成器
     * * 2.根据ecnodeRules规则初始化密钥生成器
     * * 3.产生密钥
     * * 4.创建和初始化密码器
     * * 5.内容加密
     * * 6.返回字符串
     *
     * @param password 加密密码
     * @param content  加密内容
     * @return
     */
    public static String AESEncode(String password, String content) {
        try {
            //获取初始化秘钥
            SecretKey key = getKeyForLinux(password);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] byte_encode = content.getBytes("utf-8");
            //9.根据密码器的初始化方式--加密：将数据加密
            byte[] byte_AES = cipher.doFinal(byte_encode);
            //10.将加密后的数据转换为字符串
            //这里用Base64Encoder中会找不到包
            //解决办法：
            //在项目的Build path中先移除JRE System Library，再添加库JRE System Library，重新编译后就一切正常了。
            String AES_encode = new String(new BASE64Encoder().encode(byte_AES));
            //11.将字符串返回
            return AES_encode;
        } catch (Exception e) {
            e.printStackTrace();
            //logger.info("异常信息：{}", e.getMessage());
        }
        //如果有错就返加nulll
        return null;
    }

    /**
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     *
     * @param password 解密密码
     * @param content  解密内容
     * @return
     */
    public static String AESDncode(String password, String content) {
        try {
            //获取初始化秘钥
            SecretKey key = getKeyForLinux(password);//new SecretKeySpec(raw, "AES");
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance("AES");
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key);
            //8.将加密并编码后的内容解码成字节数组
            byte[] byte_content = new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte[] byte_decode = cipher.doFinal(byte_content);
            String AES_decode = new String(byte_decode, "utf-8");
            return AES_decode;
        } catch (Exception e) {
            //logger.info("异常信息：{}", e);
            e.printStackTrace();
        }
        //如果有错就返加nulll
        return null;
    }

    /**
     * 初始化秘钥
     *
     * @param strKey
     * @return
     */
    private static SecretKey getKeyForLinux(String strKey) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            keyGenerator.init(128, secureRandom);
            return keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(" 初始化密钥出现异常 ");
        }
    }

    /**
     * 初始化秘钥
     *
     * @param strKey
     * @return
     */
    private static SecretKey getKeyForWindows(String strKey) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(strKey.getBytes()));
            return keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(" 初始化密钥出现异常 ");
        }

    }

    public static void main(String[] args) {
        String key = "sfadsfsa324afaas";
        String a = "asafw4afasf435afafas";
        String aa = AESUtil.AESEncode(key, a);
        System.out.println("aa= " + aa);
        String bb = AESUtil.AESDncode(key, aa);
        System.out.println("bb = " + bb);
    }
}
