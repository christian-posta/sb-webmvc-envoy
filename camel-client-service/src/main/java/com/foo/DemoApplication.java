package com.foo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);

//        String url = "http://www.my-test.com/api/bookings/1";
        String url = "http://ipaddress/";

        for (int i = 0; i < 10; i++ ) {

            System.out.println("first using Apache...");
            HttpClient httpClient = HttpClients.createSystem();
            HttpGet get = new HttpGet(url);
            BasicHttpContext context = new BasicHttpContext();
            HttpResponse httpResponse = httpClient.execute(get, context);
            String rc = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(rc);

            System.out.println("now using Spring...");
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            System.out.println(response);

            System.out.println("last using OkHttp.");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response resp = client.newCall(request).execute();
            System.out.println(resp.body().string());
        }

    }
}
