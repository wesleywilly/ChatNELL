/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIai.model;

import org.json.simple.JSONObject;

/**
 *
 * @author wesley
 */
public class Fulfillment {
    private static final String SPEECH = "speech";
    
    private String speech;
    
    public Fulfillment(JSONObject jFulfillment){
        this.speech = (String) jFulfillment.get(SPEECH);
    }

    public String getSpeech() {
        return speech;
    }
    
    
    
}
