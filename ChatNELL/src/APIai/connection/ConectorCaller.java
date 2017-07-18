/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIai.connection;

import APIai.model.Response;

/**
 *
 * @author wesley
 */
public class ConectorCaller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String question = "Who are Dilma?";
        Response response = ApiAiConnector.getResponse(question);
        
        String intent = "";
        if(response.getResult().getMetadata().getIntent_name() != null){
            intent = response.getResult().getMetadata().getIntent_name();
        }
        if(intent.equals(Intent.TO_KNOW_ABOUT_SOMEONE)){
            System.out.println(response.getResult().getParameters().getCategoryInstance());
        }else{
            System.out.println(response.getResult().getFulfillment().getSpeech());
        }
    }
    
}
