package com.lk.tools.security;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @author luokun
 * @date 2019/4/23 10:03
 */
public class EncryptUtils {
    /**
     * MD5加密，结果为全小写
     * @author eric
     * @date 2017年6月16日下午12:22:18
     * @param plainText 需要加密的文本
     * @return 加密后的结果
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if(i<0) i+= 256;
            if(i<16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    /**
     * base64编码
     * @author eric
     * @date 2017年6月16日下午12:22:08
     * @param plainText：需要编码的文本
     * @param encoding：字符编码
     * @return
     * @throws UnsupportedEncodingException
     * @throws Exception
     */
    public static String base64(String plainText, String encoding)
            throws UnsupportedEncodingException {
        byte[] encodeBase64 = Base64.encodeBase64(plainText.getBytes(encoding));
        return new String(encodeBase64);
    }

    /**
     * des加密后进行字节转16进制字符串返回
     * @author eric
     * @date 2017年6月16日下午3:44:57
     * @param plainText 需要加密的文本
     * @param key 加密密钥
     * @return
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String des(String plainText, String key)
            throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        return byteArr2HexStr(desEnCode(plainText.getBytes(), key));
    }

    /**
     * des编码
     * @author eric
     * @date 2017年6月16日下午2:26:31
     * @param plainText 需要编码的文本
     * @param key 编码的密钥
     * @return
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static byte[] desEnCode(byte[] plainText, String key)
            throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        SecureRandom random = new SecureRandom();
        //计算key的位数
        byte[] tmpKey = key.getBytes();
        byte[] arrkey = new byte[8];
        for (int i = 0; i < arrkey.length && i<tmpKey.length; i++) {
            arrkey[i] = tmpKey[i];
        }
        //计算key
        DESKeySpec desKey = new DESKeySpec(arrkey);
        //创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
        //现在，获取数据并加密
        //正式执行加密操作
        return cipher.doFinal(plainText);
    }

    /**
     * url编码，编码为TF-8
     * @author eric
     * @date 2017年6月16日下午4:20:38
     * @param plainText 需要编码的文本
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncoder(String plainText)
            throws UnsupportedEncodingException{
        return urlEncoder(plainText,"UTF-8");
    }

    /**
     * url编码
     * @author eric
     * @date 2017年6月16日下午4:19:38
     * @param plainText 需要编码的文本
     * @param encoding 编码字符串的编码格式
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncoder(String plainText, String encoding)
            throws UnsupportedEncodingException{
        return URLEncoder.encode(plainText,encoding);
    }

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813
     * @author eric
     * @date 2017年6月16日下午3:07:25
     * @param arrB 需要转换成字符串的字节
     * @return 转换后的字符串
     * @throws Exception
     */
    public static String byteArr2HexStr(byte[] arrB){
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }
}
