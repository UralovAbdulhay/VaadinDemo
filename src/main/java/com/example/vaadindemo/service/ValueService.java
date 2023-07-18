package com.example.vaadindemo.service;

import com.example.vaadindemo.entity.Value;
import com.example.vaadindemo.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author a.uralov
 * on 18.07.2023 - 14:27
 * @project VaadinDemo
 */

@Service
public class ValueService implements Serializable {

    private final static String VALUE_ID = "VALUE_ID";

    private final ValueRepository valueRepository;

    @Autowired
    public ValueService(ValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }

    public Value getValue() {
        Optional<Value> byId = valueRepository.findById(VALUE_ID);
        return byId.orElse(new Value(ValueService.VALUE_ID, 0));
    }

    public void updateValue(Integer value) {
        valueRepository.save(new Value(VALUE_ID, value, LocalDateTime.now()));
    }

    public void updateValue(Value value) {
        value.setUpdatedAt(LocalDateTime.now());
        valueRepository.saveAndFlush(value);
    }


}
