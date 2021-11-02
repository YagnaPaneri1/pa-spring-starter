package com.galvanize.tmo.paspringstarter.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String author;
    public String title;
    public int yearPublished;

}
