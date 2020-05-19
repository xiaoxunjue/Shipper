package com.revenant.shipper.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;

public class AESHelper {
    private static AESHelper instance = null;

    private Cipher cipher;

    SecretKeySpec key = null;

    AlgorithmParameterSpec iv = null;

    public static AESHelper getInstance() {
        String key = "YvllFaIri5Q6dxeG", iv = "sZM5JhqvbkprWA0u";
        return getInstance(key, iv);
    }

    public static AESHelper getInstance(String key, String iv) {
        instance = new AESHelper(key, iv);
        return instance;
    }

    /**
     * 将32bytes的16禁止串压缩成16bytes，充分利用128bits的密钥空间
     *
     * @param hex
     * @return byte[]
     */
    public static byte[] packHex(String hex) {
        int len = hex.length() >> 1;
        byte[] b = new byte[len];
        short k = 0;
        for (int i = 0, j = 0; i < len; i++, j = i << 1) {
            k = Short.parseShort(hex.substring(j, j + 2), 16);
            b[i] = (byte) (k & 0xff);
        }
        return b;
    }

    public static String sha1(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] b = MessageDigest.getInstance("sha1").digest(s.getBytes("UTF-8"));
        return byte2hex(b);
    }

    public static String byte2hex(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            if (b[i] >= 0 && b[i] < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(b[i] & 0xff));
        }
        return sb.toString();
    }

    /**
     * 生成可打印的指定长度的随机字符串 asc码范围[32,126]
     *
     * @param length
     * @return
     */
    public static String getRandStr(int length) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append((char) (r.nextInt(127 - 32) + 32));
        }
        return sb.toString();
    }

    private AESHelper(String keyStr, String ivStr) {
        try {
            iv = new IvParameterSpec(ivStr.getBytes("utf-8"));
            key = new SecretKeySpec(keyStr.getBytes("utf-8"), "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     *
     * @return String
     */
    public byte[] encrypt(byte[] txt) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            return cipher.doFinal(txt);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     *
     * @param txt
     *            txt
     *
     * @return String
     */
/*    public String encrypt(String txt) {
        try {
            byte[] b = this.encrypt(txt.getBytes("utf-8"));
            return Base64.encodeToString(b, 0, b.length, Base64.URL_SAFE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /**
     * 加密
     *
     *            txt
     *
     * @return String
     */
    public String encrypt(String txt) {
        try {
            byte[] b = this.encrypt(txt.getBytes("utf-8"));
            return Base64.encodeToString(b, 0, b.length, Base64.NO_PADDING | Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     *
     * @return String
     */
    public byte[] decrypt(byte[] txt) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            return cipher.doFinal(txt);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String txt){
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] b = txt.getBytes();
            b = Base64.decode(b, 0, b.length, Base64.NO_PADDING | Base64.NO_WRAP);
            b = cipher.doFinal(b);
            return new String(b, "utf-8");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     *
     * @return String
     */
    /*public String decrypt(String txt) {

        try {
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] b = txt.getBytes();
            b = Base64.decode(b, 0, b.length, Base64.URL_SAFE);
            b = cipher.doFinal(b);
            return new String(b, "utf-8");
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public static void test() {
        String txt = "123456";
        AESHelper aes = AESHelper.getInstance("YvllFaIri5Q6dxeG","sZM5JhqvbkprWA0u");
        String x = aes.encrypt(txt);
        System.out.println(x);
    }

    public static void main(String[] args) {
        String txt = "{\"errCode\":\"0\",\"errMsg\":\"SUCCESS\",\"vresSign\":\"54CAD23E4ED861915F8C22BD4F897F9D\",\"timeStamp\":1550128699598,\"data\":[{\"parentId\":null,\"studentId\":null,\"classId\":29120,\"className\":\"同步2014届4班\",\"adviser\":null,\"courseTableId\":null,\"adviserUpdateOnly\":null,\"courseJson\":[[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"]],\"isAssistant\":null},{\"parentId\":null,\"studentId\":null,\"classId\":29973,\"className\":\"同步2017届1班\",\"adviser\":2276082,\"courseTableId\":null,\"adviserUpdateOnly\":0,\"courseJson\":[[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"测试\",\"高级\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"测试\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"],[\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\",\"\"]],\"isAssistant\":null}]}\n";
        AESHelper aes = AESHelper.getInstance();
        //String x = aes.encrypt(txt, Base64.NO_PADDING | Base64.NO_WRAP);
        String x = aes.encrypt("{\"code\":\"0\",\"msg\":\"SUCCESS\",\"sign\":null,\"timestamp\":0,\"data\":{\"total\":32,\"result\":[{\"id\":2738,\"name\":\"手机\",\"type\":null,\"loading\":\"福建省-泉州市-鲤城区\",\"loadingCode\":\"350502\",\"unload\":\"浙江省-金华市-婺城区\",\"unloadCode\":\"330702\",\"weight\":1.200,\"cube\":null,\"total\":4,\"accountId\":87,\"status\":false,\"ctime\":\"2020-03-19 13:56:38\",\"mtime\":\"2020-03-19 13:56:38\",\"isDel\":0,\"isPublish\":1,\"despatchDate\":\"2020-03-19\",\"recieveName\":\"李\",\"recieveMobile\":\"13101555965\",\"isAuto\":true,\"isOnline\":false,\"price\":1.20,\"danWei\":1,\"vehicleLeader\":\"不限车长|不限车型|不限\",\"companyName\":\"测*五\",\"loadingDetail\":\"泉州\",\"unloadingDetail\":\"金华\",\"loadDate\":\"2020-03-19-2020-03-21\",\"isInvoice\":true,\"remark\":\"\",\"isPlatform\":true,\"number\":5},{\"id\":2556,\"name\":\"测试货物\",\"type\":null,\"loading\":\"新疆维吾尔自治区-昌吉回族自治州-昌吉市\",\"loadingCode\":\"652301 \n" +
                "2020-03-19 17:00:05 1041342 [reactor-http-epoll-6] INFO  c.c.g.f.WrapperResponseGlobalFilter - data: \",\"unload\":\"天津市-天津市-和平区\",\"unloadCode\":\"120101\",\"weight\":2.000,\"cube\":null,\"total\":1,\"accountId\":87,\"status\":false,\"ctime\":\"2020-03-17 15:21:11\",\"mtime\":\"2020-03-17 15:21:11\",\"isDel\":0,\"isPublish\":1,\"despatchDate\":\"2020-03-17\",\"recieveName\":\"木木\",\"recieveMobile\":\"13591241046\",\"isAuto\":false,\"isOnline\":false,\"price\":12.00,\"danWei\":1,\"vehicleLeader\":\"5.6米|普通|不限\",\"companyName\":\"测*五\",\"loadingDetail\":\"00\",\"unloadingDetail\":\"00\",\"loadDate\":\"2020-03-17-2020-03-20\",\"isInvoice\":true,\"remark\":\"\",\"isPlatform\":true,\"number\":2},{\"id\":2555,\"name\":\"测试货物\",\"type\":null,\"loading\":\"重庆市-重庆市-万州区\",\"loadingCode\":\"500101\",\"unload\":\"天津市-天津市-和平区\",\"unloadCode\":\"120101\",\"weight\":2.000,\"cube\":null,\"total\":1,\"accountId\":87,\"status\":false,\"ctime\":\"2020-03-17 15:20:55\",\"mtime\":\"2020-03-17 15:20:55\",\"isDel\":0,\"isPublish\":1,\"despatchDate\":\"2020-03-17\",\"recieveName\":\"木木\",\"recieveMobile\":\"13591241046\",\"isAuto\":false,\"isOnline\":false,\"price\":10.00,\"danWei\":1,\"vehicleLeader\":\"5.6米|普通|不限\",\"companyName\":\"测*五\",\"loadingDetail\":\"00\",\"unloadingDetail\":\"00\",\"loadDate\":\"2020-03-17-2020-03-20\",\"isInvoice\":true,\"remark\":\"\",\"isPlatform\":true,\"number\":2},{\"id\":2554,\"name\":\"测试货物\",\"type\":null,\"loading\":\"吉林省-松原市-宁江区\",\"loadingCode\":\"220702\",\"unload\":\"天津市-天津市-和平区\",\"unloadCode\":\"120101\",\"weight\":2.000,\"cube\":null,\"total\":2,\"accountId\":87,\"status\":false,\"ctime\":\"2020-03-17 15:20:43\",\"mtime\":\"2020-03-17 15:20:43\",\"isDel\":0,\"isPublish\":1,\"despatchDate\":\"2020-03-17\",\"recieveName\":\"木木\",\"recieveMobile\":\"13591241046\",\"isAuto\":false,\"isOnline\":false,\"price\":8.00,\"danWei\":1,\"vehicleLeader\":\"5.6米|普通|不限\",\"companyName\":\"测*五\",\"loadingDetail\":\"00\",\"unloadingDetail\":\"00\",\"loadDate\":\"2020-03-17-2020-03-20\",\"isInvoice\":true,\"remark\":\"\",\"isPlatform\":true,\"number\":2},{\"id\":2553,\"name\":\"测试货物\",\"type\":null,\"loading\":\"山东省-枣庄市-市中区\",\"loadingCode\":\"370402\",\"unload\":\"天津市-天津市-和平区\",\"unloadCode\":\"120101\",\"weight\":2.000,\"cube\":null,\"total\":2,\"accountId\":87,\"status\":false,\"ctime\":\"2020-03-17 15:20:28\",\"mtime\":\"2020-03-17 15:20:28\",\"isDel\":0,\"isPublish\":1,\"despatchDate\":\"2020-03-17\",\"recieveName\":\"木木\",\"recieveMobile\":\"13591241046\",\"isAuto\":false,\"isOnline\":false,\"price\":6.00,\"danWei\":1,\"vehicleLeader\":\"5.6米|普通|不限\",\"companyName\":\"测*五\",\"loadingDetail\":\"00\",\"unloadingDetail\":\"00\",\"loadDate\":\"2020-03-17-2020-03-20\",\"isInvoice\":true,\"remark\":\"\",\"isPlatform\":true,\"number\":2},{\"id\":2552,\"name\":\"测试货物\",\"type\":null,\"loading\":\"福建省-泉州市-鲤城区\",\"loadingCode\":\"350502\",\"unload\":\"天津市-天津市-和平区\",\"unloadCode\":\"120101\",\"weight\":2.000,\"cube\":null,\"total\":2,\"accountId\":87,\"status\":false,\"ctime\":\"2020-03-17 15:20:14\",\"mtime\":\"2020-03-17 15:20:14\",\"isDel\":0,\"isPublish\":1,\"despatchDate\":\"2020-03-17\",\"recieveName\":\"木木\",\"recieveMobile\":\"13591241046\",\"isAuto\":false,\"isOnline\":false,\"price\":4.00,\"danWei\":1,\"vehicleLeader\":\"5.6米|普通|不限\",\"companyName\":\"测*五\",\"loadingDetail\":\"00\",\"unloadingDetail\":\"00\",\"loadDate\":\"2020-03-17-2020-03-20\",\"isInvoice\":true,\"remark\":\"\",\"isPlatform\":true,\"number\":2},{\"id\":2551,\"name\":\"测试货物\",\"type\":null,\"loading\":\"西藏自治区-拉萨市-城关区\",\"loadingCode\":\"540102\",\"unload\":\"天津市-天津市-和平区\",\"unloadCode\":\"120101\",\"weight\":2.000,\"cube\":null,\"total\":2,\"accountId\":87,\"status\":false,\"ctime\":\"2020-03-17 15:20:01\",\"mtime\":\"2020-03-17 15:20:01\",\"isDel\":0,\"isPublish\":1,\"despatchDate\":\"2020-03-17\",\"recieveName\":\"木木\",\"recieveMobile\":\"13591241046\",\"isAuto\":false,\"isOnline\":false,\"price\":2.00,\"danWei\":1,\"vehicleLeader\":\"5.6米|普通|不限\",\"companyName\":\"测*五\",\"loadingDetail\":\"00\",\"unloadingDetail\":\"00\",\"loadDate\":\"2020-03-17-2020-03-20\",\"isInvoice\":true,\"remark\":\"\",\"isPlatform\":true,\"number\":2},{\"id\":2519,\"name\":\"测试货物\",\"type\":null,\"loading\":\"贵州省-贵阳市-南明区\",\"loadingCode\":\"520102\",\"unload\":\"山东省-枣庄市-市中区\",\"unloadCode\":\"370402\",\"weight\":2.000,\"cube\":null,\"total\":2,\"accountId\":87,\"status\":false,\"ctime\":\"2020-03-17 10:26:37\",\"mtime\":\"2020-03-17 10:26:37\",\"isDel\":0,\"isPublish\":1,\"despatchDate\":\"2020-03-17\",\"recieveName\":\"木木\",\"recieveMobile\":\"13591241046\",\"isAuto\":false,\"isOnline\":false,\"price\":8.00,\"danWei\":1,\"vehicleLeader\":\"4.2米|普通|不限\",\"companyName\":\"测*五\",\"loadingDetail\":\"00\",\"unloadingDetail\":\"00\",\"loadDate\":\"2020-03-17-2020-03-17\",\"isInvoice\":true,\"remark\":\"\",\"isPlatform\":true,\"number\":2},{\"id\":2518,\"name\":\"测试货物\",\"type\":null,\"loading\":\"山西省-长治市-城区\",\"loadingCode\":\"140402\",\"unload\":\"山东省-枣庄市-市中区\",\"unloadCode\":\"370402\",\"weight\":2.000,\"cube\":null,\"total\":2,\"accountId\":87,\"status\":false,\"ctime\":\"2020-03-17 10:26:26\",\"mtime\":\"2020-03-17 10:26:26\",\"isDel\":0,\"isPublish\":1,\"despatchDate\":\"2020-03-17\",\"recieveName\":\"木木\",\"recieveMobile\":\"13591241046\",\"isAuto\":false,\"isOnline\":false,\"price\":6.00,\"danWei\":1,\"vehicleLeader\":\"4.2米|普通|不限\",\"companyName\":\"测*五\",\"loadingDetail\":\"00\",\"unloadingDetail\":\"00\",\"loadDate\":\"2020-03-17-2020-03-17\",\"isInvoice\":true,\"remark\":\"\",\"isPlatform\":true,\"number\":2},{\"id\":2517,\"name\":\"测试货物\",\"type\":null,\"loading\":\"山东省-枣庄市-市中区\",\"loadingCode\":\"370402\",\"unload\":\"山东省-枣庄市-市中区\",\"unloadCode\":\"370402\",\"weight\":2.000,\"cube\":null,\"total\":2,\"accountId\":87,\"status\":false,\"ctime\":\"2020-03-17 10:26:09\",\"mtime\":\"2020-03-17 10:26:09\",\"isDel\":0,\"isPublish\":1,\"despatchDate\":\"2020-03-17\",\"recieveName\":\"木木\",\"recieveMobile\":\"13591241046\",\"isAuto\":false,\"isOnline\":false,\"price\":4.00,\"danWei\":1,\"vehicleLeader\":\"4.2米|普通|不限\",\"companyName\":\"测*五\",\"loadingDetail\":\"00\",\"unloadingDetail\":\"00\",\"loadDate\":\"2020-03-17-2020-03-17\",\"isInvoice\":true,\"remark\":\"\",\"isPlatform\":true,\"number\":2}],\"pageNum\":1,\"pageSize\":10,\"pages\":4,\"size\":10}}");
        System.out.println(x);
        //String y = aes.decrypt(txt);
        //System.out.println(aes.decrypt("KKX36pzIfALA/C32jtw6q9DiFGdPSpQjwJnX1JEuH9dyK77jDuFB8nCI/1L+FSTlVLwEyp5Km0P5N3wwn95AEi9eij8SSY3LcX9+GI8ILl9R4schpV/xM4/+wZZ/VIX6b0LhATOHsvkLMN1H/tBHe0L5trVQFhplSNRaNirMpngCzZj5IorHNhZsycGv5Y5d6TaYsxq1FBlpzP42asbBBgzpLWXznSI90BJ3gHWn4FyK6q6fZZqkR8EB7nJu76XBqhC4Bitb0faD50VeBbcWfP+ac+uUw2NNp3dHw0Zr8Foq0rJDCZ2KioKDcfjt0PzMc1Sc5mfN3g9/wAQnnNaluLT172LUW2xkhbcQZv/ZiptJFgMXXg3y2prWIqpePt/N2Ffei/MsR9kQxkfVnfxptoPvRtzOuugTNNR2nCIhzwGLoLzksoss8gyAdzAVMMYjj8+5H2dkyFJupL+ybpp+qbiPrLskql6ZtIdz/xZs3yvlOHLW1ZuM96NIdgQapwn1MOz7+RAMNquZop7ok2NEGcJIQmfFC6eAwL+0jX9XVKLfZolbMdP+2dkgIyzlL+STukin9JEITgQQ4oh2SHaDA4LhPBLUdW45tmm72tLO7fo8CHYb8if1Cl+3wG7HNh6AltxusjNBHPiPIpYIa69zrpwzmM9nkf6EJfWemqcHc7tAruk+BClqZcb2PIpbl2BF+/QwQQ5Jc1hbYxuO1mCF8YGvWcDWovXIT3vnicjAZe+U4kjakHTMkqnYRsC4ieDRJS0u3TAcYnLpdj30Agp4LBZ0SHVAvmWeS6w160LPkx7l7PBLj/1fhBGYMJjznB5bd8FyJW5NlyxibithGqsYStVBz4LnVqdz573pb2IGwHHdZDNhzg/+aNdUm7DWssZzBPlKjekO80llRJ8ZFJuPit5R6d1TELZTYYj6kL2Ey7/d22yZUZNzZ3H89KM3L2Kh/thzZyobOyqWR2H9SZwWbdg315C44SVRtVAWzgzmwtYChueMDFMs7N8+q4yYPIgo/CtSS11lRfT9gU1aNfKOWZusL7G96NNtlcYgt7vYJ3lC4zUpqVjl29x+7klWz3KB925CwAjdiE13X7VVw/u0BP9ZilIEPAUcTJsh5dUcS1AUViQXHH4M7g3Bv6/0tQSyvSVGfyFxERVJ5rRhOxQTuxGBqIJ+e5iwFFiJGITLKVySg4AXKeLplgbafa4pBC8x\n"));
        /*String encode = cn.hutool.core.codec.Base64.decodeStr(
                "S0tYMzZweklmQUxBL0MzMmp0dzZxOURpRkdkUFNwUWp3Sm5YMUpFdUg5ZHlLNzdqRHVGQjhuQ0kvMUwrRlNUbFZMd0V5cDVLbTBQNU4zd3duOTVBRWk5ZWlqOFNTWTNMY1g5K0dJOElMbDlSNHNjaHBWL3hNNC8rd1paL1ZJWDZiMExoQVRPSHN2a0xNTjFIL3RCSGUwTDV0clZRRmhwbFNOUmFOaXJNcG5nQ3paajVJb3JITmhac3ljR3Y1WTVkNlRhWXN4cTFGQmxwelA0MmFzYkJCZ3pwTFdYem5TSTkwQkozZ0hXbjRGeUs2cTZmWlpxa1I4RUI3bkp1NzZYQnFoQzRCaXRiMGZhRDUwVmVCYmNXZlArYWMrdVV3Mk5OcDNkSHcwWnI4Rm9xMHJKRENaMktpb0tEY2ZqdDBQek1jMVNjNW1mTjNnOS93QVFubk5hbHVMVDE3MkxVVzJ4a2hiY1Fadi9aaXB0SkZnTVhYZzN5MnByV0lxcGVQdC9OMkZmZWkvTXNSOWtReGtmVm5meHB0b1B2UnR6T3V1Z1ROTlIybkNJaHp3R0xvTHprc29zczhneUFkekFWTU1Zamo4KzVIMmRreUZKdXBMK3licHArcWJpUHJMc2txbDZadElkei94WnMzeXZsT0hMVzFadU05Nk5JZGdRYXB3bjFNT3o3K1JBTU5xdVpvcDdvazJORUdjSklRbWZGQzZlQXdMKzBqWDlYVktMZlpvbGJNZFArMmRrZ0l5emxMK1NUdWtpbjlKRUlUZ1FRNG9oMlNIYURBNExoUEJMVWRXNDV0bW03MnRMTzdmbzhDSFliOGlmMUNsKzN3RzdITmg2QWx0eHVzak5CSFBpUElwWUlhNjl6cnB3em1NOW5rZjZFSmZXZW1xY0hjN3RBcnVrK0JDbHFaY2IyUElwYmwyQkYrL1F3UVE1SmMxaGJZeHVPMW1DRjhZR3ZXY0RXb3ZYSVQzdm5pY2pBWmUrVTRramFrSFRNa3FuWVJzQzRpZURSSlMwdTNUQWNZbkxwZGozMEFncDRMQlowU0hWQXZtV2VTNncxNjBMUGt4N2w3UEJMai8xZmhCR1lNSmp6bkI1YmQ4RnlKVzVObHl4aWJpdGhHcXNZU3RWQno0TG5WcWR6NTczcGIySUd3SEhkWkROaHpnLythTmRVbTdEV3NzWnpCUGxLamVrTzgwbGxSSjhaRkp1UGl0NVI2ZDFURUxaVFlZajZrTDJFeTcvZDIyeVpVWk56WjNIODlLTTNMMktoL3Roelp5b2JPeXFXUjJIOVNad1diZGczMTVDNDRTVlJ0VkFXemd6bXd0WUNodWVNREZNczdOOCtxNHlZUElnby9DdFNTMTFsUmZUOWdVMWFOZktPV1p1c0w3Rzk2Tk50bGNZZ3Q3dllKM2xDNHpVcHFWamwyOXgrN2tsV3ozS0I5MjVDd0FqZGlFMTNYN1ZWdy91MEJQOVppbElFUEFVY1RKc2g1ZFVjUzFBVVZpUVhISDRNN2czQnY2LzB0UVN5dlNWR2Z5RnhFUlZKNXJSaE94UVR1N2RmMGhNdGFrMnhDOWZMM0pXbERqbw==");
        String decrypt = aes.decrypt(encode);
        System.out.println(decrypt);*/
        //System.out.println(aes.decrypt("0D6qT1SZ2XgyMdn185QGe2BFbt0goZ/TapQdug8t/yD8Ig8iKXfSVLkscNnIPk/I6UgNIf/++exhJdpQfCRX/hXZ3z9aQMupLHvNMpfagbe02azTFxw7gVokEW30XMRRKDbUqRHjOK7cv6FW3kcFpQ"));
        //System.out.println(aes.decrypt("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"));
        System.out.println("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\n");
        System.out.println(aes.decrypt("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\n"));
    }
}
