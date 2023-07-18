package com.example.vaadindemo;

import com.example.vaadindemo.entity.Value;
import com.example.vaadindemo.service.ValueService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author a.uralov
 * on 18.07.2023 - 9:01
 * @project VaadinDemo
 */

@Route("/task")
public class CounterView extends VerticalLayout {

    private final ValueService valueService;
    private Value value;


    @Autowired
    public CounterView(ValueService valueService) {
        this.valueService = valueService;
        value = valueService.getValue();


        Binder<Value> binder = new Binder<>(Value.class);


        IntegerField textField = new IntegerField("Counter");

        binder.forField(textField).bind(Value::getValue, Value::setValue);

        textField.setMin(Integer.MIN_VALUE);
        textField.setMax(Integer.MAX_VALUE);
        textField.setStep(1);
        textField.setStepButtonsVisible(true);
        textField.setValueChangeMode(ValueChangeMode.EAGER);

        Button increaseBtn = new Button("+");
        Button decreaseBtn = new Button("-");


        textField.addValueChangeListener((e) -> {
            System.out.println(e.getSource().getValue());
            int current2 = textField.getValue() == null ? 0 : textField.getValue();
            updateValue(current2);
        });

        increaseBtn.addThemeVariants(ButtonVariant.MATERIAL_OUTLINED);
        increaseBtn.addClickListener(e -> {
            int current = textField.getValue() == null ? 0 : textField.getValue();
            textField.setValue(current + 1);
            int current2 = textField.getValue() == null ? 0 : textField.getValue();
            updateValue(current2);
        });

        decreaseBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
        decreaseBtn.addClickListener(e -> {
            int current = textField.getValue() == null ? 0 : textField.getValue();
            textField.setValue(current - 1);
            int current2 = textField.getValue() == null ? 0 : textField.getValue();
            updateValue(current2);

        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(decreaseBtn, increaseBtn);
        add(textField, horizontalLayout);

        setHorizontalComponentAlignment(Alignment.CENTER, textField, horizontalLayout);


    }

    private void updateValue(Integer integer) {
        valueService.updateValue(integer);

    }


}
