package org.example.fileReader;

import org.example.city.City;
import org.example.city.Connection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class CsvReader { //CSV reader and implements it

    private String fileName;

    public CsvReader(String fileName) {
        this.fileName = fileName;
    }

    public List<City> readValues() {
        Path filePath = null;
        try {
            filePath = Path.of(getClass().getClassLoader().getResource(fileName).toURI());
        }
        catch (Exception e) { //catches any exception
            e.printStackTrace();
        }
        try(Stream<String> lines = Files.lines(filePath)) {
            return lines.map(line -> {
                String[] splitArray = line.split("\\s*,\\s*");

                String name = splitArray[0];
                double latitude = Double.parseDouble(splitArray[1]);
                double longitude = Double.parseDouble(splitArray[2]);

                return new City(name, latitude, longitude);
            }).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

