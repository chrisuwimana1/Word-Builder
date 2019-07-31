package com.altimetrik.demo;

import java.util.ArrayList;
import java.util.List;

public class WordPermutations {


    String input = "this is it";
    int length = 4;

   // List<String> listOfWords = getWords(input, length);

    public static void getWords(String input, int k){

        //String inputString = input.trim();

        char[] arrayOfChars = input.trim().toCharArray();
        int arrayLength = arrayOfChars.length;

        printAllKLengthRec(arrayOfChars,"",arrayLength, k);
    }

   public static void printAllKLengthRec(char[] set, String prefix, int n, int k) {
       // Base case: k is 0,
       // print prefix
       List<String> words = new ArrayList<>();
       if (k == 0)
       {
           System.out.println(prefix);
           return;
       }
       // One by one add all characters
       // from set and recursively
       // call for k equals to k-1
       for (int i = 0; i < n; ++i)
       {

           // Next character of input added
           String newPrefix = prefix + set[i];
            words.add(prefix);
           // k is decreased, because
           // we have added a new character
           printAllKLengthRec(set, newPrefix,
                   n, k - 1);
       }
       System.out.println(words.size());
   }
}
