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
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SpringBootApplication
public class Main {

    public static final String LINK_API_GATEWAY_DEPLOY = "https://qo6ki1xwbc.execute-api.us-west-2.amazonaws.com/prod/bill?";

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @RequestMapping(value = "/bill", method=GET)
    public ResponseEntity<?> getJsonBill(
            @RequestParam("id") int id,
            @RequestParam("date") String date,
            @RequestParam("nameEmployee") String nameEmployee,
            @RequestParam("idEmployee") long idEmployee,
            @RequestParam("companysNit") long companysNit,
            @RequestParam("companysPhone") long companysPhone,
            @RequestParam("consumerName") String consumerName,
            @RequestParam("consumerId") long consumerId,
            @RequestParam("consumerPhone") long consumerPhone,
            @RequestParam("consumerEmail") String consumerEmail,
            @RequestParam("purchasePrice") float purchasePrice,
            @RequestParam("ivaPercentage") int ivaPercentage,
            @RequestParam("ivaPrice") float ivaPrice,
            @RequestParam("total") float total) throws MalformedURLException {
        
        String dateAWS = date.replace("/", "%2f").replace(" ", "%20").replace(":", "%3a");

        String link = "id=" + id
                + "&date=" + dateAWS
                + "&nameEmployee=" + nameEmployee
                + "&idEmployee=" + idEmployee
                + "&companysNit=" + companysNit
                + "&companysPhone=" + companysPhone
                + "&consumerName=" + consumerName
                + "&consumerId=" + consumerId
                + "&consumerPhone=" + consumerPhone
                + "&consumerEmail=" + consumerEmail
                + "&purchasePrice=" + purchasePrice
                + "&ivaPercentage=" + ivaPercentage
                + "&ivaPrice=" + ivaPrice
                + "&total=" + total;
        URL urlLink = new URL(LINK_API_GATEWAY_DEPLOY + link.replace("/", "%2f").replace(" ", "%20").replace(":", "%3a"));
        String ans = "";
        try (BufferedReader reader
                = new BufferedReader(new InputStreamReader(urlLink.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                ans += inputLine;
            }
        } catch (IOException x) {
            System.err.println(x);
        }

        return new ResponseEntity<>(ans, HttpStatus.OK);
    }

}
