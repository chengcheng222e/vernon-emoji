package com.vernon.faces.util;

import java.io.UnsupportedEncodingException;

public class Emoji {
    private String unicode = "";
    private String utf8 = "";
    private String utf16 = "";
    private String sbUnicode = "";
    private String faces = "";
    private String name = "";     //
    private String shortName = "";
    private String cn = "";       //中文含义

    public String getUnicode() {
        return unicode;
    }

    public String getUnicodeStr() {
        String tmpStr = utf16.toUpperCase().replaceAll("(U\\+| )", "");
        byte[] bytes = HexConver.hexStrToBytes(tmpStr);
        try {
            return new String(bytes, "utf-16");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }


    public void setUnicode(String unicode) {
        this.unicode = unicode.trim();
    }

    public String getUtf8() {
        return utf8;
    }

    public byte[] getUtf8ToBytes() throws UnsupportedEncodingException {
        return HexConver.hexStrToBytes(utf8.replaceAll(" ", ""));
    }

    public String getUtf8ToStr() throws UnsupportedEncodingException {
        if (utf8 == null) {
            return null;
        }
        return new String(getUtf8ToBytes(), "utf-8");
    }

    public void setUtf8(String utf8) {
        this.utf8 = utf8;
    }

    public String getUtf16() {
        return utf16;
    }

    public void setUtf16(String utf16) {
        this.utf16 = utf16;
    }

    public String getSbUnicode() {
        return sbUnicode;
    }

    public void setSbUnicode(String sbUnicode) {
        this.sbUnicode = sbUnicode;
    }

    public String getSbUnicodeStr() {
        String tmpStr = sbUnicode.toUpperCase().replaceAll("(U\\+| )", "");
        byte[] bytes = HexConver.hexStrToBytes(tmpStr);
        try {
            return new String(bytes, "utf-16");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    public String getFaces() {
        return faces;
    }

    public void setFaces(String faces) {
        this.faces = faces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCn() {
        return cn;
    }

    public String getCnKey() {
        return "[" + this.cn + "]";
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getImg() {
        String tmpStr = unicode.toLowerCase().replaceAll("u\\+", "").replaceAll(" ", "-");
        return tmpStr + ".png";
    }

    /**
     * emoji表情转换(hex -> utf-16)
     * 例如，“自行车”的unicode编码值为U+1F6B2，如果我们要在程序代码中使用“自行车”这个emoji表情，需要这样使用：
     * String bike = String.valueOf(Character.toChars(0x1F6B2));
     *
     * @param hexEmoji
     * @return
     */
    public static String emoji(int hexEmoji) {
        return String.valueOf(Character.toChars(hexEmoji));
    }

    @Override
    public String toString() {
        return "Emoji{" +
                "unicode='" + unicode + '\'' +
                ", utf8='" + utf8 + '\'' +
                ", utf16='" + utf16 + '\'' +
                ", sbUnicode='" + sbUnicode + '\'' +
                ", faces='" + faces + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", cn='" + cn + '\'' +
                '}';
    }

    public String toDateLineStr() {
        StringBuffer sb = new StringBuffer();
        sb.append(unicode);
        String splitStr = ";";
        sb.append(splitStr);
        sb.append(utf8);
        sb.append(splitStr);
        sb.append(utf16);
        sb.append(splitStr);
        sb.append(sbUnicode);
        sb.append(splitStr);
        sb.append(name);
        sb.append(splitStr);
        sb.append(shortName);
        sb.append(splitStr);
        sb.append(cn);
        sb.append(splitStr);
        return sb.toString();
    }
}
