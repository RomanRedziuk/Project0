package com.rr.bookslibrary.controller;

import com.rr.bookslibrary.Book;
import com.rr.bookslibrary.BookStorage;
import com.rr.bookslibrary.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BookController
{
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private BookStorage bookStorage;

    public BookController(BookStorage bookStorage)
    {
        this.bookStorage = bookStorage;
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookStorage.getBooks();
    }

    @PostMapping("/books")
    public void addBooks(@RequestBody Book book) {
        bookStorage.getBooks().add(book);
    }
}
