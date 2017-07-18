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
public class Result {
    private static final String SCORE = "score";
    private static final String METADATA = "metadata";
    private static final String RESOLVED_QUERY = "resolvedQuery";
    private static final String ACTION = "action";
    private static final String SOURCE = "source";
    private static final String FULFILLMENT = "fulfillment";
    private static final String PARAMETERS = "parameters";
    
    private double score;
    private Metadata metadata;
    private String resolvedQuery;
    private String action;
    private String source;
    private Fulfillment fulfillment;
    private Parameters parameters;
    
    public Result(JSONObject jResult){
        this.score = (double) jResult.get(SCORE);
        this.metadata = new Metadata((JSONObject) jResult.get(METADATA));
        this.resolvedQuery = (String) jResult.get(RESOLVED_QUERY);
        this.action = (String) jResult.get(ACTION);
        this.source = (String) jResult.get(SOURCE);
        this.fulfillment = new Fulfillment((JSONObject) jResult.get(FULFILLMENT));
        this.parameters = new Parameters((JSONObject) jResult.get(PARAMETERS));
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public double getScore() {
        return score;
    }

    public String getResolvedQuery() {
        return resolvedQuery;
    }

    public String getAction() {
        return action;
    }

    public String getSource() {
        return source;
    }

    public Fulfillment getFulfillment() {
        return fulfillment;
    }

    public Parameters getParameters() {
        return parameters;
    }
           
    
}
