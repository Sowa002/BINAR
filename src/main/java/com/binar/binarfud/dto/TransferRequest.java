package com.binar.binarfud.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private String bpay1;
    private String bpay2;
    private double amount;
}
