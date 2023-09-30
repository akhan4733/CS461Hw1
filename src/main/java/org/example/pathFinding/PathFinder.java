package org.example.pathFinding;

import org.example.city.City;
import org.example.city.Connection;

import java.util.List;

public interface PathFinder {
    List<City> findShortestPath(City startCity, City endCity); //shortest path
}