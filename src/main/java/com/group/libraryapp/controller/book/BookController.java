package com.group.libraryapp.controller.book;

import com.group.libraryapp.DTO.book.request.BookLoanRequest;
import com.group.libraryapp.DTO.book.request.BookRequest;
import com.group.libraryapp.DTO.book.request.BookReturnRequest;
import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("/book")
    public void saveBook(@RequestBody BookRequest request) {
        service.saveBook(request);
    }
    @PostMapping("/book/loan")
    public void loanBook(@RequestBody BookLoanRequest request) {
        service.loanBook(request);
    }
    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequest request) {
        service.returnBook(request);
    }

}
