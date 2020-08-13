package com.baeldung.binary.numbers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Java 7 introduced the binary literal. It simplified binary number usage.
 *
 * To use it, we need to prefix the number with 0B or 0b:
 */
public class BinaryNumbersTest {

    @Test
    public void given_binaryLiteral_thenReturnDecimalValue() {
        byte five = 0b101;
        assertEquals( (byte) 5, five);

        short three = 0b11;
        assertEquals((short) 3, three);

        int nine = 0B1001;
        assertEquals(9, nine);

        long twentyNine = 0b11101;
        assertEquals(29, twentyNine);

        int minusThirtySeven = -0B100101;
        assertEquals(-37, minusThirtySeven);
    }

    @Test
    public void given_decimalNumber_then_convertToBinaryNumber() {
        assertEquals("1000", Integer.toBinaryString(8));
        assertEquals("101", Integer.toBinaryString(5));
    }

    @Test
    public void given_decimalNumberEqualsZero_thenReturnZero() {
        assertEquals("0", new BinaryNumbers().convertDecimalToBinaryString(0));
    }

    @Test
    public void given_decimalNumber_thenConvertWithOwnImplementation() {
        assertEquals("110", new BinaryNumbers().convertDecimalToBinaryString(6));
    }

    @Test
    public void given_binaryNumber_then_ConvertToDecimalNumber() {
        assertEquals(8, Integer.parseInt("1000", 2));
        assertEquals(20, Integer.parseInt("10100", 2));
    }

    @Test
    public void given_binaryNumber_thenConverToWithOwnImplementation() {
        assertEquals(8, new BinaryNumbers().convertBinaryToDecimal(1000));
    }

}

