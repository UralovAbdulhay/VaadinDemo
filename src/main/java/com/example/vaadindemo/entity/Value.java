package com.example.vaadindemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/**
 * @author a.uralov
 * on 18.07.2023 - 14:12
 * @project VaadinDemo
 */

@Entity
@Table(name = "Value_s")
public class Value {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "value_s")
    Integer value;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;


    public Value(Long id, Integer value) {
        this.id = id;
        this.value = value;
        this.updatedAt = LocalDateTime.now();
    }

    public Value() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Value{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
