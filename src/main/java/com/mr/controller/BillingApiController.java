package com.mr.controller;

import com.mr.model.Billing;
import com.mr.service.BillingService;
import com.mr.util.CustomErrorType;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BillingApiController {

    private static final Logger logger = LoggerFactory.getLogger(BillingApiController.class);

    @Autowired
    private BillingService billingService;

    @GetMapping("/billings")
    public ResponseEntity<List<Billing>> listAllBillings() {
        List<Billing> billings = billingService.findAllBillings();
        if (billings.isEmpty()) {
            billings = new ArrayList<>();
        }
        return new ResponseEntity<>(billings, HttpStatus.OK);
    }

    @GetMapping("/billings/{id}")
    public ResponseEntity<?> getBilling(@PathVariable Long id) {
        logger.info("Fetching Billing with id {}", id);
        Billing billing = billingService.findById(id);
        if (billing == null) {
            logger.error("Billing with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType(String.format("Billing with id %d not found", id)), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(billing, HttpStatus.OK);
    }

    @GetMapping("/billings/user/{userId}")
    public ResponseEntity<List<Billing>> listBillingByUserId(@PathVariable Long userId) {
        logger.info("Fetching Billing with user id {}", userId);
        List<Billing> billings = billingService.findBillingByUserId(userId);
        if (billings == null || billings.isEmpty()) {
            billings = new ArrayList<>();
        }
        return new ResponseEntity<>(billings, HttpStatus.OK);
    }

    @GetMapping("/billings/user/{userId}/amount/{expression}/{amount}")
    public ResponseEntity<List<Billing>> listBillingByUserIdAndAmount(
            @PathVariable Long userId, @ApiParam(allowableValues = "gt,lt,equal", required = true) @PathVariable String expression, @PathVariable Double amount) {
        logger.info("Fetching Billing with user id {} with amount {} {}", userId, expression, amount);
        List<Billing> billings  = billingService.findByUserIdAndAmount(userId, expression, amount);
        if (billings == null || billings.isEmpty()) {
            billings = new ArrayList<>();
        }
        return new ResponseEntity<>(billings, HttpStatus.OK);
    }
}
