package com.estsoft.springproject.book.controller;

import com.estsoft.springproject.book.domain.dto.BookDTO;
import com.estsoft.springproject.book.domain.entity.Book;
import com.estsoft.springproject.book.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public String AllBooks(Model model) {
        List<Book> bookList = service.findBooks();
        List<BookDTO> bookDTOList = bookList.stream().map(BookDTO::new).toList();
        model.addAttribute("bookList", bookDTOList);
        return "bookManagement";
    }

    @GetMapping("/{id}")
    public String OneBook(@PathVariable String id, Model model) {
        BookDTO bookDTO = new BookDTO(service.findByBook(id));
        model.addAttribute("book", bookDTO);
        return "bookDetail";
    }

    @PostMapping
    public String addBook(@RequestParam String id, @RequestParam String name, @RequestParam String author) {
        service.addBook(new Book(id, name, author));
        return "redirect:/books"; //    GET /books
    }
}
