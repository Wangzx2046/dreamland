package com.example.dsdemo.controller.zkb;


import com.example.dsdemo.biz.zkb.entity.ZkbXgyz;
import com.example.dsdemo.biz.zkb.service.IZkbXgyzService;
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
 * @Description ：赚客吧-小鸽驿站
 * @since : 2021-01-21
 */
@Slf4j
@RestController
@RequestMapping("/system/zkb-xgyz")
public class ZkbXgyzController  {

    @Resource
    private IZkbXgyzService zkbXgyzService;


    @GetMapping("/1")
    public ResponseEntity<Object> all(ZkbXgyz zkbXgyz) {

        List<ZkbXgyz> list = zkbXgyzService.list(zkbXgyz);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/2")
    public ResponseEntity<Object> all2(ZkbXgyz zkbXgyz) {

        List<ZkbXgyz> list = zkbXgyzService.list2(zkbXgyz);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/3")
    public ResponseEntity<Object> all3(ZkbXgyz zkbXgyz) {

        List<ZkbXgyz> list = zkbXgyzService.list3(zkbXgyz,"gen_gen1");

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/4")
    public ResponseEntity<Object> all4(ZkbXgyz zkbXgyz) {

        List<ZkbXgyz> list = zkbXgyzService.list3(zkbXgyz,"def_dreamland");

        return new ResponseEntity<>(list, HttpStatus.OK);
    }





}
