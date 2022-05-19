package com.qualityunit;

import com.qualityunit.service.AnalyticalService;
import com.qualityunit.service.LineValidatorService;
import com.qualityunit.service.QueryService;
import com.qualityunit.service.TimeLineService;
import com.qualityunit.service.impl.AnalyticalServiceImpl;
import com.qualityunit.service.impl.LineValidatorServiceImpl;
import com.qualityunit.service.impl.QueryServiceImpl;
import com.qualityunit.service.impl.TimeLineServiceImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnalyticalApp {
    private static final LineValidatorService lineValidatorService = new LineValidatorServiceImpl();
    private static final QueryService queryService = new QueryServiceImpl(lineValidatorService);
    private static final TimeLineService timeLineService =
            new TimeLineServiceImpl(lineValidatorService);
    private static final AnalyticalService analyticalService = new AnalyticalServiceImpl(
            queryService, timeLineService);

    private static final List<String> queries = new ArrayList<>();
    private static final List<String> timeLines = new ArrayList<>();
    private static final File dataToAnalyze = new File("src/main/resources/DataToAnalyze.txt");

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(dataToAnalyze);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("The specified path to the file could not be found!", e);
        }
        int numberOfLines = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfLines; i++) {
            String line = scanner.nextLine();
            if (line.startsWith("D")) {
                queries.add(line);
            } else if (line.startsWith("C")) {
                timeLines.add(line);
            } else {
                throw new RuntimeException("The line must start with C or D symbol!");
            }
        }
        scanner.close();
        String[] analysis = analyticalService.analyze(timeLines, queries);
        System.out.println(Arrays.toString(analysis));
    }
}
