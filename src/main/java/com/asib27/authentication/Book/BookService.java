package com.asib27.authentication.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book addNewBook(Book book) {
        return bookRepository.save(book);

    }

    public String getBookIdByName(String book_name){
        return bookRepository.getBookIdByName(book_name);
    }
    public void deleteBook(String bookname) {
        bookRepository.deleteById(bookname);
    }

    public Book getBook(String bookname) {
        boolean exist = bookRepository.existsById(bookname);
        if(!exist){
            throw new IllegalStateException("No book with given id !!");
        }
        return bookRepository.findById(bookname).get();
    }

    public List<Book> getRandomBooks(int count) {
        return bookRepository.getRandomBooks(count);
    }

    public String getLink(String bookname) {
        Book book = bookRepository.findById(bookname).get();
        return book.getLink();
    }

    public Book getBookData(String isbn) {
        return bookRepository.findById(isbn).get();
    }
}

