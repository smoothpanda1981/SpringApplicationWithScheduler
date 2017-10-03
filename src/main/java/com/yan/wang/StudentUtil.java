package com.yan.wang;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by ywang on 15.08.17.
 */
@Service
public class StudentUtil {
    @Scheduled(fixedRate=2000)
    public void countStudent() throws Exception {
        System.out.println("Count Student...");
//        URL url = new URL("https://www.bitstamp.net/api/v2/ticker/btcusd/");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Content-Type", "application/json");
//        System.out.println(con.getResponseMessage());
//        con.disconnect();
    }
}
