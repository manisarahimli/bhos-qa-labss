package com.example.springproj3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController

@RequestMapping("/api")

public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/books")
    public String saveBook(@RequestBody Book book) throws ExecutionException, InterruptedException {

        return BookService.saveBook(book);

    }

    @GetMapping("/books/{name}")
    public Book getBookDetails(@PathVariable String name) throws ExecutionException, InterruptedException {

        return BookService.getBookDetails(name);

    }

}
