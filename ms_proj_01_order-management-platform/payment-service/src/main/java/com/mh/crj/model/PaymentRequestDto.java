package com.mh.crj.model;

import com.mh.crj.entity.PaymentMethod;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    @NotNull(message = "Order id is required")
    private Integer orderId;

    @NotNull(message = "User id is required")
    private Integer userId;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
    // UPI, CARD, NET_BANKING, CASH_ON_DELIVERY

}
