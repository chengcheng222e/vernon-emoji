package com.dianziq.faces;

import com.dianziq.faces.util.Emoji;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * xxx
 * User: chenyuan
 * Date: 2013-12-29
 */
public class EmojiData {
    private static List<Emoji> emojiList;

    static {
        try {
            emojiList = new ArrayList<Emoji>();
            //U+1F604;F0 9F 98 84;U+D83D U+DE04;E415;SMILING FACE WITH OPEN MOUTH AND SMILING EYES;smile;em哈哈;
            InputStream is = EmojiHelper.class.getResourceAsStream("/emoji_all.txt");
            BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(is));
            String instring;
            while ((instring = bufferedreader.readLine()) != null) {
                String oneLine = instring.trim();
                if (oneLine != null && 0 != oneLine.length()) {
                    String[] emojiArray = oneLine.split(";");
                    if (emojiArray.length >= 7) {
                        Emoji emoji = new Emoji();
                        emoji.setUnicode(emojiArray[0].toUpperCase());
                        emoji.setUtf8(emojiArray[1]);
                        emoji.setUtf16(emojiArray[2]);
                        emoji.setSbUnicode(emojiArray[3]);
                        emoji.setName(emojiArray[4]);
                        emoji.setShortName(emojiArray[5]);
                        emoji.setCn(emojiArray[6]);
                        emojiList.add(emoji);
                    }
                }
            }
            is.close();
            bufferedreader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Emoji> getEmojiList() {
        return emojiList;
    }
}
