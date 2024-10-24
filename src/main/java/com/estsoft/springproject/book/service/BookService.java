package com.estsoft.springproject.book.service;

import com.estsoft.springproject.book.domain.entity.Book;
import com.estsoft.springproject.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findBooks() {
        return repository.findAll(Sort.by("id").descending()); // Select (id,name,author) from book order by id;
    }

    public Book findByBook(String id){
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("not find id : " + id));
    }

    public Book addBook(Book book){
        return repository.save(book);
    }
}
