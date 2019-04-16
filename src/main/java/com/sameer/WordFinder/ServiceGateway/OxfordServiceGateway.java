package com.sameer.WordFinder.ServiceGateway;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
		//	System.out.println();
		//	System.out.println("Querying Oxford. Checking: "+word);
	    	String url  = "https://od-api.oxforddictionaries.com/api/v1/entries/en/";
	        Client client = ClientBuilder.newClient(); 
	         WebTarget target =  client.target(url+word);
	         String str = target.request(MediaType.APPLICATION_JSON).header("app_id", "dc6d6104")
	        		 .header("app_key", "615a27e265b2687e20a57427912c89f2").get(String.class);
	           
	         
	         try {
	        	 JSONObject jsonobj = new JSONObject(str);
				JSONObject results = new JSONObject(jsonobj.get("results"));
			//	System.out.println(results);
			//	JSONObject lexicalEntries = new JSONObject(results.get("lexicalEntries").toString());
			//	JSONObject entries = new JSONObject(lexicalEntries.get("entries"));
			//	JSONObject senses = new JSONObject(entries.get("senses"));
				//JSONObject definitions = senses.getJSONObject("definitions");
			//	meaning = senses.get("definitions").toString();
	        	 
	        	// meaning = jsonobj.results.lexicalEntries.entries.senses.definitions;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
		//		System.out.println("Error while geting json data.");
				e.printStackTrace();
			}
	         	
	   meaning = " ";
	    	}
	    	catch(NotFoundException e) {
	    //		System.out.println("Not an english word. "+e.getMessage());
	    	//	meaning = "Not found.";
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
