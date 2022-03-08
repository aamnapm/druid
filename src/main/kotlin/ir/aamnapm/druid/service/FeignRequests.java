package ir.aamnapm.druid.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "druid", url = "http://server-ip:port/druid/v2/sql")
public interface FeignRequests {

    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = "application/json")
    String data(String req);

}
