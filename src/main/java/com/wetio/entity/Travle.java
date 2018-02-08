package com.wetio.entity;

import java.util.Date;

public class Travle {

    private int id;
    private String company;
    private String startCity;
    private String way;
    private int destinations;
    private Date beginTime;

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() { return company;  }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public int getDestinations() { return destinations; }

    public void setDestinations(int destinations) {
        this.destinations = destinations;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }
}
