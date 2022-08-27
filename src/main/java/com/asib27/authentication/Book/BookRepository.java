package com.asib27.authentication.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String>{
    @Query(value = "select b.isbn from books as b where b.name = ?1", nativeQuery = true)
    String getBookIdByName(String book_name);

    @Query(value = "select * from books order by random() limit ?1 ",nativeQuery = true)
    List<Book> getRandomBooks(int n);
}
