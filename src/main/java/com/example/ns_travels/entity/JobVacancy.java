package com.example.ns_travels.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job_vacancies")
public class JobVacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String company;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String salaryRange;

    @Column(columnDefinition = "TEXT")
    private String requirements;
    @Column(nullable = false)
    private String contactInfo;

    public JobVacancy() {
    }

    public JobVacancy(Long id, String title, String company, String location, String salaryRange, String requirements, String contactInfo) {
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
        return "JobVacancy{" +
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
