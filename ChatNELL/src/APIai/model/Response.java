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
public class Response {
    private static final String RESULT = "result";
    private static final String ID = "id";
    private static final String SESSION_ID = "sessionId";
    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS = "status";
    
    private Result result;
    private String id;
    private String session_id;
    private String timestamp;
    private Status status;
    
    public Response(JSONObject jResponse){
        this.result = new Result((JSONObject) jResponse.get(RESULT));
        this.id = (String) jResponse.get(ID);
        this.session_id = (String) jResponse.get(SESSION_ID);
        this.timestamp = (String) jResponse.get(TIMESTAMP);
        this.status = new Status((JSONObject) jResponse.get(STATUS));
    }

    public Result getResult() {
        return result;
    }

    public String getId() {
        return id;
    }

    public String getSessionId() {
        return session_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Status getStatus() {
        return status;
    }
    
    
}
