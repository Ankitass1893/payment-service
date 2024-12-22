package com.secor.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderLookupService orderLookupService;

    public PaymentController(PaymentService paymentService, OrderLookupService orderLookupService) {
        this.paymentService = paymentService;
        this.orderLookupService = orderLookupService;
    }

    @GetMapping("/all")
    public List<Payment> getAllProducts() {
        return paymentService.getAllProducts();
    }

    @GetMapping("/getByID/{paymentid}")
    public Payment getProductById(@PathVariable Long paymentid) throws ResourceNotFoundException {
        return paymentService.getProductById(paymentid);
    }

    @PostMapping("/addNew")
    public ResponseEntity<Payment> createProduct(@RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.createProduct(payment));
    }

    @DeleteMapping("/getByID/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        paymentService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/process")
        public ResponseEntity<String> processPayment(@RequestParam Long orderId) {
            OrderProcess order = orderLookupService.fetchOrderDetails(orderId);

            if (order != null) {
                // Proceed with payment processing
                return ResponseEntity.ok("Payment processed for Order ID: " + orderId +order);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Order not found for ID: " + orderId);
            }
        }
    }


