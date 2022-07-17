package com.pathan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class DsvToJsonL {

    ManageDate manageDate = new ManageDate();

    CSVReader readTextFile(String source, char separator) throws FileNotFoundException {
        FileReader filereader = new FileReader(source);
        CSVParser parser = new CSVParserBuilder().withSeparator(separator).build();
        CSVReader csv = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();
        return csv;
    }

    void convertData(CSVReader csv, String outputFile) throws IOException, ParseException {
        String[] fieldNames = csv.readNext();
        String[] nextRecord;
        while ((nextRecord = csv.readNext()) != null) {
            Map<String, String> obj = new LinkedHashMap<>();
            for (int i = 0; i < fieldNames.length; i++) {
                if (!nextRecord[i].isEmpty() && manageDate.isDate(nextRecord[i])) {
                    String date= manageDate.stringToDate(nextRecord[i]);
                    obj.put(fieldNames[i], date);
                } else if (!nextRecord[i].isEmpty())
                    obj.put(fieldNames[i], nextRecord[i]);
            }
            ObjectMapper mapper = new ObjectMapper();
            writeJsonLFile(mapper.writeValueAsString(obj)+"\n", outputFile);
        }

    }

    void writeJsonLFile(String result, String destinationFilePath) {
        File file = new File(destinationFilePath+"\\output.JSONL");
        try (FileOutputStream fop = new FileOutputStream(file, true)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] contentInBytes = result.getBytes();
            fop.write(contentInBytes);
            fop.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
