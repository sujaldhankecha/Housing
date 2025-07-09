package com.Project_3.Housing.Repository;

import com.Project_3.Housing.Model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {

    @Query(value = "select avg(saleprice) from house", nativeQuery = true)
    Double getAveragePrice();

    @Query(value = "select location, avg(saleprice) from house group by location", nativeQuery = true)
    List<Object[]> getAveragePricePerLocation();

    @Query(value = "select max(saleprice) from house", nativeQuery = true)
    Double getMaxPrice();

    @Query(value = "select min(saleprice) from house", nativeQuery = true)
    Double getMinPrice();

    @Query(value = "select avg(saleprice) from house where location = ?", nativeQuery = true)
    Double getAveragePriceByLocation(String location);
}
