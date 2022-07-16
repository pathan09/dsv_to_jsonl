package com.pathan;

import com.opencsv.CSVReader;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class App {
    static String sourceFilePath = "D:\\SourceCode\\input.txt";
    static String destinationFilePath = "D:\\SourceCode";
    static char separator = ',';

    public static void main(String[] args) {

        if (args.length >= 3){
            sourceFilePath = args[0];
            destinationFilePath = args[1];
            separator = args[2].charAt(0);
        }
        DsvToJsonL dsvToJson = new DsvToJsonL();

        try {
            CSVReader csv = dsvToJson.readTextFile(sourceFilePath, separator);
            List<String> result = dsvToJson.convertData(csv);
            dsvToJson.writeJsonLFile(result, destinationFilePath+"\\output.jsonl");
            System.out.println("DSV to JSONL Conversion Successful");
        } catch (IOException e) {
            System.out.println("Error while reading a file."+e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error while parsing." + e.getMessage());
        }

    }
}
