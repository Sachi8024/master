package com.javatrain.conronovirustracker.models;

public class LocationStats {

    private String state;
    private String country;
    private int totalDeaths;
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }
    public int getTotalDeaths() {
        return totalDeaths;
    }
    
    @Override
    public String toString() {
        return "LocationStats [country=" + country + ", state=" + state + ", totalDeaths=" + totalDeaths + "]";
    }
    
    
}
