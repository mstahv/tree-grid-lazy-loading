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
                "WHERE NOT subpath(e.managerPath, 0, -1) <@ ARRAY[%s]"
                .formatted(String.join(",", skipSubtree.stream().map(s -> "text2ltree('%s')".formatted(s.getManagerPath())).toList()));

        return jdbcTemplate.query("""
    SELECT 
        e.managerPath, 
        e.firstName, 
        e.lastName, 
        e.title,
        (SELECT COUNT(*) FROM employees WHERE subpath(managerPath, 0, -1) = e.managerPath) directReports
    FROM employees e
    -- skip subtrees closed in the UI
    %s
    ORDER BY e.managerPath, e.lastName
    LIMIT ? OFFSET ?
    ;
                """.formatted(skippedSubTreesSql), (rs, rowNum) ->
                    new DirectReportsDto(
                            rs.getString("managerPath"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("title"),
                            rs.getInt("directReports"))
                ,
                // parameters
                limit,
                skip
        );

    }


}
