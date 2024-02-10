package org.example;

import org.example.enums.OutputFileNameType;
import org.example.errors.CreateWriterException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FilesParser {
    public final CommandLineOptions options;

    private final boolean shouldPrintStatistics;

    private int integersCount = 0;
    private int minInteger = Integer.MAX_VALUE;
    private int maxInteger = Integer.MIN_VALUE;
    private int sumOfIntegers = 0;

    private int floatsCount = 0;
    private double minFloat = Double.MAX_VALUE;
    private double maxFloat = Double.MIN_VALUE;
    private double sumOfFloats = 0;

    private int stringsCount = 0;
    private int minStringLength = Integer.MAX_VALUE;
    private int maxStringLength = 0;

    private final Map<OutputFileNameType, BufferedWriter> writers = new HashMap<>();

    FilesParser(CommandLineOptions options) {
        this.options = options;
        shouldPrintStatistics = options.getIsFullStatistics() || options.getIsShortStatistics();
    }

    public void parseFiles() {
        String[] fileNames = options.getInputFileNames();
        for (String fileName : fileNames) {
            try {
                parseFile(fileName);
            } catch (IOException error) {
                System.out.println("Ошибка при чтении файла '" + fileName + "': " + error.getMessage());
            }
        }

        printStatistics();

        closeWriters();
    }

    public void parseFile(String fileName) throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("Файл '" + fileName + "' не существует.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException error) {
            System.out.println("Ошибка при чтении файла '" + fileName + "': " + error.getMessage());
        }
    }

    private BufferedWriter createWriter(OutputFileNameType fileType) throws CreateWriterException {
        try {
            createDirectory();
        } catch (IOException e) {
            throw new CreateWriterException("Невозможно создать папку: '" + options.getOutputPath() + "'");
        }

        try {
            if (!writers.containsKey(fileType)) {
                String filePath = options.getOutputFilePath(fileType);
                FileWriter fileWriter = new FileWriter(filePath, options.getIsAppend());
                BufferedWriter writer = new BufferedWriter(fileWriter);
                writers.put(fileType, writer);
            }
            return writers.get(fileType);
        } catch (IOException error) {
            throw new CreateWriterException("Невозможно создать FileWriter для  " + fileType);
        }
    }

    private void createDirectory() throws IOException {
        String outputDirectoryName = options.getOutputPath();
        Path outputDirectoryPath = Paths.get(outputDirectoryName);

        if (!Files.exists(outputDirectoryPath)) {
            Files.createDirectory(outputDirectoryPath);
        }
    }

    private void closeWriters() {
        for (BufferedWriter writer : writers.values()) {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException error) {
                System.out.println("Ошибка при закрытии файла: " + error.getMessage());
            }
        }
    }

    private void processLine(String line) throws IOException {
        try {
            if (isInteger(line)) {
                int number = Integer.parseInt(line);
                writeIntoFile(OutputFileNameType.INTEGER, Integer.toString(number));
                integersCount++;
                sumOfIntegers += number;
                minInteger = Math.min(number, minInteger);
                maxInteger = Math.max(number, maxInteger);
            } else if (isFloat(line)) {
                double number = Double.parseDouble(line);
                writeIntoFile(OutputFileNameType.FLOAT, Double.toString(number));
                floatsCount++;
                sumOfFloats += number;
                minFloat = Math.min(number, minFloat);
                maxFloat = Math.max(number, maxFloat);
            } else {
                writeIntoFile(OutputFileNameType.STRING, line);
                stringsCount++;
                minStringLength = Math.min(line.length(), minStringLength);
                maxStringLength = Math.max(line.length(), maxStringLength);
            }
        } catch (Error e) {
            System.out.println("Ошибка при парсинге строки: '" + line + "'");
            throw e;
        }
    }

    private boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloat(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void writeIntoFile(OutputFileNameType fileType, String string) {
        try {
            BufferedWriter writer = createWriter(fileType);
            writer.write(string);
            writer.newLine();
        } catch (CreateWriterException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Невозможно записать значение " + fileType);
        }
    }

    private void printStatistics() {
        if (!shouldPrintStatistics) {
            return;
        }

        System.out.println("Статистика");

        System.out.println("Для целых чисел:");
        System.out.println("Количество: " + integersCount);
        if (options.getIsFullStatistics() && integersCount > 0) {
            System.out.printf("Минимальное значение: %d\nМаксимальное значение: %d\nСумма: %d\nСреднее: %g\n",
                    minInteger, maxInteger, sumOfIntegers, (double) sumOfIntegers / integersCount);

        }

        System.out.println("\nДля вещественных чисел:");
        System.out.println("Количество: " + floatsCount);
        if (options.getIsFullStatistics() && floatsCount > 0) {
            System.out.printf("Минимальное значение: %g\nМаксимальное значение: %g\nСумма: %g\nСреднее: %g\n",
                    minFloat, maxFloat, sumOfFloats, sumOfFloats / floatsCount);
        }

        System.out.println("\nДля строк:");
        System.out.println("Количество: " + stringsCount);
        if (options.getIsFullStatistics() && stringsCount > 0) {
            System.out.println("Минимальная длина: " + minStringLength);
            System.out.println("Максимальная длина: " + maxStringLength);
        }
    }
}
