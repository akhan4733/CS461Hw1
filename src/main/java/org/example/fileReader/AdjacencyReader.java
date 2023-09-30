package org.example.fileReader;

import org.example.city.City;
import org.example.city.Connection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class AdjacencyReader { // reads the adjacency txt file
    private String fileName;

    public AdjacencyReader(String fileName) {
        this.fileName = fileName;
    } //picks up the file  and uses it

    public List<Connection> readValues(List<City> cityList) { //
        Path filePath = null;
        try {
            filePath = Path.of(getClass().getClassLoader().getResource(fileName).toURI());
        }
        catch (Exception e){ //catches the exception
            e.printStackTrace();
        }
        try(Stream<String> lines = Files.lines(filePath)) {
            return lines.map(line -> {
                String[] splitArray = line.split(" ");
                String cityNameOne = splitArray[0];
                String cityNameTwo = splitArray[1];//


                List<City> connectionCities = cityList.stream()
                        .filter(city -> city.name().equals(cityNameOne) || city.name().equals(cityNameTwo))
                        .toList();
                return new Connection(connectionCities.get(0), connectionCities.get(1)); //sum of the cities
            }).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
