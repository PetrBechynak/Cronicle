package wsdl;

import com.google.protobuf.ServiceException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WSController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(method=RequestMethod.POST,value = "/store")
    public Store store(@RequestParam Map<String,String> requestParams) {
        String time=requestParams.get("time");
        String summary=requestParams.get("summary");
        String temperature=requestParams.get("temperature");
        String apparenttemperature=requestParams.get("apparenttemperature");
        String humidity=requestParams.get("humidity");
        String cloudcover=requestParams.get("cloudcover");

        return new Store(time, summary, temperature, apparenttemperature, humidity, cloudcover);
    }

    @RequestMapping(method=RequestMethod.GET,value = "/load")
    public WeatherHistory getWeatherHistory(@RequestParam Map<String,String> requestParams) throws IOException, ServiceException, ParseException {
        //GET localhost:8080/load?startdate=2015-01-01&enddate=2016-12-31
        String startdate=requestParams.get("startdate");
        String enddate=requestParams.get("enddate");

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date sDate = format.parse(startdate);
        System.out.println(sDate);
        Long startUnixTime = sDate.getTime()/1000;

        Date eDate = format.parse(enddate);
        System.out.println(eDate);
        Long endUnixTime = eDate.getTime()/1000;


        WeatherHistory weatherHistory = new WeatherHistory();
        weatherHistory.loadDataBetween(startUnixTime.toString(),endUnixTime.toString());

        return weatherHistory;
    }

}