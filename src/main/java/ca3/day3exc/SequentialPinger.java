/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca3.day3exc;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felesiah
 */
public class SequentialPinger {
   public static void main(String args[]) throws Exception {
  Callable <String> c1 = () ->{
        String[] hostList = { "http://crunchify.com", "http://yahoo.com",
                "http://www.ebay.com", "http://google.com",
                "http://www.example.co", "https://paypal.com",
                "http://bing.com/", "http://techcrunch.com/",
                "http://mashable.com/", "http://thenextweb.com/",
                "http://wordpress.com/", "http://cphbusiness.dk/",
                "http://example.com/", "http://sjsu.edu/",
                "http://ebay.co.uk/", "http://google.co.uk/",
                "http://www.wikipedia.org/",
                "http://dr.dk","http://pol.dk","https://www.google.dk",
                "http://phoronix.com" ,"http://www.webupd8.org/",
                "https://studypoint-plaul.rhcloud.com/", "http://stackoverflow.com",
                "http://docs.oracle.com","https://fronter.com",
                "http://imgur.com/", "http://www.imagemagick.org"
                };

           for (int i = 0; i < hostList.length; i++) { 
            String url = hostList[i];
            String status = getStatus(url);
              try {
                  status = getStatus(url);
               
              } catch (IOException ex) {
                  Logger.getLogger(SequentialPinger.class.getName()).log(Level.SEVERE, null, ex);
              }
              return url + "\t\tStatus:" + status;
          }
          return null;
        };
       ExecutorService ex = Executors.newCachedThreadPool();
       Future<String> f1 = ex.submit(c1);
       System.out.println(f1.get());
   }
   
   
 
    public static String getStatus(String url) throws IOException {
 
        String result = "Error";
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
 
            int code = connection.getResponseCode();
            if (code == 200) 
                result = "Green";
            if(code==301)
                result="Redirect";
        } catch (IOException e) {
            result = "->Red<-";
        }
        return result;
    }  
}
