package com.sameer.WordFinder.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sameer.WordFinder.Processor.WordProcessor;
import com.sameer.WordFinder.ServiceGateway.OxfordServiceGateway;

public class Main {


	
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Input a String (any combination of letters)-(For testing purpose try to limit the length to 4.)");
		String input = sc.next();
		WordProcessor processor = new WordProcessor();
		int startIndex = 0;
		int endIndex = input.length()-1;
		processor.permute(input, startIndex, endIndex);
		List<String> permuteList = processor.getPermuteList();
		OxfordServiceGateway dictionary  = new OxfordServiceGateway();
		List<String>englishWordList = new ArrayList<String>();
		for(String word: permuteList) {
			if(dictionary.isEnglishWord(word)) {
				englishWordList.add(word);
			}
		}
		
		
		sc.close();
	}
}
