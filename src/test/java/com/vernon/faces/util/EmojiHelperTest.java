package com.vernon.faces.util;

import com.vernon.faces.EmojiHelper;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * xxx
 * User: chenyuan
 * Date: 2013-12-29
 */
public class EmojiHelperTest {
    @org.junit.Test
    public void testReplace2cn() throws Exception {
        String s = "😏👷👵😺💧";
        String out = "[em假笑][em建筑工人][em老奶奶][em笑脸猫][em滴]";
        assertEquals(out, EmojiHelper.replace2cn(s));

        s = "😏11👷22👵33😺44💧cc";
        out = "[em假笑]11[em建筑工人]22[em老奶奶]33[em笑脸猫]44[em滴]cc";
        assertEquals(out, EmojiHelper.replace2cn(s));

        s = "\uE330基要c";
        out = "[em快跑]基要c";
        assertEquals(out, EmojiHelper.replace2cn(s));

        s = "cc\uD83C\uDDFA\uD83C\uDDF8基c";
        out = "cc[em美国国旗]基c";
        assertEquals(out, EmojiHelper.replace2cn(s));

        // TEST null
        assertEquals("", EmojiHelper.replace2cn(""));
        assertEquals(null, EmojiHelper.replace2cn(null));

        s = "\uD83D\uDC40]]";
        out = "[em眼睛]]]";
        assertEquals(out, EmojiHelper.replace2cn(s));

        s = "abcd";
        out = "abcd";
        assertEquals(out, EmojiHelper.replace2cn(s));

        s = "em美国国旗";
        out = "em美国国旗";
        assertEquals(out, EmojiHelper.replace2cn(s));

        s = "5678";
        out = "5678";
        assertEquals(out, EmojiHelper.replace2cn(s));
    }

    @Test
    public void testRemove() throws Exception {
        String s = "😏👷👵😺💧";
        String out = "";
        assertEquals(out, EmojiHelper.remove(s));

        s = "\u263a";
        out = "";
        assertEquals(out, EmojiHelper.remove(s));
    }

    @Test
    public void hasEmoji() throws Exception {
        String s = "😏👷👵😺💧";
        assertTrue(EmojiHelper.hasEmoji(s));
        assertFalse(EmojiHelper.hasEmoji(""));
        assertFalse(EmojiHelper.hasEmoji(null));
        assertFalse(EmojiHelper.hasEmoji("as,dasdsds"));
    }
}
