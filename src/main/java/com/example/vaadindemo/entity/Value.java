package com.example.vaadindemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author a.uralov
 * on 18.07.2023 - 14:12
 * @project VaadinDemo
 */

@Entity
@Table(name = "Value_s")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Value {

    @Id
    @Column(name = "id")
    String id;

    @Column(name = "value_s")
    Integer value;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;


    public Value(String id, Integer value) {
        this.id = id;
        this.value = value;
        this.updatedAt = LocalDateTime.now();
    }
}
