package org.example;

import org.example.enums.OutputFileNameType;

import java.nio.file.Path;

public class CommandLineOptions {
    private final String outputPath;
    private final String prefix;
    private final boolean append;
    private final boolean isShortStatistics;
    private final boolean isFullStatistics;
    private final String[] inputFileNames;

    CommandLineOptions(String outputPath, String prefix, boolean append, boolean isShortStatistics, boolean isFullStatistics, String[] inputFileNames) {
        this.outputPath = outputPath != null ? outputPath : "./";
        this.prefix = prefix != null ? prefix : "";
        this.append = append;
        this.isShortStatistics = isShortStatistics;
        this.isFullStatistics = isFullStatistics;
        this.inputFileNames = inputFileNames;
    }

    public boolean getIsAppend() {
        return append;
    }

    public boolean getIsFullStatistics() {
        return isFullStatistics;
    }

    public boolean getIsShortStatistics() {
        return isShortStatistics;
    }

    public String[] getInputFileNames() {
        return inputFileNames;
    }

    public String getOutputFilePath(OutputFileNameType type) {
        return Path.of(outputPath, prefix + type).toString();
    }

    public String getOutputPath() {
        return outputPath;
    }
}
