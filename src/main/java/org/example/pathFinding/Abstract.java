package org.example.pathFinding;

import org.example.city.City;
import org.example.city.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Abstract implements PathFinder {

    protected Map<City, List<City>> Adjacency;

    protected Abstract(List<City> cityList, List<Connection> connectionList) {
        Adjacency = new HashMap<>();
        cityList.forEach(this::addCity);
        connectionList.forEach(this::addCityConnection);
    }

    private void addCity(City city) {
        Adjacency.put(city, new ArrayList<>());
    }

    private void addCityConnection(City cityOne, City cityTwo) {
        Adjacency.get(cityOne).add(cityTwo);
        Adjacency.get(cityTwo).add(cityOne);
    }

    private void addCityConnection(Connection connection) {
        addCityConnection(connection.getCity1(), connection.getCity2());
    }
}
