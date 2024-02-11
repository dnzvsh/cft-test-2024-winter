package org.example;

import org.example.enums.OutputFileNameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCommandLineOptions {
    private CommandLineOptions commandLineOptions;
    private final String[] inputFiles = {"in.txt", "in2.txt"};

    @BeforeEach
    void setUp() {
        commandLineOptions = new CommandLineOptions("result", "my_",
                true, false, true, inputFiles);
    }

    @Test
    void getOutputPath() {
        Assertions.assertEquals("result", commandLineOptions.getOutputPath());
    }

    @Test
    void getOutputPathWithNull() {
        var commandLineOptions = new CommandLineOptions(null, "my_",
                true, false, true, inputFiles);
        Assertions.assertEquals("./", commandLineOptions.getOutputPath());
    }

    @Test
    void getIsAppend() {
        Assertions.assertTrue(commandLineOptions.getIsAppend());
    }

    @Test
    void getIsShortStatistics() {
        Assertions.assertFalse(commandLineOptions.getIsShortStatistics());
    }

    @Test
    void getIsFullStatistics() {
        Assertions.assertTrue(commandLineOptions.getIsFullStatistics());
    }

    @Test
    void getInputFileNames() {
        Assertions.assertArrayEquals(inputFiles, commandLineOptions.getInputFileNames());
    }

    @Test
    void getOutputFilePathForStringWithoutPrefix() {
        var commandLineOptions = new CommandLineOptions("result", null,
                true, false, true, inputFiles);
        Assertions.assertEquals("result/strings.txt", commandLineOptions.getOutputFilePath(OutputFileNameType.STRING));
    }

    @Test
    void getOutputFilePathForStringWithoutOutputPath() {
        var commandLineOptions = new CommandLineOptions(null, "my_",
                true, false, true, inputFiles);
        Assertions.assertEquals("./my_strings.txt", commandLineOptions.getOutputFilePath(OutputFileNameType.STRING));
    }

    @Test
    void getOutputFilePathForStringWithoutOutputPathAndPrefix() {
        var commandLineOptions = new CommandLineOptions(null, null,
                true, false, true, inputFiles);
        Assertions.assertEquals("./strings.txt", commandLineOptions.getOutputFilePath(OutputFileNameType.STRING));
    }

    @Test
    void getOutputFilePathForString() {
        Assertions.assertEquals("result/my_strings.txt", commandLineOptions.getOutputFilePath(OutputFileNameType.STRING));
    }

    @Test
    void testGetOutputFilePathForInteger() {
        Assertions.assertEquals("result/my_integers.txt", commandLineOptions.getOutputFilePath(OutputFileNameType.INTEGER));
    }

    @Test
    void testGetOutputFilePathForFloat() {
        Assertions.assertEquals("result/my_floats.txt", commandLineOptions.getOutputFilePath(OutputFileNameType.FLOAT));
    }
}
