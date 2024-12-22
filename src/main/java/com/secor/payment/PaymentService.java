package com.secor.payment;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository productRepository;

    public PaymentService(PaymentRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Payment> getAllProducts() {
        return (List<Payment>) productRepository.findAll();
    }

    public Payment getProductById(Long id) throws ResourceNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Payment createProduct(Payment product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
