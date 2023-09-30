package org.example.pathFinding;

import org.example.city.City;
import org.example.city.Connection;

import java.util.*;

public class BestFirstSearch extends Abstract {

    public BestFirstSearch(List<City> cityList, List<Connection> connectionList) {
        super(cityList, connectionList);
    }

    @Override
    public List<City> findShortestPath(City startCity, City endCity) {
        PriorityQueue<City> priorityQueue = new PriorityQueue<>((city1, city2) -> {
            double distance1 = PathFinding.calculateDistance(city1, endCity);
            double distance2 = PathFinding.calculateDistance(city2, endCity);
            return Double.compare(distance1, distance2);
        });

        Map<City, City> parentMap = new HashMap<>();
        Set<City> visited = new HashSet<>();

        priorityQueue.offer(startCity);
        visited.add(startCity);

        while (!priorityQueue.isEmpty()) {
            City currentCity = priorityQueue.poll();

            if (currentCity.equals(endCity)) {
                return PathFinding.reconstructPath(parentMap, startCity, endCity);
            }

            for (City neighbor : Adjacency.get(currentCity)) {
                if (!visited.contains(neighbor)) {
                    priorityQueue.offer(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, currentCity);
                }
            }
        }

        // If no path is found
        return new ArrayList<>();
    }
}
