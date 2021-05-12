package com.zero.dreamland.influxdb.controller;


import com.influxdb.query.FluxTable;
import com.zero.dreamland.common.annotation.AnonymousAccess;
import com.zero.dreamland.influxdb.biz.TimeSeriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : Wang.zx
 * @Description ：influxDB test
 * @since : 2021-01-21
 */
@Slf4j
@RestController
@RequestMapping("/influx/test")
public class InfuluxDbController  {

    @Resource
    private TimeSeriesRepository timeSeriesRepository;


    @AnonymousAccess
    @GetMapping
    public ResponseEntity<Object> all() {

        List<FluxTable> list= timeSeriesRepository.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @AnonymousAccess
    @GetMapping("/1")
    public ResponseEntity<Object> test1() {

        List<FluxTable> list= timeSeriesRepository.findAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }




}
