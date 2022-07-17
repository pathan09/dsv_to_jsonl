package com.pathan;

import com.opencsv.CSVReader;
import java.io.*;
import java.text.ParseException;

public class App {
    public static void main(String[] args) {
        if (args.length >= 3) {
            String sourceFilePath = args[0];
            String destinationFilePath = args[1];
            char separator = args[2].charAt(0);
            DsvToJsonL dsvToJson = new DsvToJsonL();

            try {
                CSVReader csv = dsvToJson.readTextFile(sourceFilePath, separator);
                dsvToJson.convertData(csv, destinationFilePath);
                System.out.println("DSV to JSONL Conversion Successful");
            } catch (IOException e) {
                System.out.println("Error while reading a file." + e.getMessage());
            } catch (ParseException e) {
                System.out.println("Error while parsing." + e.getMessage());
            }
        }else{
            System.out.println("Provide source, destination and separator as in arguments");
        }
    }
}
