package com.qualityunit.service.impl;

import com.qualityunit.exception.DataProcessingException;
import com.qualityunit.model.Query;
import com.qualityunit.model.RequestLine;
import com.qualityunit.model.TimeLine;
import com.qualityunit.service.LineValidatorService;
import com.qualityunit.util.DataValidator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LineValidatorServiceImpl implements LineValidatorService {
    @Override
    public void validateAndSaveServiceNumber(RequestLine requestLine, String serviceNumber) {
        if (!serviceNumber.contains(".") && DataValidator.isNumeric(serviceNumber)) {
            int intServiceNumber = Integer.parseInt(serviceNumber);
            if (intServiceNumber <= 10 && intServiceNumber >= 1) {
                requestLine.setServiceNumber(intServiceNumber);
            } else {
                throw new DataProcessingException(
                        "Service Number must be in the range 1-10! Not " + intServiceNumber + "!");
            }
        } else if (serviceNumber.contains(".") && DataValidator.isNumeric(serviceNumber)) {
            String[] splitServiceNumber = serviceNumber.split("\\.");
            if (splitServiceNumber.length == 2) {

                int intServiceNumber = Integer.parseInt(splitServiceNumber[0]);
                int intServiceVariation = Integer.parseInt(splitServiceNumber[1]);
                if (intServiceNumber <= 10 && intServiceNumber >= 1 && intServiceVariation <= 3
                        && intServiceVariation >= 1) {
                    requestLine.setServiceNumber(Integer.parseInt(splitServiceNumber[0]));
                    requestLine.setServiceVariation(Integer.parseInt(splitServiceNumber[1]));
                } else {
                    throw new DataProcessingException(
                            "Service Number must be in the range 1-10! Service Variation must be in the range 1-3!");
                }

            } else {
                throw new DataProcessingException(
                        "Service Number (" + serviceNumber + ") is not valid!");
            }
        } else if (serviceNumber.contains("*") && serviceNumber.length() == 1) {
            requestLine.setServiceNumber(0); //0 means query match all services/question types
        } else {
            throw new DataProcessingException(
                    "Service Number (" + serviceNumber + ") is not valid!");
        }
    }

    @Override
    public void validateAndSaveQuestionType(RequestLine requestLine, String questionType) {
        if (!questionType.contains(".") && DataValidator.isNumeric(questionType)) {

            int intQuestionType = Integer.parseInt(questionType);
            if (intQuestionType >= 1 && intQuestionType <= 10) {
                requestLine.setQuestionType(intQuestionType);
            } else {
                throw new DataProcessingException(
                        "Question Type must be in the range 1-10! Not " + questionType + "!");
            }
        } else if (questionType.contains(".") && DataValidator.isNumeric(questionType)) {
            String[] splitQuestionType = questionType.split("\\.");

            if (splitQuestionType.length == 2) {

                int intQuestionType = Integer.parseInt(splitQuestionType[0]);
                int intQuestionCategory = Integer.parseInt(splitQuestionType[1]);
                if (intQuestionType >= 1 && intQuestionType <= 10 && intQuestionCategory >= 1
                        && intQuestionCategory <= 20) {
                    requestLine.setQuestionType(intQuestionType);
                    requestLine.setQuestionCategory(intQuestionCategory);
                } else {
                    throw new DataProcessingException(
                            "Question Type must be in the range 1-10! Question Category must be in the range 1-20!");
                }
            } else if (splitQuestionType.length == 3) {

                int intQuestionType = Integer.parseInt(splitQuestionType[0]);
                int intQuestionCategory = Integer.parseInt(splitQuestionType[1]);
                int intQuestionSubcategory = Integer.parseInt(splitQuestionType[2]);
                if (intQuestionType >= 1 && intQuestionType <= 10 && intQuestionCategory >= 1
                        && intQuestionCategory <= 20 && intQuestionSubcategory >= 1
                        && intQuestionSubcategory <= 5) {
                    requestLine.setQuestionType(intQuestionType);
                    requestLine.setQuestionCategory(intQuestionCategory);
                    requestLine.setQuestionSubcategory(intQuestionSubcategory);
                } else {
                    throw new DataProcessingException(
                            "Question Type must be in the range 1-10! "
                                    + "Question Category must be in the range 1-20! "
                                    + "Question Subcategory must be in the range 1-5!");
                }
            } else {
                throw new DataProcessingException(
                        "Question Type (" + questionType + ") is not valid!");
            }
        } else if (questionType.contains("*") && questionType.length() == 1) {
            requestLine.setQuestionType(0); //0 means query match all services/question types
        } else {
            throw new DataProcessingException(
                    "Question Type (" + questionType + ") is not valid!");
        }
    }

    @Override
    public void validateAndSaveResponseType(RequestLine requestLine, String responseType) {
        if (responseType.contains("P") && responseType.length() == 1
                || responseType.contains("N") && responseType.length() == 1) {
            requestLine.setResponseType(responseType);
        } else {
            throw new DataProcessingException(
                    "Response Type (" + responseType + ") is not valid!");
        }
    }

    @Override
    public void validateAndSaveDate(TimeLine timeLine, String date) {
        if (DataValidator.isDate(date)) {
            timeLine.setDate(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } else {
            throw new DataProcessingException("Date (" + date + ") is not valid!");
        }
    }

    @Override
    public void validateAndSaveTime(TimeLine timeLine, String time) {
        if (DataValidator.isNumeric(time)) {
            int number = Integer.parseInt(time);
            if (number >= 0) {
                timeLine.setTime(number);
            } else {
                throw new DataProcessingException("Time (" + time + ") is not valid!");
            }
        } else {
            throw new DataProcessingException("Time (" + time + ") is not valid!");
        }
    }

    @Override
    public void validateAndSaveDateFromAndDateTo(Query query, String dateFromAndDateTo) {
        if (!dateFromAndDateTo.contains("-") && DataValidator.isDate(dateFromAndDateTo)) {
            query.setDateFrom(
                    LocalDate.parse(dateFromAndDateTo, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } else if (dateFromAndDateTo.contains("-")) {
            String[] splitDateFromAndDateTo = dateFromAndDateTo.split("-");
            if (splitDateFromAndDateTo.length == 2) {
                String dateFrom = splitDateFromAndDateTo[0];
                String dateTo = splitDateFromAndDateTo[1];
                if (DataValidator.isDate(dateFrom) && DataValidator.isDate(dateTo)) {
                    query.setDateFrom(
                            LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("d.MM.yyyy")));
                    query.setDateTo(
                            LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("d.MM.yyyy")));
                } else {
                    throw new DataProcessingException(
                            "DateFrom (" + dateFrom + ") or DateTo (" + dateTo + ") is not valid!");
                }
            }
        }
    }
}
