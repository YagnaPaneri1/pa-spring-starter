package com.galvanize.tmo.paspringstarter.repository;

import com.galvanize.tmo.paspringstarter.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepo extends JpaRepository<Book, Long> {
    @Override
    Book save(Book lib);


    @Query("FROM Book ORDER BY title ASC")
    List<Book> findAllOrderByTitleAsc();
}

