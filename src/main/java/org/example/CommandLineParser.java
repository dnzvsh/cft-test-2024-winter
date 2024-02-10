package org.example;

import org.apache.commons.cli.*;

public class CommandLineParser {
    static CommandLineOptions parseCommandLine(String[] args) throws ParseException {
        final var options = new Options()
                .addOption("o", "outputPath", true, "Path for output files.")
                .addOption("p", "prefix", true, "Prefix for output files.")
                .addOption("a", "append", false, "Append data to existing files.")
                .addOption("s", "short", false, "Short statistics output.")
                .addOption("f", "full", false, "Full statistics output.");

        try {
            var parser = new DefaultParser();
            CommandLine cmdLine = parser.parse(options, args);

            String outputPath = cmdLine.getOptionValue('o', "");
            String prefix = cmdLine.getOptionValue('p', "");
            boolean append = cmdLine.hasOption('a');
            boolean isShortStatistics = cmdLine.hasOption('s');
            boolean isFullStatistics = cmdLine.hasOption('f');

            var fileNames = cmdLine.getArgs();

            return new CommandLineOptions(outputPath, prefix, append, isShortStatistics, isFullStatistics, fileNames);
        } catch (ParseException e) {
            new HelpFormatter().printHelp("Аргументы:", options);
            throw new ParseException("Невозможно обработать аргументы командной строки, попробуйте еще раз.");
        }
    }
}
