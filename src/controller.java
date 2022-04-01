


import java.net.MalformedURLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import weatherCode.APIconnect;

public class controller {

    @FXML
    private TextField cityChoice;

    @FXML
    private TextField humidityBox;

    @FXML
    private TextField minTempField;

    @FXML
    private TextField windSpeedBox;

    private final static String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final static String weatherAPI = "https://www.metaweather.com/api/location/";


    @FXML
    void enterdKeyIsPressed(ActionEvent event) {
        try{
            String city = cityChoice.getText();

            //get the woeid of the desired city
            String woeid = getWoeid(city);
            System.out.println(woeid);
            //now to get the information
            JSONObject weatherInfo = getWeather(woeid);
            System.out.println(weatherInfo.toJSONString());

            String humidity = weatherInfo.get("humidity").toString();
            
            humidityBox.setText(humidity);

            //set the mintemp
            windSpeedBox.setText(weatherInfo.get("wind_speed").toString());

            //set the wind speed
            minTempField.setText(weatherInfo.get("min_temp").toString());
            
            

            

        } catch(Exception e){
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
