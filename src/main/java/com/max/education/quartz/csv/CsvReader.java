package com.max.education.quartz.csv;

import com.opencsv.CSVReader;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Reader for CSV file. File contains only holidays for year.
 *
 * @author Max
 */

@Log
@Component
public class CsvReader {
    @Getter
    @Value("@{app.path}")
    private String pathCSV;

    public List<List<String>> readFileAndGetDates() {
        List<List<String>> records = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(pathCSV))) {
            String[] values = null;
            while ((values = reader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            log.info("Problem with parse CSV file in path: " + pathCSV + "Cause: " + e);
        }
        if (records.isEmpty()) {
            throw new RuntimeException("Problem with parse file! Result is empty.");
        }
        return records;
    }
}