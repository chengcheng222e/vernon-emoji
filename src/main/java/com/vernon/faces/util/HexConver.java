package com.vernon.faces.util;

public class HexConver {
    /**
     * @param hex
     * @return
     */
    public static byte[] hexStrToBytes(String hex) {
        hex = hex.toUpperCase();
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (hexChar2Byte(achar[pos]) << 4 | hexChar2Byte(achar[pos + 1]));
        }
        return result;
    }

    /**
     * @param c
     * @return
     */
    public static byte hexChar2Byte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * String的字符串转换成unicode的String
     *
     * @param strText strText 全角字符串
     * @return String 每个unicode之间无分隔符
     * @throws Exception
     */
    public static String strToUnicode(String strText)
            throws Exception {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = (int) c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128)
                str.append("U+" + strHex + " ");
            else // 低位在前面补00
                str.append("\\u00" + strHex);
        }
        return str.toString();
    }

    public static void main(String[] args) throws Exception {
        byte[] bytes = hexStrToBytes("D83DDC08");
        String s = new String(bytes, "utf-16");
        System.out.println(strToUnicode(s));
        System.out.println("aa" + s.replaceAll(Emoji.emoji(0x1F408), ""));
    }
}

