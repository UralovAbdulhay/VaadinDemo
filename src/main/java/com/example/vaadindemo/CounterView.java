package com.example.vaadindemo;

import com.example.vaadindemo.entity.Value;
import com.example.vaadindemo.service.ValueService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
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


    @Autowired
    public CounterView(ValueService valueService) {
        this.valueService = valueService;
        Value value = valueService.getValue();


        Binder<Value> binder = new Binder<>(Value.class);
        Binder<Value> binder1 = new Binder<>(Value.class);

        IntegerField textField = new IntegerField("Counter");

        binder.forField(textField).bind(Value::getValue,
                Value::setValue
        );

        TextArea field = new TextArea("-", "-");


        binder1.forField(field).bind(Value::toString, (e, e1) -> {
                    System.out.println(e.getValue());
                    System.out.println(e1);
                }
        );
        field.addBlurListener(e -> {
            binder1.readBean(value);
        });


        textField.setMin(Integer.MIN_VALUE);
        textField.setMax(Integer.MAX_VALUE);
        textField.setStep(1);
        textField.setStepButtonsVisible(true);
        textField.setValueChangeMode(ValueChangeMode.EAGER);

        Button increaseBtn = new Button("+");
        Button decreaseBtn = new Button("-");


        textField.addValueChangeListener((e) -> {
            try {
                binder.writeBean(value);
                valueService.updateValue(value);
                binder.readBean(value);
                binder1.readBean(value);
            } catch (ValidationException ex) {
                throw new RuntimeException(ex);
            }
        });

        increaseBtn.addThemeVariants(ButtonVariant.MATERIAL_OUTLINED);
        increaseBtn.addClickListener(e -> {
            int current = textField.getValue() == null ? 0 : textField.getValue();
            textField.setValue(current + 1);
        });
        decreaseBtn.addThemeVariants(ButtonVariant.LUMO_ERROR);
        decreaseBtn.addClickListener(e -> {
            int current = textField.getValue() == null ? 0 : textField.getValue();
            textField.setValue(current - 1);
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout(decreaseBtn, increaseBtn);
        add(textField, horizontalLayout);
        add(field);

        setHorizontalComponentAlignment(Alignment.CENTER, textField, horizontalLayout, field);

    }



}
