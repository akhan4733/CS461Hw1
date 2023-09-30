package org.example.pathFinding;

import org.example.city.City;
import org.example.city.Connection;

import java.util.*;

public class DepthFirst extends Abstract {

    public DepthFirst(List<City> cityList, List<Connection> connectionList) {
        super(cityList, connectionList);
    }

    @Override
    public List<City> findShortestPath(City startCity, City endCity) {
        Set<City> visited = new HashSet<>();
        Map<City, City> parentMap = new HashMap<>();
        Stack<City> stack = new Stack<>();

        stack.push(startCity);
        visited.add(startCity);

        while (!stack.isEmpty()) {
            City currentCity = stack.pop();

            if (currentCity.equals(endCity)) {
                return PathFinding.reconstructPath(parentMap, startCity, endCity);
            }

            for (City neighbor : Adjacency.get(currentCity)) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, currentCity);
                }
            }
        }

        // If no path is found then use this
        return new ArrayList<>();
    }
}
