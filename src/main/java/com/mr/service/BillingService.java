package com.mr.service;

import com.mr.model.Billing;
import com.mr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BillingService {
    private static final AtomicLong counter = new AtomicLong();
    private static List<Billing> billings;

    @Autowired
    private UserService userService;

    public BillingService() {
        billings = populateDummyBillings();
    }

    private static List<Billing> populateDummyBillings() {
        return Stream.of(
                new Billing(counter.incrementAndGet(), 10000D, "Internet", new User(1L)),
                new Billing(counter.incrementAndGet(), 120000D, "TV Cable", new User(2L)),
                new Billing(counter.incrementAndGet(), 150000D, "Internet", new User(3L)),
                new Billing(counter.incrementAndGet(), 300000D, "Internet", new User(4L)),
                new Billing(counter.incrementAndGet(), 400000D, "Internet", new User(2L)),
                new Billing(counter.incrementAndGet(), 50000D, "TV Cable", new User(3L)),
                new Billing(counter.incrementAndGet(), 20000D, "TV Cable", new User(1L))
        ).collect(Collectors.toList());
    }

    public List<Billing> findAllBillings() {
        billings.forEach(billing -> {
            billing.setUser(userService.findById(billing.getUser().getId()));
        });
        return billings;
    }

    public Billing findById(Long id) {
        Billing billing = billings.stream().filter(b -> b.getId() == id).findAny().orElse(null);
        if (billing == null) {
            return null;
        } else {
            billing.setUser(userService.findById(billing.getUser().getId()));
            return billing;
        }
    }

    public List<Billing> findBillingByUserId(Long userId) {
        List<Billing> filteredList = new ArrayList<>();
        billings.forEach(billing -> {
            if (billing.getUser().getId() == userId) {
                filteredList.add(billing);
            }
        });

        if (!filteredList.isEmpty()) {
            filteredList.forEach(x -> {
                x.setUser(userService.findById(x.getUser().getId()));
            });
        }
        return filteredList;
    }

    public List<Billing> findByUserIdAndAmount(Long userId, String expression, Double amount) {
        List<Billing> filteredList = new ArrayList<>();
        billings.forEach(billing -> {
            if (Objects.equals(expression, "gt")) {
                if (billing.getUser().getId() == userId && billing.getAmount() > amount) {
                    filteredList.add(billing);
                }
            } else if (Objects.equals(expression, "lt")) {
                if (billing.getUser().getId() == userId && billing.getAmount() < amount) {
                    filteredList.add(billing);
                }
            } else {
                if (billing.getUser().getId() == userId && billing.getAmount().equals(amount)) {
                    filteredList.add(billing);
                }
            }
        });

        if (!filteredList.isEmpty()) {
            filteredList.forEach(x -> {
                x.setUser(userService.findById(x.getUser().getId()));
            });
        }
        return filteredList;
    }

}
