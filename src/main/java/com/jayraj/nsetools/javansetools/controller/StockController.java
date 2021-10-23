/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jayraj.nsetools.javansetools.controller;

import java.time.Duration;

import com.jayraj.nsetools.javansetools.configuration.ProcessingUtils;
import com.jayraj.nsetools.javansetools.models.Stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 */
@RestController
public class StockController {
    @Autowired
    @Qualifier("nseArchiveClient")
    private WebClient nseArchiveClient;

    @Autowired
    @Qualifier("nseAPIClient")
    private WebClient nseAPIClient;

    @GetMapping(value = "/stocks", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Stock> getAllStocks() {
        return ProcessingUtils.CsvToStocks(nseArchiveClient.get().retrieve().bodyToFlux(String.class));
    }

    @GetMapping(value = "/stocks/{symbol}")
    public Mono<Object> getStockDetails(@PathVariable String symbol) {
        return nseAPIClient.get()
                .uri(uriBuilder -> uriBuilder.path("/quote-equity").queryParam("symbol", symbol).build()).retrieve()
                .bodyToMono(Object.class).log();
    }

}
