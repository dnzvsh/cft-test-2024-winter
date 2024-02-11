package org.example.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCheckType {
    @Test
    public void isIntegerSimple() {
        Assertions.assertTrue(CheckType.isInteger("42"));
    }

    @Test
    public void isIntegerZero() {
        Assertions.assertTrue(CheckType.isInteger("0"));
    }

    @Test
    public void isIntegerNegative() {
        Assertions.assertTrue(CheckType.isInteger("-42"));
    }


    @Test
    public void isIntegerBig() {
        Assertions.assertTrue(CheckType.isInteger(String.valueOf(Integer.MAX_VALUE)));
    }

    @Test
    public void isIntegerBigNegative() {
        Assertions.assertTrue(CheckType.isInteger(String.valueOf(Integer.MIN_VALUE)));
    }

    @Test
    public void isIntegerStartsWithZero() {
        Assertions.assertTrue(CheckType.isInteger("007"));
    }

    @Test
    public void isIntegerTooBig() {
        Assertions.assertFalse(CheckType.isInteger("999999999999999999999999999999999"));
    }

    @Test
    public void isIntegerWithLetter() {
        Assertions.assertFalse(CheckType.isInteger("32t2"));
    }

    @Test
    public void isIntegerWithDot() {
        Assertions.assertFalse(CheckType.isInteger("32.2"));
    }


    @Test
    public void isFloatSimple() {
        Assertions.assertTrue(CheckType.isFloat("42.32"));
    }

    @Test
    public void isFloatZero() {
        Assertions.assertTrue(CheckType.isFloat("0.0"));
    }

    @Test
    public void isFloatNegative() {
        Assertions.assertTrue(CheckType.isFloat("-42.42"));
    }

    @Test
    public void isFloatBig() {
        Assertions.assertTrue(CheckType.isFloat(String.valueOf(Float.MAX_VALUE)));
    }

    @Test
    public void isFloatBigNegative() {
        Assertions.assertTrue(CheckType.isFloat(String.valueOf(Float.MIN_VALUE)));
    }

    @Test
    public void isFloatWithE() {
        Assertions.assertTrue(CheckType.isFloat("1.528535047E-25"));
    }
}
