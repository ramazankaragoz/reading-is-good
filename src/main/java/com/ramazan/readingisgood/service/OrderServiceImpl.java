package com.ramazan.readingisgood.service;

import com.ramazan.readingisgood.dto.CreateOrderDTO;
import com.ramazan.readingisgood.dto.OrderDetailDTO;
import com.ramazan.readingisgood.entity.Order;
import com.ramazan.readingisgood.entity.OrderDetail;
import com.ramazan.readingisgood.exception.*;
import com.ramazan.readingisgood.repository.*;
import com.ramazan.readingisgood.util.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.stream.Collectors.groupingBy;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final StockRepository stockRepository;

    private final CustomerRepository customerRepository;

    private final BookRepository bookRepository;

    private final OrderDetailRepository orderDetailRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            StockRepository stockRepository,
                            CustomerRepository customerRepository,
                            BookRepository bookRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.stockRepository = stockRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public Order newOrder(CreateOrderDTO createOrderDTO, OrderStatus orderStatus) {

        var customer = customerRepository.findById(createOrderDTO.getFkCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));

        var orderDetailList = getOrderDetail(createOrderDTO.getOrderDetail());

        var totalAmount = calculateTotalAmount(orderDetailList);

        decreaseStock(orderDetailList);

        var order = new Order(customer, orderStatus, totalAmount,
                orderDetailList, createOrderDTO.getStartDate(), createOrderDTO.getEndDate());


        orderRepository.save(order);
        orderDetailList.forEach(orderDetail -> orderDetail.setOrder(order));
        orderDetailRepository.saveAll(orderDetailList);

        return order;
    }

    @Override
    public Order findById(UUID fkOrderId) {
        return orderRepository.findById(fkOrderId).orElseThrow(() -> new OrderNotFoundException("Order not found."));
    }

    @Override
    public List<Order> findBetweenByStartDateAndEndDate(Date endDate, Date startDate) {
        return orderRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(endDate, startDate)
                .orElseThrow(() -> new OrderNotFoundException("Order not found."));
    }

    private void decreaseStock(List<OrderDetail> orderDetailList) {
        for (OrderDetail orderDetail : orderDetailList) {
            var stock = stockRepository.findByBookId(orderDetail.getBook().getId())
                    .orElseThrow(() ->
                            new StockNotFoundException("There are not enough books in stock for this transaction."));

            if (stock.getQuantity() < orderDetail.getQuantity()) {
                throw new StockInvalidQuantityException("Not enough books in stock for this operation.");
            }

            stock.setQuantity(stock.getQuantity() - orderDetail.getQuantity());

            stockRepository.save(stock);
        }
    }

    private Double calculateTotalAmount(List<OrderDetail> orderDetailList) {
        var totalAmount = new AtomicReference<>(0.0);
        orderDetailList.forEach(orderDetail -> totalAmount.updateAndGet(v -> v + orderDetail.getAmount()));
        return totalAmount.get();
    }

    private List<OrderDetail> getOrderDetail(List<OrderDetailDTO> orderDetailDtoList) {
        var bookMap = orderDetailDtoList.stream()
                .collect(groupingBy(OrderDetailDTO::getFkBookId));
        List<OrderDetail> orderDetailList = new ArrayList<>();

        for (Map.Entry<UUID, List<OrderDetailDTO>> entry : bookMap.entrySet()) {
            var book = bookRepository.findById(entry.getKey())
                    .orElseThrow(() -> new BookNotFoundException("Book not found"));
            var orderDetail = new OrderDetail();
            orderDetail.setBook(book);
            var quantity = 0;

            for (OrderDetailDTO orderDetailDTO : entry.getValue()) {
                quantity += orderDetailDTO.getQuantity();
            }
            orderDetail.setQuantity(quantity);
            orderDetail.setAmount(quantity * book.getPrice());
            orderDetailList.add(orderDetail);
        }

        return orderDetailList;
    }
}
