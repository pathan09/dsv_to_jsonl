package com.pathan;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DsvToJsonLTest {

    private final DsvToJsonL dsvToJsonL = new DsvToJsonL();
    @Test
    @DisplayName("Read Text File")
    void readTextFile() throws IOException {
        FileReader filereader = new FileReader(App.sourceFilePath);
        CSVParser parser = new CSVParserBuilder().withSeparator(App.separator).build();
        CSVReader csv = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();
        CSVReader csvReader = dsvToJsonL.readTextFile(App.sourceFilePath, App.separator);
        String[] expected = csv.readNext();
        String[] actual = csvReader.readNext();
        assertEquals(expected[0], actual[0] );
    }

    @Test
    void convertData() throws IOException, ParseException {
        FileReader filereader = new FileReader(App.sourceFilePath);
        CSVParser parser = new CSVParserBuilder().withSeparator(App.separator).build();
        CSVReader csv = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();
        CSVReader csvReader = dsvToJsonL.readTextFile(App.sourceFilePath, App.separator);
        String expected = csv.readNext()[0];
        List<String> result = dsvToJsonL.convertData(csvReader);
        Boolean actual = result.get(0).split(":")[0].contains(expected);
        assertEquals(Boolean.TRUE, actual);
    }

    @Test
    void writeJsonLFile() throws IOException {
        List<String> data = List.of((new String[]{"hello", "pathan"}));
        dsvToJsonL.writeJsonLFile(data, App.destinationFilePath+"\\output_test.jsonl");

        FileReader filereader = new FileReader(App.destinationFilePath+"\\output_test.jsonl");
        CSVReader csv = new CSVReaderBuilder(filereader)
                .build();

        String actual = csv.readNext()[0];

        assertEquals(data.get(0), actual);

    }
}