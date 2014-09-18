package com.vernon.faces.util;

import com.vernon.faces.EmojiData;
import com.vernon.faces.util.Emoji;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * xxx
 * User: chenyuan
 * Date: 2013-12-29
 */
public class ReplaceOldHelper {
    private static String[] unicodeEmojis;
    private static String[] unicodeNulls;
    private static String[] unicodeCns;

    private static String[] sbCodes;
    private static String[] sbCode2Unicodes;

    static {
        init();
    }

    private static void init() {
        List<String> unicodeEmojiList = new ArrayList<String>();
        List<String> unicodeNullList = new ArrayList<String>();
        List<String> unicodeCnList = new ArrayList<String>();
        List<String> sbCodeList = new ArrayList<String>();
        List<String> sbCode2UnicodeList = new ArrayList<String>();
        List<Emoji> emojiList = EmojiData.getEmojiList();

        for (Emoji emoji : emojiList) {
            String unicodeStr = emoji.getUnicodeStr();

            unicodeEmojiList.add(unicodeStr);
            unicodeNullList.add("");
            String cn = "[" + emoji.getCn() + "]";
            String img = emoji.getImg();
            unicodeCnList.add(cn);

            String sbUnicode = emoji.getSbUnicode();
            if (StringUtils.isNotBlank(sbUnicode)) {
                sbCodeList.add(emoji.getSbUnicodeStr());
                sbCode2UnicodeList.add(unicodeStr);
            }
        }

        unicodeEmojis = unicodeEmojiList.toArray(new String[]{});
        unicodeNulls = unicodeNullList.toArray(new String[]{});
        unicodeCns = unicodeCnList.toArray(new String[]{});

        sbCodes = sbCodeList.toArray(new String[]{});
        sbCode2Unicodes = sbCode2UnicodeList.toArray(new String[]{});
    }

    /**
     * 将emoji表情替换成[表情]的形式
     *
     * @param s
     * @return
     */
    public static String replace2cn(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        //去除ios7中的FEOF,以兼容ios5,ios6
        s = s.replaceAll("\uFE0F", "");
        //softbank
        s = StringUtils.replaceEach(s, sbCodes, sbCode2Unicodes);
        //ios5,6,7
        s = StringUtils.replaceEach(s, unicodeEmojis, unicodeCns);

        s = s.replaceAll("[\uE000-\uF8FF]", "");
        return s;
    }

    /**
     * 删除字符串中的emoji表情
     *
     * @param s
     * @return
     */
    public static String remove(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        //去除ios7中的FEOF,以兼容ios5,ios6
        s = s.replaceAll("\uFE0F", "");

        //softbank
        s = s.replaceAll("[\uE000-\uF8FF]", "");

        //ios5,6,7
        s = StringUtils.replaceEach(s, unicodeEmojis, unicodeNulls);
        return s;
    }
}
