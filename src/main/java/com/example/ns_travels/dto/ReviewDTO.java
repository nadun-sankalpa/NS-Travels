package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class ReviewDTO {
    private Long id;
    private Long userId;
    private String entityType;
    private Long entityId;
    private double rating;
    private String comment;
    private LocalDate date;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, Long userId, String entityType, Long entityId, double rating, String comment, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.entityType = entityType;
        this.entityId = entityId;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", userId=" + userId +
                ", entityType='" + entityType + '\'' +
                ", entityId=" + entityId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                '}';
    }
}
