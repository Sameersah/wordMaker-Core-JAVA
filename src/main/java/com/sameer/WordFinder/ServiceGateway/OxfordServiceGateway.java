package com.sameer.WordFinder.ServiceGateway;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONML;
import org.json.JSONObject;

public class OxfordServiceGateway {
	
	private  String meaning;
	
	public OxfordServiceGateway() {
		meaning = new String();
	}

	public  boolean isEnglishWord(String word) {
		try {
	    	String url  = "https://od-api.oxforddictionaries.com/api/v1/entries/en/";
	        Client client = ClientBuilder.newClient(); 
	         WebTarget target =  client.target(url+word);
	         String str = target.request(MediaType.APPLICATION_JSON).header("app_id", "dc6d6104")
	        		 .header("app_key", "615a27e265b2687e20a57427912c89f2").get(String.class);


	         try {
                 System.out.println("Found: "+word);
             //    System.out.println(str);
	        	 JSONObject jsonobj = new JSONObject(str);
                 JSONArray resultsArray = jsonobj.getJSONArray("results");
                 JSONObject resultsArrayFirstObject = resultsArray.getJSONObject(0);
                 JSONArray lexicalEntriesArray = resultsArrayFirstObject.getJSONArray("lexicalEntries");
                 for(int i=0;i<lexicalEntriesArray.length();i++){
                     JSONObject lexicalEntriesObject = lexicalEntriesArray.getJSONObject(i);
                     JSONArray entriesArray = lexicalEntriesObject.getJSONArray("entries");
                     JSONObject entriesFirstObject = entriesArray.getJSONObject(0);
                     JSONArray sensesArray = entriesFirstObject.getJSONArray("senses");
                     for(int j=0;j<sensesArray.length();j++){
                         JSONObject sensesFirstObject = sensesArray.getJSONObject(j);
                         if(sensesFirstObject.has("definitions")){
                             JSONArray definitionsArray = sensesFirstObject.getJSONArray("definitions");
                             System.out.println(i+1+"."+j+". "+definitionsArray.getString(0));
                         }

                     }


                    System.out.println();
                 }


			} catch (JSONException e) {
				e.printStackTrace();
			}

	   meaning = " ";
	    	}
	    	catch(NotFoundException e) {

	    		return false;
	    	}
		return true;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
}
