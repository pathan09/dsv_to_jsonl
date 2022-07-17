package com.pathan;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DsvToJsonLTest {

    private final DsvToJsonL dsvToJsonL = new DsvToJsonL();
    @Test
    @DisplayName("Read Text File")
    void readTextFile() throws IOException {
        String sourceFilePath = "src/test/resources/input1.txt";
        FileReader filereader = new FileReader(sourceFilePath);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csv = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();
        CSVReader csvReader = dsvToJsonL.readTextFile(sourceFilePath, ',');
        String[] expected = csv.readNext();
        String[] actual = csvReader.readNext();
        assertEquals(expected[0], actual[0] );

        String sourceFilePath2 = "src/test/resources/input2.txt";
        filereader = new FileReader(sourceFilePath);
        parser = new CSVParserBuilder().withSeparator('|').build();
        csv = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();
        csvReader = dsvToJsonL.readTextFile(sourceFilePath2, '|');
        expected = csv.readNext();
        actual = csvReader.readNext();
        assertEquals(expected[0], actual[0] );
    }

    @Test
    void convertData() throws IOException, ParseException {
        String input1 = "src/test/resources/input1.txt";
        String input2 = "src/test/resources/input2.txt";
        String output = "src/test/resources";
        FileReader filereader = new FileReader(input1);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csv = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();
        dsvToJsonL.convertData(csv, output);

        File file = new File(output+"\\output.JSONL");
        assertTrue(file.exists());

    }

}