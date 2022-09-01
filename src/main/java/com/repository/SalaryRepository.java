package com.repository;

import com.data.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {

    @Query("SELECT u FROM Salary u WHERE u.province = ?1")
    public List<Salary> findSalaryByProvinceName(String userName);
}
