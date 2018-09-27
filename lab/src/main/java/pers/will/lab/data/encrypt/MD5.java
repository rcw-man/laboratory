package pers.will.lab.data.encrypt;

import java.security.MessageDigest;

public class MD5 {

    public static String encode(String password, String salt) {
        if (salt != null) {
            password += salt;
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        String[] pas = new String[]{"123456", "12345678", "abcdef"};
        for (String var : pas) {
            System.out.println(encode(var, null));
        }

//        String[] users = new String[]{"jamie", "abby", "dickson", "summer", "keefe", "duke", "simba", "leo.deng", "jesse", "ted", "shelley", "daisy"};
//        for (String var: users) {
//            System.out.println(encode(var + "123456", null));
//        }
    }

}
