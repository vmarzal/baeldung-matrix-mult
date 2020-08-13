package com.baeldung.binary.numbers;

public class BinaryNumbers {

    public String convertDecimalToBinaryString(Integer decimalNumber) {

        if (decimalNumber == 0) {
            return "0";
        }

        StringBuilder binaryNumber = new StringBuilder();
        Integer quotient = decimalNumber;

        while (quotient > 0) {
            int remainder = quotient % 2;
            binaryNumber.append(remainder);
            quotient /= 2;
        }
        binaryNumber = binaryNumber.reverse();
        return binaryNumber.toString();
    }

    public Integer convertBinaryToDecimal(Integer binaryNumber) {
        Integer decimalNumber = 0;
        Integer base = 1;

        while (binaryNumber > 0) {
            int lastDigit = binaryNumber % 10;
            binaryNumber = binaryNumber / 10;
            decimalNumber += lastDigit * base;
            base = base * 2;
        }
        return decimalNumber;

    }
}
