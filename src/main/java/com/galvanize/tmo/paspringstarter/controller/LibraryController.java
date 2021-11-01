package com.galvanize.tmo.paspringstarter.controller;

import com.galvanize.tmo.paspringstarter.model.Library;
import com.galvanize.tmo.paspringstarter.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryController {
    @Autowired
    LibraryRepo libraryRepo;
    @GetMapping("/health")
    public void health() {

    }

    @PostMapping(path = "/api/books")
    public ResponseEntity<Library> createTutorial(@RequestBody Library library) {
        try {
            Library lib = libraryRepo.save(library);
            return new ResponseEntity<>(lib, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<Library>> getAllBooks() {
        try {
            List<Library> lib = new ArrayList<>();
            libraryRepo.findAllOrderByTitleAsc().forEach(lib::add);
                if (lib.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(lib, HttpStatus.OK);
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
