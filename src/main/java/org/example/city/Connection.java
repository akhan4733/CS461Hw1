package org.example.city;

import org.example.pathFinding.PathFinding;

import java.util.Objects;



public class Connection {
    private final City city1;
    private final City city2;
    private final double distance;

    public Connection(City city1, City city2) { // calculating the distance
        this.city1 = city1;
        this.city2 = city2;
        distance = PathFinding.calculateDistance(city1, city2);
    }

    public City getCity1() {
        return city1;
    } //returns city1

    public City getCity2() {
        return city2;
    } //returns city2

    public double getDistance() {
        return distance;
    } //returns distance

    @Override
    public String toString() {
        return city1.name() + " ---> " + city2.name();
    } //showcases the best path with the arrows

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connection that)) return false;
        return Double.compare(that.distance, distance) == 0 && Objects.equals(city1, that.city1) && Objects.equals(city2, that.city2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city1, city2, distance);
    }
}
