package com.example.cloudlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatesRepository extends JpaRepository<Dates, Long> {
    void deleteByCarId(Long carId);
}
