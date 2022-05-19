package com.qualityunit.service.impl;

import com.qualityunit.model.TimeLine;
import com.qualityunit.service.LineValidatorService;
import com.qualityunit.service.TimeLineService;
import java.util.ArrayList;
import java.util.List;

public class TimeLineServiceImpl implements TimeLineService {
    private final LineValidatorService lineValidatorService;
    private final List<TimeLine> timeLinesList = new ArrayList<>();

    public TimeLineServiceImpl(LineValidatorService lineValidatorService) {
        this.lineValidatorService = lineValidatorService;
    }

    @Override
    public List<TimeLine> processTimeLines(List<String> timeLinesString) {
        for (String timeLineString : timeLinesString) {
            TimeLine timeLine = new TimeLine();
            String[] splitTimeLine = timeLineString.split(" ");

            int requiredNumberOfQueryParameters = 6;
            if (splitTimeLine.length != requiredNumberOfQueryParameters) {
                throw new IllegalArgumentException("Invalid request!");
            } else {

                int indexOfServiceNumber = 1;
                String serviceNumber = splitTimeLine[indexOfServiceNumber];
                lineValidatorService.validateAndSaveServiceNumber(timeLine, serviceNumber);

                int indexOfQuestionType = 2;
                String questionType = splitTimeLine[indexOfQuestionType];
                lineValidatorService.validateAndSaveQuestionType(timeLine, questionType);

                int indexOfResponseType = 3;
                String responseType = splitTimeLine[indexOfResponseType];
                lineValidatorService.validateAndSaveResponseType(timeLine, responseType);

                int indexOfDate = 4;
                String date = splitTimeLine[indexOfDate];
                lineValidatorService.validateAndSaveDate(timeLine, date);

                int indexOfTime = 5;
                String time = splitTimeLine[indexOfTime];
                lineValidatorService.validateAndSaveTime(timeLine, time);

                timeLinesList.add(timeLine);
            }
        }
        return timeLinesList;
    }
}
