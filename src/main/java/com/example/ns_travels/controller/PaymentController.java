package com.example.ns_travels.controller;

import com.example.ns_travels.dto.PaymentDTO;
import com.example.ns_travels.entity.User;
import com.example.ns_travels.repository.UsersRepo;
import com.example.ns_travels.service.PaymentService;
import com.example.ns_travels.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;
    private final UsersRepo usersRepo;

    public PaymentController(PaymentService paymentService, UsersRepo usersRepo) {
        this.paymentService = paymentService;
        this.usersRepo = usersRepo;
    }

    // ✅ Add a new payment
    @PostMapping("/addPayment")
    public ResponseEntity<ResponseUtil> addPayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            if (paymentDTO.getUserId() == null) {
                return ResponseEntity.badRequest().body(new ResponseUtil(400, "User ID is required", null));
            }

            Optional<User> user = usersRepo.findById(paymentDTO.getUserId());
            if (user.isEmpty()) {
                return ResponseEntity.badRequest().body(new ResponseUtil(400, "User not found", null));
            }

            paymentService.save(paymentDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseUtil(201, "Payment added successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error adding payment: " + e.getMessage(), null));
        }
    }

    // ✅ Update a payment
    @PutMapping("/updatePayment")
    public ResponseEntity<ResponseUtil> updatePayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            if (paymentDTO.getId() == null) {
                return ResponseEntity.badRequest().body(new ResponseUtil(400, "Payment ID is required", null));
            }

            paymentService.update(paymentDTO);
            return ResponseEntity.ok(new ResponseUtil(200, "Payment updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error updating payment: " + e.getMessage(), null));
        }
    }

    // ✅ Delete a payment
    @DeleteMapping("/deletePayment/{id}")
    public ResponseEntity<ResponseUtil> deletePayment(@PathVariable Long id) {
        try {
            paymentService.delete(id);
            return ResponseEntity.ok(new ResponseUtil(200, "Payment deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error deleting payment: " + e.getMessage(), null));
        }
    }

    // ✅ Get payment by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseUtil> getPaymentById(@PathVariable Long id) {
        try {
            PaymentDTO paymentDTO = paymentService.getPaymentById(id);
            return ResponseEntity.ok(new ResponseUtil(200, "Payment fetched successfully", paymentDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error fetching payment: " + e.getMessage(), null));
        }
    }

    // ✅ Get all payments
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUtil> getAllPayments() {
        try {
            List<PaymentDTO> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(new ResponseUtil(200, "Payments fetched successfully", payments));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error fetching payments: " + e.getMessage(), null));
        }
    }

    // ✅ Get payments by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseUtil> getPaymentsByUserId(@PathVariable Long userId) {
        try {
            List<PaymentDTO> payments = paymentService.getPaymentsByUserId(userId);
            return ResponseEntity.ok(new ResponseUtil(200, "Payments for user fetched successfully", payments));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseUtil(500, "Error fetching user's payments: " + e.getMessage(), null));
        }
    }
}
