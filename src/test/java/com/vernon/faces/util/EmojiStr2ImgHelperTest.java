package com.vernon.faces.util;

import com.vernon.faces.EmojiStr2ImgHelper;

import static org.junit.Assert.assertEquals;

/**
 * xxx
 * User: chenyuan
 * Date: 2013-12-27
 */
public class EmojiStr2ImgHelperTest {
    @org.junit.Test
    public void testCn2Img() throws Exception {
        //cn2ImgMap.put("[伤心]", "59.gif");
        //cn2ImgMap.put("[猪头]", "60.gif");

        String src = "[伤心]";
        String output = "<img src=\"59.gif\"/>";
        assertEquals(output, EmojiStr2ImgHelper.str2Img(src, "<img src=\"", "\"/>"));

        src = "c[伤心]dwada[猪头]awda[猪头]aa";
        output = "c[59.gif]dwada[60.gif]awda[60.gif]aa";
        assertEquals(output, EmojiStr2ImgHelper.str2Img(src, "[", "]"));


        src = "c[伤心]dwa[da[猪头]awda[[猪头]aa";
        output = "c[59.gif]dwa[da[60.gif]awda[[60.gif]aa";
        assertEquals(output, EmojiStr2ImgHelper.str2Img(src, "[", "]"));

        src = "[伤心]dwa[da[猪头]awda[[猪头";
        output = "[59.gifdwa[da[60.gifawda[[猪头";
        assertEquals(output, EmojiStr2ImgHelper.str2Img(src, "[", null));

        src = "[伤心]d估计da[猪头]awda[[猪头[[";
        output = "<img src=\"http://img.dianziq.com/img/59.gif\"/>d估计da<img src=\"http://img.dianziq.com/img/60.gif\"/>awda[[猪头[[";
        assertEquals(output, EmojiStr2ImgHelper.str2Img(src, "<img src=\"http://img.dianziq.com/img/", "\"/>"));


        src = "[em眼睛]";
        output = "<img src=\"1f440.png\"/>";
        assertEquals(output, EmojiStr2ImgHelper.str2Img(src, "<img src=\"", "\"/>"));
    }
}
