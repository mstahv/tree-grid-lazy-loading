package com.example.demo;

/**
 * Dto for the SQL query.
 */
public class DirectReportsDto {

    private final Integer managerId;
    private final Integer employeeId;
    private final Integer directReports;
    private final String title;
    private final Integer level;
    private final String firstName;
    private final String lastName;
    private final boolean leaf;
    private final String path;

    public DirectReportsDto(Integer level, Boolean isleaf, String path, Integer directReports, Integer employeeId, Integer managerId,  String firstName, String lastName, String title) {
        this.leaf = isleaf;
        this.path = path;
        this.managerId = managerId;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.directReports = directReports;
        this.level = level;
    }

    public Integer getDirectReports() {
        return directReports;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public String getPath() {
        return path;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public Integer getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "FlatEmployeeDto{" +
                "managerId=" + managerId +
                ", employeeId=" + employeeId +
                ", title='" + title + '\'' +
                ", level=" + level +
                '}';
    }

}
