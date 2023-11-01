package com.example.demo;

import java.util.Objects;

/**
 * Dto for the SQL query.
 */
public class DirectReportsDto {

    private String managerPath;
    private final Integer directReports;
    private final String title;
    private final String firstName;
    private final String lastName;

    public DirectReportsDto(String path, String firstName, String lastName, String title, Integer directReports) {
        this.managerPath = path;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.directReports = directReports;
    }

    public Integer getDirectReports() {
        return directReports;
    }

    public String getManagerPath() {
        return managerPath;
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
        return managerPath.split("\\.").length - 1;
    }

    @Override
    public String toString() {
        return "DirectReportsDto{" +
                "managerPath='" + managerPath + '\'' +
                ", directReports=" + directReports +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectReportsDto that = (DirectReportsDto) o;
        return Objects.equals(managerPath, that.managerPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerPath);
    }
}
