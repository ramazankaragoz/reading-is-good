package com.ramazan.readingisgood.integrationtest;

import com.ramazan.readingisgood.controller.BookController;
import com.ramazan.readingisgood.entity.Book;
import com.ramazan.readingisgood.entity.Stock;
import com.ramazan.readingisgood.util.StockOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookControllerIntegrationTest {

    @Autowired
    BookController bookController;

    private Book book;

    @BeforeEach
    void setUp() {
        book=new Book();
        book.setName(UUID.randomUUID().toString().substring(0,5) +"@test.com");
        book.setAuthor("ramazan");
        book.setPrice(Math.random());
    }

    @Test
    public void test_book_when_save(){

        ResponseEntity<Book> bookResponseEntity=bookController.save(book,5);
        Assertions.assertTrue(bookResponseEntity.getStatusCode().equals(HttpStatus.CREATED));

    }

    @Test
    public void test_book_when_update(){

        ResponseEntity<Book> bookResponseEntity=bookController.save(book,5);
        Assertions.assertTrue(bookResponseEntity.getStatusCode().equals(HttpStatus.CREATED));
        Book book = bookResponseEntity.getBody();

        ResponseEntity<Stock> bookUpdateResult=bookController.updateBookFromStock(book.getId(),5, StockOperation.REMOVE);
        Assertions.assertTrue(bookUpdateResult.getStatusCode().equals(HttpStatus.OK));

    }
}
