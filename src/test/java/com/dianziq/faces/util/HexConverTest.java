package com.dianziq.faces.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * xxx
 * User: chenyuan
 * Date: 2013-12-12
 */
public class HexConverTest {
    @Test
    public void testHexStringhexChar2Byte() throws Exception {
        byte[] bytes = HexConver.hexStrToBytes("1112");
        assertEquals(17, bytes[0]);
        assertEquals(18, bytes[1]);

        bytes = HexConver.hexStrToBytes("0AA0");
        assertEquals(10, bytes[0]);
        assertEquals(-96, bytes[1]);
    }

    @Test
    public void testhexChar2Byte() throws Exception {
        assertEquals(0, HexConver.hexChar2Byte('0'));
        assertEquals(1, HexConver.hexChar2Byte('1'));
        assertEquals(2, HexConver.hexChar2Byte('2'));
        assertEquals(3, HexConver.hexChar2Byte('3'));
        assertEquals(10, HexConver.hexChar2Byte('A'));
        assertEquals(11, HexConver.hexChar2Byte('B'));
        assertEquals(12, HexConver.hexChar2Byte('C'));
        assertEquals(13, HexConver.hexChar2Byte('D'));
        assertEquals(14, HexConver.hexChar2Byte('E'));
    }
}
