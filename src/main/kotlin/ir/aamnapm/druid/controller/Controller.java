package ir.aamnapm.druid.controller;

import ir.aamnapm.druid.model.DruidData;
import ir.aamnapm.druid.service.DruidFeignService;
import ir.aamnapm.druid.service.DruidHttpService;
import ir.aamnapm.druid.service.DruidJDBCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    DruidJDBCService service;

    @Autowired
    DruidHttpService httpService;

    @Autowired
    DruidFeignService feignService;

    @GetMapping(value = "/data")
    public ResponseEntity<List<DruidData>> get() {
        return new ResponseEntity<>(service.start(), HttpStatus.OK);
    }

    @GetMapping(value = "/http")
    public ResponseEntity<CharSequence> getFromHttp() {
        return new ResponseEntity<>(httpService.getData(), HttpStatus.OK);
    }

    @GetMapping(value = "/feign")
    public ResponseEntity<List<Map<String, String>>> getFromFeign() {
        return new ResponseEntity<>(feignService.getData(), HttpStatus.OK);
    }
}
