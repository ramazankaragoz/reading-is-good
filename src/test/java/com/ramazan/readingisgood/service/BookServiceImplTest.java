package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.entity.Book;
import com.ramazan.readingisgood.repository.BookRepository;
import com.ramazan.readingisgood.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookServiceImplTest Unit Tests")
class BookServiceImplTest {

    @InjectMocks
    BookServiceImpl bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    StockRepository stockRepository;

    private Book book;

    @BeforeEach
    void setUp() {
        book=new Book();
        book.setName(UUID.randomUUID().toString().substring(0,5) +"@test.com");
        book.setAuthor("ramazan");
        book.setPrice(Math.random());
    }

    @Test
    void test_book_when_save() {
        bookService.save(book,5);
        verify(bookRepository,times(1)).save(book);
    }
}