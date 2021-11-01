package com.galvanize.tmo.paspringstarter.repository;

import com.galvanize.tmo.paspringstarter.model.Library;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;

@Repository
public interface LibraryRepo extends JpaRepository<Library, Long> {
    @Override
    Library save(Library lib);


    @Query("FROM Library ORDER BY title ASC")
    List<Library> findAllOrderByTitleAsc();
}

