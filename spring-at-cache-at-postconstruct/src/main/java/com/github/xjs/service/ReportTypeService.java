package com.github.xjs.service;

import com.github.xjs.dao.ReportTypeDao;
import com.github.xjs.entity.h2.ReportType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReportTypeService {

    @Autowired
    private ReportTypeDao reportTypeDao;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            // load cache data
            reportTypeDao.getAll();
        }
    }

//    @PostConstruct
//    public void postConstruct(){
//        // load cache data
//        reportTypeDao.getAll();
//    }

    public ReportType getById(Long id){
        Map<Long, ReportType> all = reportTypeDao.getAll();
        return all.get(id);
    }
}
