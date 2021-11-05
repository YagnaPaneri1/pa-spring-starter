package com.galvanize.tmo.paspringstarter.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.galvanize.tmo.paspringstarter.model.Book;
import com.galvanize.tmo.paspringstarter.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.serializer.Serializer;

import java.io.Serializable;
import java.util.*;

@RestController
public class LibraryController {
    @Autowired
    LibraryRepo libraryRepo;
    @GetMapping("/health")
    public void health() {

    }

    @PostMapping(path = "/api/books")
    public ResponseEntity<Book> createTutorial(@RequestBody Book book) {
        try {
            Book lib = libraryRepo.save(book);
            return new ResponseEntity<>(lib, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/books")
    public ResponseEntity <String>getAllBooks() {
        try {
            List<Book> lib = libraryRepo.findAllOrderByTitleAsc();
                if (lib.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String,List<Book> >myHashMap =  new HashMap<>();
            myHashMap.put("Books",lib);
                ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(myHashMap);
            return new ResponseEntity<> (jsonString, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            libraryRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
