package com.vernon.faces.util;

import com.vernon.faces.EmojiStr2ImgHelper;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * xxx
 * User: chenyuan
 * Date: 2014-01-02
 */
public class ScriptBuilder {
    public static String buildJavaScript() {
        StringBuffer sButter = new StringBuffer();

        sButter.append("var emojiHash=[];");

        int i = 0;
        Map<String, String> cnKey2ImgMap = EmojiStr2ImgHelper.getCnKey2ImgMap();
        Set<String> keys = cnKey2ImgMap.keySet();
        for (String k : keys) {
            sButter.append("this.emojiHash[\"" + k + "\"]=\"" + cnKey2ImgMap.get(k) + "\";");
        }
        return sButter.toString();
    }

    public static String buildJava() {
        StringBuffer sButter = new StringBuffer();
        sButter.append("Map<String,String> m =new HashMap<String,String>();\n");
        Map<String, String> cnKey2ImgMap = EmojiStr2ImgHelper.getCnKey2ImgMap();
        Set<String> keys = cnKey2ImgMap.keySet();
        for (String cnKey : keys) {
            if (cnKey.startsWith("[em")) {
                String img = cnKey2ImgMap.get(cnKey);
                sButter.append("m.put(\"" + cnKey + "\",\"" + img + "\");\n");
            }
        }
        return sButter.toString();


    }

    public static String buildCnKey2Img() {
        StringBuffer sButter = new StringBuffer();
        Map<String, String> cnKey2ImgMap = EmojiStr2ImgHelper.getCnKey2ImgMap();
        Set<String> keys = new TreeSet<String>(cnKey2ImgMap.keySet());
        //Set<String> keys = cnKey2ImgMap.keySet();
        for (String cnKey : keys) {
            if (cnKey.startsWith("[em")) {
                String img = cnKey2ImgMap.get(cnKey);
                sButter.append(cnKey + "=" + img + "\n");
            }
        }
        return sButter.toString();
    }

    public static void main(String[] args) {
        System.out.println(buildJavaScript());
        //System.out.println(buildJava());
//        System.out.println(buildCnKey2Img());
    }
}
