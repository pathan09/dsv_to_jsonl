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

    List<String> convertData(CSVReader csv) throws IOException, ParseException {
        String[] fieldNames = csv.readNext();
        String[] nextRecord;
        List<String> result = new ArrayList<>();
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
            result.add(mapper.writeValueAsString(obj));
        }
        return result;
    }

    void writeJsonLFile(List<String> result, String destinationFilePath) throws IOException {
        FileWriter outFile = new FileWriter(destinationFilePath, true);
        BufferedWriter outStream = new BufferedWriter(outFile);
        for (int i = 0; i < result.size(); i++) {
            outStream.write(result.get(i) + "\n");
        }
        outStream.close();
    }
}
