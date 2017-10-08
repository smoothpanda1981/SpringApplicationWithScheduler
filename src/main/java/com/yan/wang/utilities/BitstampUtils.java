package com.yan.wang.utilities;

import org.apache.log4j.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Stream;

public class BitstampUtils {
    private SecretKeySpec keyspec;
    private Mac mac;
    private String key;
    private String clientid;
    private long nonce;

    private static final Logger logger = Logger.getLogger(BitstampUtils.class);

    public StringBuffer getGetData(String httpLink, String requestName) {
        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(httpLink);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            connection.setRequestMethod("GET");
            //add request header
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + httpLink);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //p//        URL url = new URL("https://www.bitstamp.net/api/v2/ticker/btcusd/");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Content-Type", "application/json");
//        System.out.println(con.getResponseMessage());
//        con.disconnect();rint result
            System.out.println(response.toString());

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }

    public StringBuffer getPostData(String httpLink, String requestName) throws ParseException, IOException {
        Map<String,String> args = new HashMap<String,String>() ;

//		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        //Date d = f.parse(f.format(new Date()));
        //long milliseconds = d.getTime();
        this.nonce = System.currentTimeMillis();

        args.put("nonce", Long.toString(this.nonce)) ;
        args.put("key", this.key) ;

        if (requestName.equals("user_transaction")) {
            args.put("limit", Integer.toString(1000));
        }
        // create url form encoded post data
        String postData = "" ;
        for (Iterator<String> iter = args.keySet().iterator(); iter.hasNext();) {
            String arg = iter.next() ;
            if (postData.length() > 0) postData += "&" ;
            postData += arg + "=" + URLEncoder.encode(args.get(arg)) ;
        }


        //URL url = new URL("https://www.bitstamp.net/api/v2/balance/");
        URL url = new URL(httpLink);
        URLConnection conn = url.openConnection() ;
        conn.setUseCaches(false) ;
        conn.setDoOutput(true) ;

        mac.update(Long.toString(this.nonce).getBytes()) ;
        mac.update(this.clientid.getBytes()) ;
        mac.update(this.key.getBytes()) ;

        postData += "&signature="+String.format("%064x", new BigInteger(1, mac.doFinal())).toUpperCase() ;
        logger.info("postData : " + postData);
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded") ;
        conn.setRequestProperty("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36") ;

        // write post data
        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
        out.write(postData) ;
        out.close() ;

        // read response
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = null;
        StringBuffer response = new StringBuffer() ;
        while ((line = in.readLine()) != null)
            response.append(line) ;
        in.close() ;


        return response;
    }



    public void setAuthKeys(String key,String secret,String clientid) {
        try {
            this.keyspec = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256") ;
            this.mac = Mac.getInstance("HmacSHA256") ;
            this.key = key;
            this.clientid = clientid;
            mac.init(keyspec) ;
            logger.info(mac.getMacLength());
            logger.info(mac.getAlgorithm());
            logger.info(mac.getProvider());
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAuthKeys() {
        List<String> stringList = new ArrayList<>();
        String fileName = "/home/ywang/Data/bitstamp.txt";

        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(s -> putInList(stringList, s));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList;
    }

    private void putInList(List<String> stringList, String s) {
        stringList.add(s);
    }
}
