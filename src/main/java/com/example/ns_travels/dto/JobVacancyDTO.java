package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JobVacancyDTO {
    private Long id;
    private String title;
    private String company;
    private String location;
    private String salaryRange;
    private String requirements;
    private String contactInfo;

    public JobVacancyDTO() {
    }

    public JobVacancyDTO(Long id, String title, String company, String location, String salaryRange, String requirements, String contactInfo) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.salaryRange = salaryRange;
        this.requirements = requirements;
        this.contactInfo = contactInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "JobVacancyDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", salaryRange='" + salaryRange + '\'' +
                ", requirements='" + requirements + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
