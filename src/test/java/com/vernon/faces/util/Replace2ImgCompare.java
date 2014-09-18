package com.vernon.faces.util;

import com.vernon.faces.EmojiHelper;
import com.vernon.faces.EmojiStr2ImgHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * xxx
 * User: chenyuan
 * Date: 2013-12-25
 */
public class Replace2ImgCompare {
    public static String lida(String content, int index) {
        StringBuffer sbContent = new StringBuffer();
        int leftIndex = content.indexOf("[", index);
        int rightIndex = content.indexOf("]", index);
        if (leftIndex != -1 && rightIndex != -1 && rightIndex > leftIndex) {
            //如果"["和"]"之前存在"[",那么重置leftIndex索引，以便最近的"["和"]"匹配
            if (content.substring(0, rightIndex).lastIndexOf("[") != leftIndex) {
                leftIndex = content.substring(0, rightIndex).lastIndexOf("[");
            }
            String cn = content.substring(leftIndex, rightIndex + 1);
            String imgName = EmojiHelper.cn2Img(cn);
            if (StringUtils.isNotBlank(imgName)) {
                sbContent.append(content.substring(0, leftIndex));
                sbContent.append("<img src=\"" + imgName + "\">");
                sbContent.append(content.substring(rightIndex + 1, content.length()));
                //匹配成功并且替换了字符串，则将开始索引以左边匹配到的为准
                return lida(sbContent.toString(), leftIndex - 1);
            } else {
                sbContent.append(content);
                return lida(sbContent.toString(), rightIndex + 1);
            }
        } else if (leftIndex != -1 && rightIndex != -1 && rightIndex < leftIndex) {
            return lida(content, rightIndex + 1);
        }
        return content;
    }

    public static String seq(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        List<Pair> pairList = null;
        Pair p = getPair(s, 0);
        while (p != null) {
            if (pairList == null) {
                pairList = new ArrayList<Pair>();
            }
            pairList.add(p);
            p = getPair(s, p.right);
        }

        if (pairList == null) {
            return s;
        }

        return getStrByPair(s, pairList);
    }

    private static String getStrByPair(String s, List<Pair> pairList) {
        StringBuffer sbContent = new StringBuffer();

        int length = s.length();
        int l = 0;
        int r = 0;
        for (Pair pair : pairList) {
            if (r < pair.left) {
                sbContent.append(s.substring(r, pair.left));
            }
            String cn = s.substring(pair.left, pair.right + 1);
            String imgName = EmojiHelper.cn2Img(cn);
            if (StringUtils.isNotBlank(imgName)) {
                sbContent.append("<img src=\"" + imgName + "\">");
            } else {
                sbContent.append(cn);
            }
            l = pair.left;
            r = pair.right + 1;
        }

        sbContent.append(s.substring(r, s.length()));
        return sbContent.toString();
    }

    private static Pair getPair(String s, int start) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        int size = s.length();
        if (start > size) {
            return null;
        }

