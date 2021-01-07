package com.example.application.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;

@Tag("chart-view")
@JsModule("views/chart/chart-view.ts")
public class ChartComponent extends Component {
    private static PropertyDescriptor<String, String>
            JSON_CHART = PropertyDescriptors
            .propertyWithDefault("jsonChart", "");

    public ChartComponent() {
    }

    public ChartComponent(String jsonChart) {
        setJsonChart(jsonChart);
    }


    public String getJsonChart() {
        return get(JSON_CHART);
    }

    public void setJsonChart(String jsonChart) {
        set(JSON_CHART, jsonChart);
    }

}
