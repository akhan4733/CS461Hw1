package org.example.pathFinding;

import org.example.city.City;
import org.example.city.Connection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IdDfs extends Abstract {

    public IdDfs(List<City> cityList, List<Connection> connectionList) {
        super(cityList, connectionList);
    }

    private boolean depthFirstSearch(City currentCity, City endCity, Set<City> visited, List<City> path, int maxDepth) {
        if (currentCity.equals(endCity)) {
            path.add(currentCity);
            return true; // Found a path
        }

        if (maxDepth == 0) {
            return false; // this has reached max depth without using the distance
        }

        visited.add(currentCity);

        for (City neighbor : Adjacency.get(currentCity)) {
            if (!visited.contains(neighbor)) {
                path.add(currentCity);
                if (depthFirstSearch(neighbor, endCity, visited, path, maxDepth - 1)) {
                    return true; // Found a path
                }
                path.remove(path.size() - 1);
            }
        }

        return false;
    }

    @Override
    public List<City> findShortestPath(City startCity, City endCity) {
        int maxDepth = 1;
        List<City> path = new ArrayList<>();

        while (true) {
            Set<City> visited = new HashSet<>();
            if (depthFirstSearch(startCity, endCity, visited, path, maxDepth)) {
                return path;
            }
            maxDepth++;
        }
    }
}

