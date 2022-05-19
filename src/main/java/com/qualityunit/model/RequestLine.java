package com.qualityunit.model;

public abstract class RequestLine {
    private int serviceNumber;
    private int serviceVariation;
    private int questionType;
    private int questionCategory;
    private int questionSubcategory;
    private String responseType;

    public int getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(int serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public int getServiceVariation() {
        return serviceVariation;
    }

    public void setServiceVariation(int serviceVariation) {
        this.serviceVariation = serviceVariation;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(int questionCategory) {
        this.questionCategory = questionCategory;
    }

    public int getQuestionSubcategory() {
        return questionSubcategory;
    }

    public void setQuestionSubcategory(int questionSubcategory) {
        this.questionSubcategory = questionSubcategory;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
