package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.entity.Book;
import com.ramazan.readingisgood.entity.Stock;
import com.ramazan.readingisgood.exception.BookAlreadyExistException;
import com.ramazan.readingisgood.exception.StockInvalidQuantityException;
import com.ramazan.readingisgood.exception.StockNotFoundException;
import com.ramazan.readingisgood.repository.BookRepository;
import com.ramazan.readingisgood.repository.StockRepository;
import com.ramazan.readingisgood.util.StockOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;


@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final StockRepository stockRepository;

    public BookServiceImpl(BookRepository bookRepository, StockRepository stockRepository) {
        this.bookRepository = bookRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public Book save(Book book, Integer quantity) {

        if (bookRepository.existsByAuthorAndName(book.getAuthor(), book.getName())) {
            throw new BookAlreadyExistException("The book already exists in the system.");
        }

        var savedBook = bookRepository.save(book);
        Stock stock = new Stock();
        stock.setBook(savedBook);
        stock.setIsAvailable(Boolean.TRUE);
        stock.setQuantity(quantity);
        stockRepository.save(stock);
        return savedBook;
    }

    @Override
    public Stock updateBookFromStock(UUID fkBookId, Integer quantity, StockOperation stockOperation) {

        var stock = stockRepository.findByBookId(fkBookId)
                .orElseThrow(() -> new StockNotFoundException("There are not enough books in stock for this transaction."));

        if (Objects.nonNull(stock.getId()) && StockOperation.REMOVE.equals(stockOperation)
                && stock.getQuantity() < quantity) {
            throw new StockInvalidQuantityException("Not enough books in stock for this operation.");
        }

        return updateStockInformation(quantity, stockOperation, stock);
    }

    private Stock updateStockInformation(Integer quantity, StockOperation stockOperation, Stock stock) {
        switch (stockOperation) {
            case ADD:
                stock.setQuantity(stock.getQuantity() + quantity);
                break;
            case REMOVE:
                stock.setQuantity(stock.getQuantity() - quantity);
                break;
            case PASSIVE:
                stock.setDeleted(Boolean.TRUE);
                break;
        }
        return stockRepository.save(stock);
    }
}
