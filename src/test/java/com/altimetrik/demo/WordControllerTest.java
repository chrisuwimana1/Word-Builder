package com.altimetrik.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class WordControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WordService wordService ;

    @InjectMocks
    private WordController wordController = new WordController();

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
       List<String> words = new ArrayList<>();
        words.add("me");

       String input = "this is it";
       int length = 2;

       String url = "http://localhost:8888/wordbuilder/" + input + "/"+length;

       //wordService.cleanSet();

        Mockito.when(restTemplate.getForEntity(url, ResponseEntity.class))
          .thenReturn(new ResponseEntity(words, HttpStatus.OK));

        ResponseEntity<List<String>> returnedWords =  wordController.getValidwords("em", 2);
        Assert.assertEquals(words, returnedWords);

    }


}
