package com.sameer.WordFinder.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sameer.WordFinder.Processor.WordProcessor;
import com.sameer.WordFinder.ServiceGateway.OxfordServiceGateway;

public class Main {

	public static void main(String args[]) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Input a String (MAXIMUM 4 LETTERS PLEASE !!!!!-)");
		System.out.println("Permutaion of 5 and above letters makes more than 100 API calls which is not allowed by Oxford APIs");
		System.out.println("for FREE accounts!!. Please enter the String -\n");
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
