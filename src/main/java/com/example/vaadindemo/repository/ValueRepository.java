package com.example.vaadindemo.repository;

import com.example.vaadindemo.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author a.uralov
 * on 18.07.2023 - 14:30
 * @project VaadinDemo
 */

public interface ValueRepository extends JpaRepository<Value, String> {

    @Override
    Optional<Value> findById(String s);

    @Query(nativeQuery = true, value = "update Value_s set value_s = :value, updated_at = :updatedAt where id = :id")
    void updateValue(@Param("id") String id, @Param("value") Integer value, LocalDateTime updatedAt);
}
