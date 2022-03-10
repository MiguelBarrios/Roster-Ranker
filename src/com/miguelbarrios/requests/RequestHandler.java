package com.miguelbarrios.requests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.miguelbarrios.util.Config;

public class RequestHandler {
	
	public String sendGetRequest(String requestUrl) {
        
		try{
           // String rankingsRequest = "https://api.collegefootballdata.com/rankings?year=2021&week=1&seasonType=regular";
			URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            
            //Add Auth Token
            String auth = String.format("Bearer %s", Config.getAuthToken());
            connection.setRequestProperty("Authorization", auth);
            
            // get response from server
            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuilder response = new StringBuilder();
           
            String line;
            while((line = bufferedReader.readLine()) != null)
            {
                response.append(line);
            }

            bufferedReader.close();
            return response.toString();
        }
        catch (Exception e)
        {
            System.err.println("Error fetching request: " + requestUrl);
        }
		
		return "";
        
	}
}
