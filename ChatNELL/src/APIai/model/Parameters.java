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
public class Parameters {
    private static final String SIMPLIFIED = "simplified";
    private static final String CATEGORY_INSTANCE = "category-instance";
    private static final String CATEGORY_NAME = "category_name";
    
    private String simplified;
    private String category_instance;
    private String category_name;
    
    public Parameters(JSONObject jParameters){
        this.simplified = (String) jParameters.get(SIMPLIFIED);
        this.category_instance = (String) jParameters.get(CATEGORY_INSTANCE);
        this.category_name = (String) jParameters.get(CATEGORY_NAME);
    }

    public String getCategoryInstance() {
        return category_instance;
    }
    
    public String getSimplified() {
        return simplified;
    }

    public String getCategory_name() {
        return category_name;
    }
    
}
