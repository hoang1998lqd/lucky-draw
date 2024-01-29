package com.fis.demo.repository;

import com.fis.demo.domain.Employee;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    @Query(value = "select * from lucky.employee \n" +
        "where \n" +
        "status = 0 and (\n" +
        "department = 'ES AB1' \n" +
        "        or department = 'ES AB13' \n" +
        "        or department = 'ES AB2' \n" +
        "        or department = 'ES AB5' \n" +
        "        or department = 'ES AB6' \n" +
        "        or department = 'ES BCIS'\n" +
        "         or department = 'ES BDC' \n" +
        "         or department = 'ES HN GA' \n" +
        "         or department = 'ES HNDB' \n" +
        "         or department = 'ES OM'  )", nativeQuery = true)
    List<Employee> getGroupOne();

    @Query(value = "select * from lucky.employee \n" +
        "where status = 0 and \n" +
        "( department = 'ES PB1' \n" +
        "or department = 'ES PB18' \n" +
        "or department = 'ES PB23' )   ", nativeQuery = true)
    List<Employee> getGroupTwo();
    @Query(value = "select * from lucky.employee \n" +
        "where  status = 0 and \n" +
        "( department = 'ES PB2' \n" +
        "or department = 'ES PB8' )  \n", nativeQuery = true)
    List<Employee> getGroupThree();
    @Query(value = "select * from lucky.employee \n" +
        "where status = 0 and \n" +
        "( department = 'ES PB20' \n" +
        "or department = 'ES PB3' \n" +
        "or department = 'ES PB9' \n" +
        "or department = 'ES PB22' ) ", nativeQuery = true)
    List<Employee> getGroupFour();

    @Query(value = "select * from lucky.employee \n" +
        "where status = 0 and \n" +
        "( department = 'ES PB5' \n" +
        "or (department = 'ES PB6' and child_department = 'ES PB6 CS' ) ) ", nativeQuery = true)
    List<Employee> getGroupFive();
    @Query(value = "select * from lucky.employee \n" +
        "where status = 0 and \n" +
        "( department = 'ES PBDXGHN' \n" +
        "or (department = 'ES PB6' and child_department = 'ES PB6DELV')\n" +
        "or (department = 'ES PB6' and child_department = 'ES PB6') ) ", nativeQuery = true)
    List<Employee> getGroupSix();
    @Query(value = "select * from lucky.employee \n" +
        "where status = 0 and \n" +
        "( (department = 'ES PB6' and child_department = 'ES PB6PROD')\n" +
        "or (department = 'ES PB6' and child_department = 'ES PB6SALE') ) ", nativeQuery = true)
    List<Employee> getGroupSeven();
}
