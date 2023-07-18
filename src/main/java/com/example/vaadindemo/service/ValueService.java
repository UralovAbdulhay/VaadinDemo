package com.example.vaadindemo.service;

import com.example.vaadindemo.entity.Value;
import com.example.vaadindemo.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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

    public Integer getValue() {
        Optional<Value> byId = valueRepository.findById(VALUE_ID);
        return byId.orElse(new Value(1L, 0)).getValue();
    }

    public void updateValue(Integer value) {
        valueRepository.updateValue(VALUE_ID, value);
    }


}
