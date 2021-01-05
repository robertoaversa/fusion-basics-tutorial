package com.example.application.views.chart;

import be.ceau.chart.LineChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.LineData;
import be.ceau.chart.dataset.LineDataset;
import be.ceau.chart.enums.BorderCapStyle;
import be.ceau.chart.enums.BorderJoinStyle;
import be.ceau.chart.options.elements.Fill;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Endpoint
@AnonymousAllowed
public class ChartEndpoint {
    final String BASE_URL = "http://localhost:8081/";

    @Autowired
    public RestTemplate restTemplate;


    public ChartJson getChart(){
        //http://localhost:8081/covidData/dati?from=2020-04-20&to=2020-05-20&type=nuovi_positivi&agg=DAY
        URI targetUrl= UriComponentsBuilder.fromUriString(BASE_URL)
                .path("covidData/dati")
                .queryParam("from", LocalDate.now().minusMonths(3))
                .queryParam("to", LocalDate.now())
                .queryParam("type","nuovi_positivi")
                .queryParam("agg","DAY")
                .build()
                .encode()
                .toUri();
        ResponseEntity<int[]> response = restTemplate.getForEntity(targetUrl, int[].class);
        int[] dataSet = response.getBody();
        List<String> label = new ArrayList<>();
        LocalDate from = LocalDate.now().minusMonths(3);
        while (from.isBefore(LocalDate.now())){
            label.add(from.toString());
            from = from.plusDays(1);
        }
        LineData lineData = createLineData(label, dataSet);
        LineChart lineChart = new LineChart();
        lineChart.setData(lineData);
            return new ChartJson(lineChart.toJson());
    }

    private LineData createLineData(List<String> labels,int... dataset) {
        return new LineData()
                .addDataset(createLineDataset(dataset))
                .addLabels(labels.toArray(String[]::new));

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
