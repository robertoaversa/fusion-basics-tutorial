package com.example.application.service;

import be.ceau.chart.LineChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

public class CovidService {
    private final String BASE_URL = "http://localhost:8081/";

    @Autowired
    private RestTemplate restTemplate;

    public LineChart getChart(){
        return null;
    }

    public int[] getDataSet(LocalDate from,LocalDate to){
        //http://localhost:8081/covidData/dati?from=2020-04-20&to=2020-05-20&type=nuovi_positivi&agg=DAY
        URI targetUrl= UriComponentsBuilder.fromUriString(BASE_URL)
                .path("covidData/dati")
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("type","nuovi_positivi")
                .queryParam("agg","DAY")
                .build()
                .encode()
                .toUri();
        ResponseEntity<int[]> response = restTemplate.getForEntity(targetUrl, int[].class);
        return response.getBody();

    }
}
