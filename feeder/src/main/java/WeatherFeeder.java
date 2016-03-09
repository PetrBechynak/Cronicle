import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by pbechynak on 3.3.2016.
 */
public class WeatherFeeder {
    public WeatherFeeder () {
    }

    public void feed() throws IOException {
        // https://api.forecast.io/forecast/1e2b157160da9ecd35e4003d7111a2d7/50,14.4,2013-12-06T12:00:00-0400

        for (Integer i =0; i<500;i++){
            Weather weather = getWeatherWhen(new DateTime().plusDays(-i));
            saveIntoCronicle(weather);
        }
    }

    private void saveIntoCronicle(Weather weather) throws IOException {
        String CrUrlString = "http://localhost:8080/store";
        URL obj = new URL(CrUrlString);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla / 5.0 (Windows NT 6.3; rv:36.0) Gecko/20100101 Firefox/36.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "time="+weather.currently.time+"&summary="+weather.currently.summary+
                "&temperature="+weather.currently.temperature+"&apparenttemperature="+weather.currently.apparentTemperature+"&humidity="+ weather.currently.humidity
                +"&cloudcover="+weather.currently.cloudCover;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
    }



    public static void main(String[] args) throws IOException {
        WeatherFeeder wf = new WeatherFeeder();
        wf.feed();

    }

    public Weather getWeatherWhen(DateTime date) throws IOException {
        String urlString = "https://api.forecast.io/forecast/1e2b157160da9ecd35e4003d7111a2d7/50,14.4,"+ date.toLocalDate()+"T12:00:00+0100?units=si";
        URL url = new URL(urlString);
        URLConnection forecastConn = url.openConnection();
        InputStream is = forecastConn.getInputStream();
        System.out.println(is);

        ObjectMapper mapper = new ObjectMapper();

        //JSON from String to Object
        Weather weather = mapper.readValue(is, Weather.class);
        System.out.println(weather.currently.temperature);
        return weather;
    }
}
