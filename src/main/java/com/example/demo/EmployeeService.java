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

    public List<DirectReportsDto> findAll(int skip, int limit, Set<Integer> skipSubtree) {
        String skippedSubTreesSql = skipSubtree.isEmpty() ?
                "" :
                "WHERE e.managerId NOT IN (%s)"
                .formatted(String.join(",", skipSubtree.stream().map(i -> i.toString()).toList()));

        return jdbcTemplate.query("""
 WITH RECURSIVE DirectReports (path, managerId, employeeId, firstName, lastName, title, directReports, level)
    AS
    (
    -- Anchor member definition
        SELECT 
            TO_CHAR(e.employeeId, '00000'),
            e.managerId, 
            e.employeeId, 
            e.firstName,
            e.lastName,
            e.title,
            (SELECT COUNT(*) FROM employees WHERE managerId = e.employeeId),
            0 AS level
        FROM employees AS e
        WHERE managerId IS NULL
        UNION ALL
    -- Recursive member definition
        SELECT 
            -- to sort the results in the right order for UI, build a path that contains zero padded ids to the root
            CONCAT(d.path, '.' , TO_CHAR(e.employeeId, '00000')),
            e.managerId,
            e.employeeId,
            e.firstName,
            e.lastName,
            e.title,
            (SELECT COUNT(*) FROM employees WHERE managerId = e.employeeId),                            
            level + 1
        FROM employees AS e
        INNER JOIN DirectReports AS d
            ON e.managerId = d.employeeId
        -- skip subtree if hinted by the query (closed in the UI)
        %s
    )
    -- Statement that executes the CTE
    SELECT path, managerId, employeeId, firstName, lastName, title, directReports, level
    FROM DirectReports
    ORDER BY path, lastName
    
    LIMIT ? OFFSET ?
    ;
                """.formatted(skippedSubTreesSql), (rs, rowNum) ->
                    new DirectReportsDto(
                            rs.getString("path"),
                            rs.getInt("managerId"),
                            rs.getInt("employeeId"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getString("title"),
                            rs.getInt("directReports"),
                            rs.getInt("level"))
                ,
                // parameters
                limit,
                skip
        );

    }


}
