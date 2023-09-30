package org.example.pathFinding;

import org.example.city.City;
import org.example.city.Connection;

import java.util.*;

public class AStar extends Abstract {

    public AStar(List<City> cityList, List<Connection> connectionList) {
        super(cityList, connectionList);
    }

    private List<City> reconstructPath(Map<City, City> cameFrom, City presentCity) {
        List<City> path = new ArrayList<>();
        while (cameFrom.containsKey(presentCity)) {
            path.add(presentCity);
            presentCity = cameFrom.get(presentCity);
        }
        path.add(presentCity);
        Collections.reverse(path);
        return path;
    }


    @Override
    public List<City> findShortestPath(City startCity, City endCity) {
        PriorityQueue<City> openSet = new PriorityQueue<>((city1, city2) -> {
            double f1 = PathFinding.calculateDistance(city1, endCity) +
                    PathFinding.calculateDistance(startCity, city1);
            double f2 = PathFinding.calculateDistance(city2, endCity) +
                    PathFinding.calculateDistance(startCity, city2);
            return Double.compare(f1, f2);
        });

        Map<City, City> cameFrom = new HashMap<>();
        Map<City, Double> gScore = new HashMap<>();
        openSet.offer(startCity);
        gScore.put(startCity, 0.0);

        while (!openSet.isEmpty()) {
            City currentCity = openSet.poll();

            if (currentCity.equals(endCity)) {
                return reconstructPath(cameFrom, currentCity);
            }

            for (City neighbor : Adjacency.get(currentCity)) {
                double tentativeGScore = gScore.getOrDefault(currentCity, Double.MAX_VALUE)
                        + PathFinding.calculateDistance(currentCity, neighbor);

                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    cameFrom.put(neighbor, currentCity);
                    gScore.put(neighbor, tentativeGScore);
                    double fScore = tentativeGScore + PathFinding.calculateDistance(neighbor, endCity);
                    openSet.offer(neighbor);
                }
            }
        }

        // If no path is found
        return new ArrayList<>();
    }
}

