package com.asib27.authentication.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Book> getBookByIsbn(String isbn){
        System.out.println(isbn);
        System.out.println(bookRepository.findById(isbn));
        return bookRepository.findById(isbn);
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
}

