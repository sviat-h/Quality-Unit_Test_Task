package com.qualityunit.service;

import java.util.List;

public interface AnalyticalService {
    String[] analyze(List<String> timeLines, List<String> queries);
}
