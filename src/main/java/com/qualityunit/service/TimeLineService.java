package com.qualityunit.service;

import com.qualityunit.model.TimeLine;
import java.util.List;

public interface TimeLineService {
    List<TimeLine> processTimeLines(List<String> timeLinesString);
}
