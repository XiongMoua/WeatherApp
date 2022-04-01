package weatherCode;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class APIconnect {

    private final String urlString;

    public APIconnect(String url) throws MalformedURLException {
        urlString = url;
    }

    //getting the string values and objects
    //get JSONArray -> [ , , , ]
    public org.json.simple.JSONArray getJsonArray (String query){
        try{
            //setting up the url
            URL url = new URL(urlString + query);

            //connecting to the url
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            connect.connect();

            //check if the connection is successful '200'
            int responseCode = connect.getResponseCode();

            if(responseCode != 200){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
            else{
                StringBuilder connectString = new StringBuilder();
                Scanner scanning = new Scanner(url.openStream());
                
                while(scanning.hasNext()){
                    connectString.append(scanning.nextLine());
                }
                //remember to close the scanner
                scanning.close();
                System.out.println(connectString);

                //now to parse the string into a JSON object
                JSONParser parse = new JSONParser();
                org.json.simple.JSONArray array = (org.json.simple.JSONArray) parse.parse(String.valueOf(connectString));

                return array;

            }
        } catch (Exception e){
            e.printStackTrace();
        }

        //if it gets to here
        return null;
    }

    //getJSONObject -> { , , ,}
    public JSONObject getJSONObject (String query) throws MalformedURLException{

        try{
            URL url = new URL(urlString + query);

            //connect to the url
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            connect.connect();

            //check if the connection is successful
            int responseCode = connect.getResponseCode();

            if(responseCode != 200){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }else{
                StringBuilder connectString = new StringBuilder();
                Scanner scanning = new Scanner(url.openStream());
                
                while(scanning.hasNext()){
                    connectString.append(scanning.nextLine());
                }
                //remember to close the scanner
                scanning.close();

                //now to parse the string into a JSON object
                JSONParser parse = new JSONParser();
                JSONObject object = (JSONObject) parse.parse(String.valueOf(connectString));

                return object;
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }

        //if it gets to here
        return null;
    
    }


}