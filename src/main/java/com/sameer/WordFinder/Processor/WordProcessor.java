package com.sameer.WordFinder.Processor;

import java.util.ArrayList;
import java.util.List;

public class WordProcessor {

	List<String> permuteList;
	
	public List<String> getPermuteList() {
		return permuteList;
	}
	public void setPermuteList(List<String> permuteList) {
		this.permuteList = permuteList;
	}
	public WordProcessor() {
		permuteList = new ArrayList<String>();
	}
	public  void permute(String str, int l, int r){
		if (l == r) 
			permuteList.add(str);
        else
        { 
            for (int i = l; i <= r; i++) 
            { 
                str = swap(str,l,i); 
                permute(str, l+1, r); 
                str = swap(str,l,i); 
            } 
        } 
	}
	
	public String swap(String a, int i, int j) 
    { 
        char temp; 
        char[] charArray = a.toCharArray(); 
        temp = charArray[i] ; 
        charArray[i] = charArray[j]; 
        charArray[j] = temp; 
        return String.valueOf(charArray); 
    } 
}
