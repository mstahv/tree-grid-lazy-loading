package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<DirectReportsDto> findAll(int skip, int limit, Set<DirectReportsDto> skipSubtree) {
        String skippedSubTreesSql = skipSubtree.isEmpty() ?
                "" :
                "managerId NOT IN (%s) AND "
                .formatted(String.join(",", skipSubtree.stream().map(i -> i.getEmployeeId().toString()).toList()));

        return jdbcTemplate.query("""
            SELECT
                emp.managerId,
                emp.employeeId,
                emp.firstName,
                emp.lastName,
                emp.title,
                (SELECT COUNT(*) FROM employees WHERE managerId = emp.employeeId) AS directReports,
                -- connect by specific pseudo columns as an example, can be handy 
                LEVEL,
                CONNECT_BY_ISLEAF,
                SYS_CONNECT_BY_PATH(employeeId, '/') path
                
            FROM employees emp
            START WITH managerId IS NULL
            CONNECT BY %s PRIOR employeeId = managerId
            OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
            """.formatted(skippedSubTreesSql), (rs, rowNum) ->
                    new DirectReportsDto(
                            rs.getInt("LEVEL") -1,
                            rs.getBoolean("CONNECT_BY_ISLEAF"),
                            rs.getString("path"),
                            rs.getInt("directReports"),
                            rs.getInt("employeeId"),
                            rs.getInt("managerId"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("title"))

                // JDBC parameters
                , skip, limit
        );

    }


}
