package com.fis.demo.util;

import com.fis.demo.domain.Employee;
import com.fis.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
//  @Autowired
//  TutorialRepository repository;
  @Autowired
    EmployeeRepository employeeRepository;

//  public void save(MultipartFile file) {
//    try {
//      List<Tutorial> tutorials = ExcelHelper.excelToTutorials(file.getInputStream());
//      repository.saveAll(tutorials);
//    } catch (IOException e) {
//      throw new RuntimeException("fail to store excel data: " + e.getMessage());
//    }
//  }
    public void saveEmployee(MultipartFile file) {
        try {
            List<Employee> tutorials = ExcelHelper.excelToEmployees(file.getInputStream());
            employeeRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

//  public ByteArrayInputStream load() {
//    List<Tutorial> tutorials = repository.findAll();
//
//    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(tutorials);
//    return in;
//  }
//
//  public List<Tutorial> getAllTutorials() {
//    return repository.findAll();
//  }
}
