package com.asib27.authentication.Book;

import com.asib27.authentication.Publisher.Publisher;
import com.asib27.authentication.Publisher.PublisherService;
import com.asib27.authentication.Writer.Writer;
import com.asib27.authentication.Writer.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    WriterService writerService;
    @Autowired
    PublisherService publisherService;

    @GetMapping("/")
    public List<Book> getAllBooks(@RequestParam(name = "type", defaultValue = "random") String type,
        @RequestParam(name = "count", defaultValue = "10") int count
    
    ){
        if(type.equals("random")){
            return bookService.getRandomBooks(count);
        }
        else if(type.equals("top")){
            return bookService.getRandomBooks(count);
        }
        else return bookService.getRandomBooks(count);
    }

    @GetMapping("/get/{book_name}")
    public String getBookIdByName(@PathVariable String book_name){
        // System.out.println("called" + book_name);
        String x = bookService.getBookIdByName(book_name);
        // System.out.println(x);
        return x;
    }

    @GetMapping("/{isbn}")
    public Optional<Book> getBookByIsbn(@PathVariable String isbn){
        // System.out.println("called" + book_name);
        Optional<Book> book = bookService.getBookByIsbn(isbn);
        // System.out.println(x);
        return book;
    }

    @PostMapping("/add")
    public String addNewBook(@RequestBody Book book){
        bookService.addNewBook(book);
        return "New book added";
    }

    @DeleteMapping(path = "/delete/{bookId}")
    public String deleteBook(@PathVariable("bookId") String bookId){
        bookService.deleteBook(bookId);
        return "Book with id "+ bookId + " is deleted.";
    }

    @PutMapping("/add/{bookName}/writer/{writerName}")
    public Book authorsOfBook(@PathVariable String bookName, @PathVariable String writerName){
        String bookId = bookService.getBookIdByName(bookName);
        Book book = bookService.getBook(bookId);
        Long writerId = writerService.getWriterIdByName(writerName);
        Writer writer = writerService.getAWriter(writerId);
        book.addWriters(writer);
        return bookService.addNewBook(book);
    }

    @GetMapping("/get/random/{count}")
    public List<Book> getRandomBooks(@PathVariable int count){
        return bookService.getRandomBooks(count);
    }

    @PutMapping("/add/{bookid}/published_by/{publisherId}")
    public Book publisherOfBook(@PathVariable String bookId, @PathVariable Long publisherId){
        Book book = bookService.getBook(bookId);
        Publisher publisher = publisherService.getPublisher(publisherId);
        book.addPublisher(publisher);
        return bookService.addNewBook(book);
    }

    @GetMapping("/get/bookLink")
    public String getBookImageLink(@RequestParam String bookName){
        return bookService.getLink(bookName);
    }
}

