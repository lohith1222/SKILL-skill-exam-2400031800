package com.klef.soa.task.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "newspaper")
public class Newspaper
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newspaperid")
    private Long newspaperId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "type", length = 100, nullable = false)
    private String type;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Long getNewspaperId() {
        return newspaperId;
    }

    public void setNewspaperId(Long newspaperId) {
        this.newspaperId = newspaperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Newspaper [newspaperId=" + newspaperId +
                ", name=" + name +
                ", type=" + type +
                ", status=" + status +
                ", location=" + location + "]";
    }
}