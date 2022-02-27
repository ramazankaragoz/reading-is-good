package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.entity.Book;
import com.ramazan.readingisgood.util.StockOperation;

import java.util.UUID;

public interface BookService {

    Book save(Book book);

    boolean updateBookFromStock(UUID fkBookId, Integer quantity, StockOperation stockOperation);
}
