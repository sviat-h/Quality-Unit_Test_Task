package com.qualityunit.service;

import com.qualityunit.model.Query;
import com.qualityunit.model.RequestLine;
import com.qualityunit.model.TimeLine;

public interface LineValidatorService {
    void validateAndSaveServiceNumber(RequestLine requestLine, String serviceNumber);

    void validateAndSaveQuestionType(RequestLine requestLine, String questionType);

    void validateAndSaveResponseType(RequestLine requestLine, String responseType);

    void validateAndSaveDate(TimeLine timeLine, String date);

    void validateAndSaveTime(TimeLine timeLine, String time);

    void validateAndSaveDateFromAndDateTo(Query query, String dateFromAndDateTo);
}
