package com.fis.demo.web.rest;

import com.fis.demo.domain.Employee;
import com.fis.demo.repository.EmployeeRepository;
import com.fis.demo.util.DateUtil;
import com.fis.demo.util.ExcelHelper;
import com.fis.demo.util.ExcelService;
import com.fis.demo.util.ResponseMessage;
import com.fis.demo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link com.fis.demo.domain.Employee}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EmployeeResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private static final String ENTITY_NAME = "employee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;
    @Autowired
    ExcelService fileService;

    private final EmployeeRepository employeeRepository;

    public EmployeeResource(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message;

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                fileService.saveEmployee(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PostMapping("/checkin")
    public ResponseEntity<ResponseMessage> checkIn(@RequestBody Map<String, Object> params) throws ParseException {
        String message;
        Optional<Employee> employee = employeeRepository.findByEmail(params.get("email").toString().toLowerCase(Locale.ENGLISH));
        if (employee.isPresent()) {
            employee.get().setCheckIn(DateUtil.convertStringToDate(params.get("checkIn").toString(), DateUtil.YEAR_MONTH_TIME).toInstant());
            employeeRepository.save(employee.get());
            message = "Checked in successfully!";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }

        message = "Checked error!";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage(message));
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        // Nhom 1:
        List<Employee> groupOne = employeeRepository.getGroupOne().stream()
            .filter(employee -> employee.getCheckIn() != null )
            .collect(Collectors.toList());

        // Nhom 2:
        List<Employee> groupTwo = employeeRepository.getGroupTwo().stream()
            .filter(employee -> employee.getCheckIn() != null)
            .collect(Collectors.toList());;

        // Nhom 3:
        List<Employee> groupThree = employeeRepository.getGroupThree().stream()
            .filter(employee -> employee.getCheckIn() != null )
            .collect(Collectors.toList());;

        // Nhom 4:
        List<Employee> groupFour = employeeRepository.getGroupFour().stream()
            .filter(employee -> employee.getCheckIn() != null)
            .collect(Collectors.toList());;

        // Nhom 5:
        List<Employee> groupFive = employeeRepository.getGroupFive().stream()
            .filter(employee -> employee.getCheckIn() != null )
            .collect(Collectors.toList());;

        // Nhom 6:
        List<Employee> groupSix = employeeRepository.getGroupSix().stream()
            .filter(employee -> employee.getCheckIn() != null )
            .collect(Collectors.toList());;

        // Nhom 7:
        List<Employee> groupSeven = employeeRepository.getGroupSeven().stream()
            .filter(employee -> employee.getCheckIn() != null )
            .collect(Collectors.toList());

        Map<String, List<Employee>> map = new HashMap<>();
        map.put("1", groupOne);
        map.put("2", groupTwo);
        map.put("3", groupThree);
        map.put("4", groupFour);
        map.put("5", groupFive);
        map.put("6", groupSix);
        map.put("7", groupSeven);


        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
