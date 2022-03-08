package ir.aamnapm.druid.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.aamnapm.druid.model.DruidRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class DruidFeignService {

    @Autowired
    private FeignRequests feignRequests;

    public List<Map<String, String>> getData() {

        DruidRequest request = createRequest();
        String requestStr = convertToStringEntity(request);

        String data = feignRequests.data(requestStr);
        return convertToList(data);
    }

    private DruidRequest createRequest() {
        DruidRequest request = new DruidRequest();
        request.setQuery("SELECT * from your_table");
        request.setResultFormat("object");
        return request;
    }

    private String convertToStringEntity(DruidRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        try {
            return mapper.writeValueAsString(request);
        } catch (IOException e) {
            e.printStackTrace();
            //throws exception
            return null;
        }
    }

    private List<Map<String, String>> convertToList(String data) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        try {
            return mapper.readValue(data, new TypeReference<List<Map<String, String>>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            //throws exception
            return Collections.emptyList();
        }
    }

}
