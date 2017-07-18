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
public class Status {
    private static final String CODE = "code";
    private static final String ERROR_TYPE = "errorType";
    
    private long code;
    private String errorType;
    
    public Status(JSONObject jStatus){
        this.code = (long) jStatus.get(CODE);
        this.errorType = (String) jStatus.get(ERROR_TYPE);
    }
    
    public JSONObject toJSON(){
        JSONObject jStatus = new JSONObject();
        jStatus.put(CODE, this.code);
        jStatus.put(ERROR_TYPE, errorType);
        return jStatus;
    }

    public long getCode() {
        return code;
    }

    public String getErrorType() {
        return errorType;
    }
    
}
