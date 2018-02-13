package com.wetio.dao;

import com.wetio.entity.City;
import com.wetio.entity.Travle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-7
 */

@Repository
public interface TravleDao {

    public List<Travle> getTravle();

    public List<Travle> getTravleByStartCity(String startCity);

    public Travle getTravleById(int id);

    public List<Travle> searchTravle(String search);

    public void updateTravleWayById(@Param("id")int id, @Param("way")String way);

    public void deleteTravleById(int id);

    public void insertTravle(@Param("company")String company, @Param("startCity")String startCity,
                             @Param("way")String way, @Param("destinations")int destinations,
                             @Param("beginTime")String beginTime);

    public List<City> getCity();

    public List<String> getStartCity();

    public void insertCity(@Param("name")String name, @Param("longitude")Double longitude,
                           @Param("latitude")Double latitude);

}
