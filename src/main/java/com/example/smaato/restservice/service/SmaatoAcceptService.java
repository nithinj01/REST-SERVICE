package com.example.smaato.restservice.service;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.io.File;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.PrintWriter;
import java.util.*;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.List;
@Service
public class SmaatoAcceptService {
    private  Long totalresp;
    private final RestTemplate restTemplate = new RestTemplate();
    Set<Integer> threadSafeSet = ConcurrentHashMap.newKeySet();
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    public void writeresplog(Integer id,String endpoint)
            throws IOException {
        try{
            threadSafeSet.add(id);
            totalresp= Long.valueOf(threadSafeSet.size());
            if (endpoint != null&&!endpoint.isEmpty()) {
                System.out.println("Testing 2 - Send Http POST request");
                sendPost(endpoint);

            }else {
                writeResponse();
            }
        }
        catch(Exception e){
            System.out.println("Error!");
        }
        finally {
            httpClient.close();
        }
    }
    private void sendGet(String url) throws Exception {

        HttpGet request = new HttpGet(url+"?id="+totalresp);

        // add request headers
        request.addHeader("custom-key", "smaato");
        request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }

    }
    private void sendPost(String url) throws Exception {

        HttpPost post = new HttpPost(url);
        JSONObject json = new JSONObject();
        json.put("id", "123");
        json.put("content","Smaato post response");
        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("id", "123"));
        urlParameters.add(new BasicNameValuePair("content", "Smaato post response"));

        StringEntity stringEntity = new StringEntity(json.toString());
        post.addHeader("content-type", "application/json");
        post.setEntity(stringEntity);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        }

    }

    public void writeResponse() {
        try {
            HttpStatus response = HttpStatus.OK;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            File log = new File("smaatologresp.txt");
            if (log.exists() == false) {
                System.out.println("We have to make a new log file.");
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append("Time: " + dtf.format(now) + " Total response: " + totalresp + " Response " + response + "\n");
            out.close();
        }catch(IOException e){
            System.out.println("COULD NOT LOG!");
        }
    }
    public void writePostResponse() {
        try {
            HttpStatus response = HttpStatus.CREATED;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            File log = new File("smaatologresp.txt");
            if (log.exists() == false) {
                System.out.println("We have to make a new log file.");
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append("Time: " + dtf.format(now) + " Response " + response + "\n");
            out.close();
        }catch(IOException e){
            System.out.println("COULD NOT LOG!");
        }
    }
    @Scheduled(cron = "0 * * * * *")
    private void logRespCount(){
        try {
            File log = new File("smaatologrespcount.txt");
            if (log.exists() == false) {
                System.out.println("We have to make a new log file.");
                log.createNewFile();
            } else {
                HttpStatus response = HttpStatus.OK;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                PrintWriter out = new PrintWriter(new FileWriter(log, true));
                out.append("Time: "+dtf.format(now)+" Total response: " + totalresp + "  " + "Response: " + response + "\n");
                out.close();
            }
        }catch(IOException e){
            System.out.println("COULD NOT LOG!");
        }
    }
}