/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIai.connection;

import APIai.model.Response;
import java.net.URLEncoder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author wesley
 */
public class ApiAiConnector {

    private static final String TOKEN = "50843d1200814a19aec5dba7a89a59f0";

    private static final String URL = "https://api.api.ai/api/query?v=20150910";
    private static final String QUERY_PREFIX = "&query=";
    private static final String LANG = "&lang=en";
    private static final String SESSION = "&sessionId=2815d11d-018d-42d8-adc7-6eb933971039";
    private static final String TIMEZONE = "&timezone=2016-12-22T14:10:59-0200";

    private static final String ENC = "UTF-8";

    public static Response getResponse(String question) {
        Response response = null;

        try {
            String query = URLEncoder.encode(question, ENC);

            boolean retrieved = false;
            while (!retrieved) {

                try {

                    Document doc = Jsoup.connect(URL + QUERY_PREFIX + query + LANG + SESSION + TIMEZONE)
                            .header("Authorization", "Bearer " + TOKEN)
                            .header("Content-Type", "application/json;charset=UTF-8")
                            .ignoreContentType(true)
                            .get();

                    String body = doc.getElementsByTag("body").first().text();
                    JSONParser parser = new JSONParser();
                    JSONObject jResult = (JSONObject) parser.parse(body);
                    response = new Response(jResult);
                    
                    System.out.println("[API.AI CONNECTOR] RESPONSE RETRIEVED!");
                    retrieved = true;

                } catch (Exception e) {
                    System.out.println("[API.AI CONNECTOR] CONNECTION ERROR!");
                    e.printStackTrace();
                    System.out.println("[API.AI CONNECTOR] TRYING AGAIN IN 3 SECONDS...");
                    try {
                        Thread.sleep(3000);
                    } catch (Exception et) {
                        System.out.println("[API.AI CONNECTOR] TRYING NOW!");
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("[API.AI CONNECTOR] ENCODING ERROR!");
            //e.printStackTrace();
        }

        return response;
    }
}
