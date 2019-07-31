package com.altimetrik.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WordController {

    @Autowired
    private WordService wordService;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/wordbuilder/{phrase}/{length}")
    public ResponseEntity<List<String>> getValidwords(@PathVariable String phrase, @PathVariable int length){
        wordService.cleanSet();
        List<String> allWords = new ArrayList<>();
        String trimmedPhrase = phrase.trim().replaceAll("\\s","");
        System.out.println(trimmedPhrase);
        if (wordService.isStringOnlyAlphabet(trimmedPhrase) ==false ||  trimmedPhrase.length() == 0 || length == 0) {
            return new ResponseEntity<>(allWords, HttpStatus.BAD_REQUEST);
        }
        wordService.permuteWords(trimmedPhrase, length);
        Set<String> words = wordService.getWords();

        for(String word : words){
            if(isValidWord(word) == true){
                allWords.add(word);
            }
        }
        return ResponseEntity.ok(allWords);
    }

    private boolean isValidWord(String word) {
		try {
			HttpHeaders headers = new HttpHeaders();
			final String resourceUrl = "https://owlbot.info/api/v3/dictionary/"+ word;
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.add("Authorization", "Token d6feafe95338e2d7ee98cb6e3c2d88c686549d78");
		    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		    HttpEntity<String> entity = new HttpEntity<>("", headers);
		    ResponseEntity<String> result = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, String.class);
			if(result.getStatusCodeValue() == 200) return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}

}
