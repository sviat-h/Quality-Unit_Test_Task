package com.qualityunit.service;

import com.qualityunit.model.Query;
import java.util.List;

public interface QueryService {
    List<Query> processQueries(List<String> queries);
}
