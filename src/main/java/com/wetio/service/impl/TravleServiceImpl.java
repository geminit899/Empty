package com.wetio.service.impl;

import com.wetio.dao.TravleDao;
import com.wetio.entity.City;
import com.wetio.entity.Travle;
import com.wetio.service.TravleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Geminit
 * @create 2018-2-7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TravleServiceImpl implements TravleService{

    @Autowired
    TravleDao travleDao;

    public List<Travle> getTravle() { return travleDao.getTravle(); }

    public List<Travle> getTravleByStartCity(String startCity) { return travleDao.getTravleByStartCity(startCity); }

    public Travle getTravleById(int id) { return travleDao.getTravleById(id); }

    public void updateTravleWayById(int id, String way) { travleDao.updateTravleWayById(id, way); }

    public void deleteTravleById(int id) { travleDao.deleteTravleById(id); }

    public void insertTravle(Travle travle) {
        travleDao.insertTravle(travle.getCompany(), travle.getStartCity(), travle.getWay(),
                                travle.getDestinations(), travle.getBeginTime());
    }

    public List<City> getCity() { return travleDao.getCity(); }

    public List<String> getStartCity() { return travleDao.getStartCity(); }

    public void insertCity(City city) {
        travleDao.insertCity(city.getName(), city.getLongitude(), city.getLatitude());
    }

}
