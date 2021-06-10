package com.mano.practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckUrlStatus {
	private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT = "URL is Incorrect!";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url){
        String retString="";
        try {
            URL urlObjectUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObjectUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int resposeCode = conn.getResponseCode()/100;
            if(resposeCode==2 || resposeCode==3 ){
                retString = SITE_IS_UP;
            }
            else{
                retString = SITE_IS_DOWN;   
            }
        } catch (MalformedURLException e) {
            retString = INCORRECT;
        } catch (IOException e) {
            retString = SITE_IS_DOWN;
        }
        return retString;
    }
}
