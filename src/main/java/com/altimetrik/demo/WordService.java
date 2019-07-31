package com.altimetrik.demo;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordService {


    private Set<String> words;

    public WordService(){
        words = new HashSet<>();
    }

    public  Set<String> getWords(){
        return words;
    }

    public void permuteWords(String input, int k){

        char[] arrayOfChars = input.toCharArray();
        int arrayLength = arrayOfChars.length;
        getAllWordsRecursively(arrayOfChars,"",arrayLength, k);
    }

    public void getAllWordsRecursively(char[] set, String prefix, int n, int k) {

        //Base case
        if (k == 0) {
            words.add(prefix);
            return;
        }

        for (int i = 0; i < n; ++i) {
            String newPrefix = prefix + set[i];
            getAllWordsRecursively(set, newPrefix, n, k - 1);
        }
    }

    public boolean isStringOnlyAlphabet(String str)
    {
        return ((!str.equals(""))
                && (str != null)
                && (str.matches("^[a-zA-Z]*$")));
    }

    public void cleanSet(){
        words.clear();
    }

}
