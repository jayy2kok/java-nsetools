package com.jayraj.nsetools.javansetools.configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jayraj.nsetools.javansetools.models.Stock;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ProcessingUtils {
    // private static DateTimeFormatter formatter =
    // DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static Flux<Stock> CsvToStocks(Flux<String> csv) {
        return csv.skip(1).map(line -> {
            log.info("line:{}", line);
            String[] fields = line.split(",");
            return new Stock().setSymbol(fields[0]).setName(fields[1]).setType(fields[2]).setListingDate(fields[3])
                    .setPaidUpValue(Integer.valueOf(fields[4])).setMarketLot(Integer.valueOf(fields[5]))
                    .setIsin(fields[6]).setFaceValue(Integer.valueOf(fields[7]));
        });
    }
}
