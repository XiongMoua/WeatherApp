package weatherCode;

import java.net.MalformedURLException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class mainWeather {
    public final static String cityAPI = "https://www.metaweather.com/api/location/search/?query=";
        
    public final static String weatherAPI = "https://www.metaweather.com/api/location/";
    public static void main(String[] args){

        System.out.println("Enter in a city that you want to know the temperature of: ");
        Scanner input = new Scanner(System.in);

        String user_input = input.nextLine();


        System.out.println(user_input);
        System.out.println(cityAPI + user_input);

        try {

            // APIconnect test = new APIconnect(cityAPI);
            // JSONObject done = (JSONObject) test.getJsonArray(user_input).get(0);
            // System.out.println(done.get("woeid"));
            
            //this gets the woeid for the user's desired city
            String woeid = getWoeid(user_input);

            
            //inputs the woeid to get the weather's information from the website
            JSONObject weatherInfo = getWeather(woeid);
            // getting all the information needed
            System.out.println("Minimum temperature: " + weatherInfo.get("min_temp"));
            System.out.println("Wind Speed: " + weatherInfo.get("wind_speed"));
            System.out.println("Humidity: " + weatherInfo.get("humidity"));

        

              
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getWoeid(String desiredCity) throws MalformedURLException {

        APIconnect connectToCity = new APIconnect(cityAPI);

        JSONObject done = (JSONObject) connectToCity.getJsonArray(desiredCity).get(0);
        System.out.println(done.get("woeid"));

        return done.get("woeid").toString();
        
    }

    public static JSONObject getWeather(String woeid) throws MalformedURLException{

        APIconnect weatherConnect = new APIconnect(weatherAPI);

        JSONObject weatherObj = weatherConnect.getJSONObject(woeid);

        //now to get the information in the array
        JSONArray weatherStuff = (JSONArray) weatherObj.get("consolidated_weather");
        JSONObject weatherInfo = (JSONObject) weatherStuff.get(0);

        return weatherInfo;
 
    }
}
