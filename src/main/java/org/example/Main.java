package org.example;

import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {
        try {
            CommandLineOptions commandLineOptions = CommandLineParser.parseCommandLine(args);

            FilesParser filesParser = new FilesParser(commandLineOptions);
            filesParser.parseFiles();

        } catch (ParseException error) {
            System.err.println(error.getMessage());
        }
    }
}