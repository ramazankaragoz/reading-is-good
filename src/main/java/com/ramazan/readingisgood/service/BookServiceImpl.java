package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.entity.Book;
import com.ramazan.readingisgood.exception.BookAlreadyExistException;
import com.ramazan.readingisgood.repository.BookRepository;
import com.ramazan.readingisgood.util.StockOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {

        if (bookRepository.existsByAuthorAndName(book.getAuthor(),book.getName())){
            throw new BookAlreadyExistException("The book already exists in the system.");
        }

        return bookRepository.save(book);
    }

    @Override
    public boolean updateBookFromStock(UUID fkBookId, Integer quantity, StockOperation stockOperation) {



        return false;
    }
}
