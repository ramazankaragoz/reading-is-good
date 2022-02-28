package com.ramazan.readingisgood.controller;

import com.ramazan.readingisgood.entity.Book;
import com.ramazan.readingisgood.entity.Stock;
import com.ramazan.readingisgood.service.BookService;
import com.ramazan.readingisgood.util.StockOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;


@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Will persist new book.You can enter the amount to add stock as a quantitiy parameter")
    @PostMapping("/{quantity}")
    public ResponseEntity<Book> save(@RequestBody @Valid Book book,
                                     @PathVariable("quantity") @Size(min = 1) Integer quantity){
        return new ResponseEntity<>(bookService.save(book,quantity), HttpStatus.CREATED);
    }

    @Operation(summary = "Will update book’s stock.The stockoperation parameter manages the adding and remove.")
    @PutMapping("/update-book-from-stock/{id}/{quantity}/{stockOperation}")
    public ResponseEntity<Stock> updateBookFromStock(@PathVariable("id") UUID fkBookId,
                                                     @PathVariable("quantity") @Size(min = 1) Integer quantity,
                                                     @PathVariable("stockOperation") StockOperation stockOperation){

        return new ResponseEntity<>(bookService.updateBookFromStock(fkBookId,quantity,stockOperation),HttpStatus.OK);
    }

}
