package com.example.vaadindemo;

import com.example.vaadindemo.service.ValueService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
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
    private NativeLabel resLabel;

    @Autowired
    public CounterView(ValueService valueService) {
        this.valueService = valueService;

        IntegerField textField = new IntegerField("Counter");
        textField.setValue(0);
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

        increaseBtn.addClickListener(e -> {
            int current = textField.getValue() == null ? 0 : textField.getValue();
            textField.setValue(current + 1);
            int current2 = textField.getValue() == null ? 0 : textField.getValue();
            updateValue(current2);
        });

        decreaseBtn.addClickListener(e -> {
            int current = textField.getValue() == null ? 0 : textField.getValue();
            textField.setValue(current - 1);
            int current2 = textField.getValue() == null ? 0 : textField.getValue();
            updateValue(current2);

        });

        add(textField, new HorizontalLayout(decreaseBtn, increaseBtn));
        add(resLabel);

    }

    private void updateValue(Integer integer) {
        valueService.updateValue(integer);
        updateValueOnView();
    }

    private void updateValueOnView() {
        resLabel.setText(String.valueOf(valueService.getValue()));
    }


}
