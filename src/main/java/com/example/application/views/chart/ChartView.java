package com.example.application.views.chart;

import be.ceau.chart.LineChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.LineData;
import be.ceau.chart.dataset.LineDataset;
import be.ceau.chart.enums.BorderCapStyle;
import be.ceau.chart.enums.BorderJoinStyle;
import be.ceau.chart.options.LineOptions;
import be.ceau.chart.options.elements.Fill;
import com.example.application.component.ChartComponent;
import com.example.application.service.CovidService;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Route("chart-view")
public class ChartView extends VerticalLayout {

    private final CovidService covidService;
    private ChartComponent chart;


    private LocalDate fromLocalDate = LocalDate.now().minusMonths(5);
    private LocalDate toLocalDate = LocalDate.now().minusDays(6);

    public ChartView(@Autowired CovidService covidService){
        this.covidService = covidService;
        DatePicker fromDatePicker = new DatePicker();
        fromDatePicker.setPlaceholder("From");
        fromDatePicker.addValueChangeListener((date)->{
            fromLocalDate = date.getValue();
            updateChart();
        });
        DatePicker toDatePicker = new DatePicker();
        toDatePicker.setPlaceholder("To");
        toDatePicker.addValueChangeListener((date)->{
            toLocalDate = date.getValue();
            updateChart();
        });
        HorizontalLayout header = new HorizontalLayout(fromDatePicker,toDatePicker);
        chart = new ChartComponent(buildLineChart().toJson());
        addAndExpand(header,chart);
        this.setAlignItems(Alignment.START);
    }


    private void updateChart(){
        chart.setJsonChart(buildLineChart().toJson());
    }

    private LineChart buildLineChart(){
        List<String> labels = new ArrayList<>();
        LocalDate localFromDateTmp = this.fromLocalDate;
        while (localFromDateTmp.isBefore(toLocalDate)){
            labels.add(localFromDateTmp.toString());
            localFromDateTmp = localFromDateTmp.plusDays(1);
        }
        LineData lineData = new LineData()
                .addDataset(createLineDataset(getDataSet()))
                .addLabels(labels.toArray(String[]::new));
        LineChart lineChart = new LineChart();
        lineChart.setData(lineData);
        LineOptions options = new LineOptions();
        options.setResponsive(true);
        lineChart.setOptions(options);
        return lineChart;
    }

    private int[] getDataSet(){
        return covidService.getDataSet(fromLocalDate,toLocalDate);
    }

    private LineDataset createLineDataset(int... dataset) {
        return new LineDataset()
                .setLabel("My First dataset")
                .setFill(new Fill(true))
                .setLineTension(0.1f)
                .setBackgroundColor(new Color(75, 192, 192, 0.4))
                .setBorderColor(new Color(75,192,192,1))
                .setBorderCapStyle(BorderCapStyle.BUTT)
                .setBorderDashOffset(0.0f)
                .setBorderJoinStyle(BorderJoinStyle.MITER)
                .addPointBorderColor(new Color(75, 192, 192, 1))
                .addPointBackgroundColor(new Color(255, 255, 255, 1))
                .addPointBorderWidth(1)
                .addPointHoverRadius(5)
                .addPointHoverBackgroundColor(new Color(75,192,192,1))
                .addPointHoverBorderColor(new Color(220,220,220,1))
                .addPointHoverBorderWidth(2)
                .addPointRadius(1)
                .addPointHitRadius(10)
                .setSpanGaps(false)
                .setData(dataset);
    }
  
}
