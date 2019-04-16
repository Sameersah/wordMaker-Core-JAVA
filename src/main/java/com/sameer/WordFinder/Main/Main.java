package com.sameer.WordFinder.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sameer.WordFinder.Processor.WordProcessor;
import com.sameer.WordFinder.ServiceGateway.OxfordServiceGateway;

public class Main {


	
	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Alphabet Input- ");
		String input = sc.next();
	//	System.out.println("Your Input is- "+input);
		WordProcessor processor = new WordProcessor();
		int startIndex = 0;
		int endIndex = input.length()-1;
		processor.permute(input, startIndex, endIndex);
		List<String> permuteList = processor.getPermuteList();
		OxfordServiceGateway dictionary  = new OxfordServiceGateway();
		List<String>englishWordList = new ArrayList<String>();
		for(String word: permuteList) {
			System.out.println("Checking Oxford for permutation- "+word);
			if(dictionary.isEnglishWord(word)) {
			//	System.out.println(word+"- Meaning: "+dictionary.getMeaning()+" ");
				System.out.println("It is an english word.");
				englishWordList.add(word);
			}
			else {
			//	System.out.println("Not an english word.");
			}
			System.out.println("\n");
		}
		
		
		sc.close();
	}
}