        int left = s.indexOf("[", start);
        int right = -1;
        if (left != -1) {
            right = s.indexOf("]", left);
            if (right != -1) {
                int i = s.substring(left + 1, right).lastIndexOf("[", left + 1);
                if (i != -1) {
                    left = start + i;
                }
                return new Pair(left, right);
            }
        }
        return null;
    }

    public static class Pair {
        int left;
        int right;
        String s;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public Pair(int left, int right, String s) {
            this.left = left;
            this.right = right;
            this.s = s;
        }
    }

    public static String regex(String s) {
        String regEx = "(\\[[^\\[]*?\\])";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        int i = 0;
        Map<String, String> map = new HashMap<String, String>();
        while (m.find()) {
            String cn = m.group();
            String imgName = EmojiHelper.cn2Img(cn);
            if (StringUtils.isNotBlank(imgName)) {
                imgName = "<img src=\"" + imgName + "\">";
                map.put(cn, imgName);
                i++;
            }
        }
        int size = map.size();
        if (size > 0) {
            String[] ks = new String[size];
            String[] vs = new String[size];
            int index = 0;
            Set<String> keySet = map.keySet();
            for (String k : keySet) {
                ks[index] = k;
                vs[index] = map.get(k);
                index++;
            }
            return StringUtils.replaceEach(s, ks, vs);
        }
        return s;
    }

    public static void main(String[] args) {
        String s = ":eqeweq[挖鼻屎]we[awd][神马]adaq[wa]eqwe";
        System.out.println(s);
        //compare(s);

        s = "[围观][围观][围[围观][围观][围观]";
        System.out.println(s);
        compare(s);

        s = "[围观][围观][围-[围观]-][围观][围观]";
        System.out.println(s);
        compare(s);

        s = s + s + s;
        System.out.println(s);
        compare(s);
        s = "var s=[];s[0]=\"[织]\";s[1]=\"[神马]\";s[2]=\"[浮云]\";s[3]=\"[给力]\";s[4]=\"[围观]\";s[5]=\"[威武]\";s[6]=\"[熊猫]\";s[7]=\"[兔子]\";s[8]=\"[奥特曼]\";s[9]=\"[囧]\";s[10]=\"[互粉]\";s[11]=\"[礼物]\";s[12]=\"[呵呵]\";s[13]=\"[嘻嘻]\";s[14]=\"[哈哈]\";s[15]=\"[可爱]\";s[16]=\"[可怜]\";s[17]=\"[挖鼻屎]\";s[18]=\"[吃惊]\";s[19]=\"[害羞]\";s[20]=\"[挤眼]\";s[21]=\"[闭嘴]\";s[22]=\"[鄙视]\";s[23]=\"[爱你]\";s[24]=\"[泪]\";s[25]=\"[偷笑]\";s[26]=\"[亲亲]\";s[27]=\"[生病]\";s[28]=\"[太开心]\";s[29]=\"[懒得理你]\";s[30]=\"[右哼哼]\";s[31]=\"[左哼哼]\";s[32]=\"[嘘]\";s[33]=\"[衰]\";s[34]=\"[委屈]\";s[35]=\"[吐]\";s[36]=\"[打哈气]\";s[37]=\"[抱抱]\";s[38]=\"[怒]\";s[39]=\"[疑问]\";s[40]=\"[馋嘴]\";s[41]=\"[拜拜]\";s[42]=\"[思考]\";s[43]=\"[汗]\";s[44]=\"[困]\";s[45]=\"[睡觉]\";s[46]=\"[钱]\";s[47]=\"[失望]\";s[48]=\"[酷]\";s[49]=\"[花心]\";s[50]=\"[哼]\";s[51]=\"[鼓掌]\";s[52]=\"[晕]\";s[53]=\"[悲伤]\";s[54]=\"[抓狂]\";s[55]=\"[黑线]\";s[56]=\"[阴险]\";s[57]=\"[怒骂]\";s[58]=\"[心]\";s[59]=\"[伤心]\";s[60]=\"[猪头]\";s[61]=\"[ok]\";s[62]=\"[耶]\";s[63]=\"[good]\";s[64]=\"[不要]\";s[65]=\"[赞]\";s[66]=\"[来]\";s[67]=\"[弱]\";s[68]=\"[蜡烛]\";s[69]=\"[钟]\";s[70]=\"[蛋糕]\";s[71]=\"[话筒]\";";
        compare(s + s + s);
        compare(s + s + s + s + s + s + s + s + s);
    }

    private static void compare(String s) {
        String r1 = null;
        String r3 = null;
        String r4 = null;
        int max = 1000;
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            r1 = Replace2ImgCompare.lida(s, 0);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("1=" + r1);

        long start3 = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            r3 = Replace2ImgCompare.seq(s);
        }
        System.out.println("3=" + r3);
        long end3 = System.currentTimeMillis();

        long start4 = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            r4 = EmojiStr2ImgHelper.str2Img(s, "<img src=\"", "\">");
        }
        System.out.println("4=" + r4);
        long end4 = System.currentTimeMillis();

        System.out.println((r1.equals(r4) && r3.equals(r4))
                + "时间对比,length=" + s.length() +
                ",李大=" + (end1 - start1) +
                "ms,顺序=" + (end3 - start3) + "ms,正则pair=" + (end4 - start4) + "ms"
        );
        System.out.println("--");
    }

}
