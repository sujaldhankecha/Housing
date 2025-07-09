package com.Project_3.Housing.Service;

import com.Project_3.Housing.Model.House;
import com.Project_3.Housing.Repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseService {

    @Autowired
    private HouseRepository repository;

    public void saveHouses(List<House> houses) {
        repository.saveAll(houses);
    }

    public Double getAvgPrice() {
        return repository.getAveragePrice();
    }

    public List<Map<String, Object>> getAvgPricePerLocation() {
        List<Object[]> raw = repository.getAveragePricePerLocation();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Object[] row : raw) {
            Map<String, Object> map = new HashMap<>();
            map.put("location", row[0]);
            map.put("averagePrice", row[1]);
            result.add(map);
        }
        return result;
    }

    public Double getMaxPrice() {
        return repository.getMaxPrice();
    }

    public Double getMinPrice() {
        return repository.getMinPrice();
    }

    public Double getAvgPriceByLocation(String location) {
        return repository.getAveragePriceByLocation(location);
    }
}
