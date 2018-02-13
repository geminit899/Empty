package com.wetio.service;

import com.wetio.entity.City;
import com.wetio.entity.Travle;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-7
 */

public interface TravleService {

    public List<Travle> getTravle();

    public List<Travle> getTravleByStartCity(String startCity);

    public Travle getTravleById(int id);

    public List<Travle> searchTravle(String search);

    public void updateTravleWayById(int id, String way);

    public void deleteTravleById(int id);

    public void insertTravle(Travle travle);

    public List<City> getCity();

    public List<String> getStartCity();

    public void insertCity(City city);

}
