package co.edu.escuelaing.arem.herokuapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SpringBootApplication
public class Main {
     public static final String LINK_API_GATEWAY_DEPLOY = "https://nebqnnjewe.execute-api.us-west-2.amazonaws.com/prod/bill?id=";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @RequestMapping(value="/bill")
    public ResponseEntity<?> getJsonBill(@RequestParam("id") int id) throws MalformedURLException{
        
        URL link = new URL(LINK_API_GATEWAY_DEPLOY + id);
            String ans = "";
            try (BufferedReader reader
                    = new BufferedReader(new InputStreamReader(link.openStream()))) {
                String inputLine = null;
                while ((inputLine = reader.readLine()) != null) {
                    ans += inputLine;
                }
            } catch (IOException x) {
                System.err.println(x);
            }
        
        return new ResponseEntity<>(ans,HttpStatus.OK);
    }

  

}