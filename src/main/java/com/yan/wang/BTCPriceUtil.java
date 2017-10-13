package com.yan.wang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yan.wang.dao.Ticker;
import com.yan.wang.utilities.BitstampUtils;
import com.yan.wang.utilities.ZohoMail;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

/**
 * Created by ywang on 15.08.17.
 */
@Service
public class BTCPriceUtil {
    @Scheduled(fixedRate=3600000, initialDelay = 10000)
    public void countStudent() throws Exception {
        //System.out.println("Current price of BTC : ");
        BitstampUtils bitstampUtils = new BitstampUtils();
			/*
				get current price
		 	*/
        ObjectMapper mapper = new ObjectMapper();

        //JSON from URL to Object
        Ticker ticker = mapper.readValue(new URL("https://www.bitstamp.net/api/v2/ticker/btcusd"), Ticker.class);

        //System.out.println("last " + ticker.getLast());

        ZohoMail zohoMail = new ZohoMail();
        zohoMail.sendMail(String.valueOf(ticker.getLast()));
    }
}
