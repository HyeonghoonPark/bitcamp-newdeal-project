package bcms.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class test {


    public static void main(String[] args) {
 
        try {
 
            String ClientId = "client_id"; //아이디
            String ClientSecret = "client_secret"; //패스워드
 
            String url = "http://www.google.com";
 
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
 
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
 
            conn.setRequestMethod("GET");
 
            conn.setRequestProperty("X-*****-Client-Id", ClientId); //header 에 값 셋팅
            conn.setRequestProperty("X-*****-Client-Secret", ClientSecret); //header 에 값 셋팅
 
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
 
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
 
            System.out.println(response.toString()); //결과, json결과를 parser하여 처리
 
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }


}
