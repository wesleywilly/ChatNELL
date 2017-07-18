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
public class Metadata {
    private static final String INTENT_NAME = "intentName";
    
    private String intent_name;
    
    public Metadata(JSONObject jMetadata){
        this.intent_name = (String) jMetadata.get(INTENT_NAME);
    }

    public String getIntent_name() {
        return intent_name;
    }
    
}