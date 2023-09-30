package org.example.city;

//records all the cities using the longitude and the latitude
public record City(String name, double latitude, double longitude) {
    @Override
    public String toString() {
        return name + " -> ";
    } // lists the cities in order with the route
}

