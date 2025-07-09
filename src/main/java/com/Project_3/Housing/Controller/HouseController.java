package com.Project_3.Housing.Controller;

import com.Project_3.Housing.Model.House;
import com.Project_3.Housing.Service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("houses")
public class HouseController {

    @Autowired
    private HouseService service;

    @PostMapping("upload")
    public ResponseEntity<String> uploadData(@RequestBody List<House> houses) {
        try {
            service.saveHouses(houses);
            return ResponseEntity.status(HttpStatus.CREATED).body("Data uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @GetMapping("average")
    public ResponseEntity<Map<String, Object>> getAvgPrice() {
        Double avg = service.getAvgPrice();
        return ResponseEntity.ok(Map.of("averagePrice", avg));
    }

    @GetMapping("average-by-location")
    public ResponseEntity<List<Map<String, Object>>> getAvgByLocation() {
        return ResponseEntity.ok(service.getAvgPricePerLocation());
    }

    @GetMapping("max")
    public ResponseEntity<Map<String, Object>> getMax() {
        return ResponseEntity.ok(Map.of("maxPrice", service.getMaxPrice()));
    }

    @GetMapping("min")
    public ResponseEntity<Map<String, Object>> getMin() {
        return ResponseEntity.ok(Map.of("minPrice", service.getMinPrice()));
    }

    @GetMapping("average/{location}")
    public ResponseEntity<?> getAvgByLocation(@PathVariable String location) {
        Double avg = service.getAvgPriceByLocation(location);
        if (avg == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found");
        }
        return ResponseEntity.ok(Map.of("location", location, "averagePrice", avg));
    }
}
