package com.sample.service;

import java.util.List;

import com.sample.web.view.Payment;

public interface PaymentService {
    void addPayment(Payment payment);
    void updatePayment(Payment payment);
    Payment getPaymentDetail(int reserveId);
    List<Payment> getAllPayments();
    List<Payment> getUsersPayments(String userId);
    
    /**
     * unique 키 reserveId로 Payment 객체를 1개 반환한다.
     * @param reserveId
     * @return Payment 객체
     */
    Payment getPaymentByReserveId(int reserveId);
}