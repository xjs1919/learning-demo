package com.github.xjs.dao;

import com.github.xjs.entity.h2.ReportType;
import com.github.xjs.repository.h2.ReportTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ReportTypeDao {
    @Autowired
    private ReportTypeRepository reportTypeRepository;
    @Cacheable(value = "report-type", key = "'report-type-all'",
            unless = "#result == null || #result.size() <= 0")
    public Map<Long, ReportType> getAll(){
        log.info("------------getAll----------------");
        return reportTypeRepository.findAll()
                .stream()
                .collect(Collectors.toMap(ReportType::getId, Function.identity()));
    }
}
