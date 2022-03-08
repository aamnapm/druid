package ir.aamnapm.druid.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.aamnapm.druid.model.DruidRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class DruidHttpService {


    public CharSequence getData() {
        try {
            return main();
        } catch (Exception e) {
            e.printStackTrace();
            //throw exception
            return null;
        }
    }

    private CharSequence main() {
        String url = "http://server-ip:port/druid/v2/sql";

        DruidRequest request = createRequest("SELECT * from your_table", "object");

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/json");
        post.setEntity(convertToStringEntity(request));

        HttpResponse response;
        try {
            response = client.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
                //throw exception
            }
            System.out.println("buffer " + result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private DruidRequest createRequest(String query, String resultFormat) {
        DruidRequest request = new DruidRequest();
        request.setQuery(query);
        request.setResultFormat(resultFormat);
        return request;
    }

    private StringEntity convertToStringEntity(DruidRequest request) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        try {
            String requestStr = mapper.writeValueAsString(request);

            return new StringEntity(requestStr);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
