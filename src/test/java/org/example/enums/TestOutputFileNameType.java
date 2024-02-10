package org.example.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestOutputFileNameType {
    @Test
    public void integers() {
        Assertions.assertEquals("integers.txt", OutputFileNameType.INTEGER.toString());
    }

    @Test
    public void floats() {
        Assertions.assertEquals("floats.txt", OutputFileNameType.FLOAT.toString());
    }

    @Test
    public void strings() {
        Assertions.assertEquals("strings.txt", OutputFileNameType.STRING.toString());
    }
}
