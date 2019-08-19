package com.max.education.quartz.ustils;

import com.max.education.quartz.csv.CsvReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class helps to create Holiday Calendar.
 *
 * @author Max
 */

@Slf4j
@Component
public class MyHolidayCreator {
    /**
     * '21' - line 2019 year
     */
    private int numberOfLineInFile = 21;

    private final CsvReader reader;

    public MyHolidayCreator(CsvReader reader) {
        this.reader = reader;
    }

    public List<Date> getAllDates() {
        List<Date> dates = new ArrayList<>();
        LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        IntStream.range(1, 13).forEachOrdered(i -> {
            List<String> list = createList(findWeekendInNeededMonth(reader.readFileAndGetDates(), i));
            list.stream().map(date -> new Date(currentDate.getYear(), i - 1, getTrueNumber(date))).forEach(dates::add);
        });
        log.info("Calendar was create!");
        return dates;
    }

    private int getTrueNumber(String date) {
        return date.contains("*") ? Integer.parseInt(date.replace('*', ' ').trim()) : Integer.parseInt(date);
    }

    private String findWeekendInNeededMonth(List<List<String>> records, int neededMonth) {
        List<String> needDates = records.get(numberOfLineInFile);
        return needDates.get(neededMonth);
    }

    private List<String> createList(String readFileAndGetString) {
        String[] result = readFileAndGetString.split(",");
        return Arrays.asList(result);
    }
}