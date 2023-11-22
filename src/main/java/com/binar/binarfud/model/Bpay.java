package com.binar.binarfud.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tbl_rekening")
@Data
public class Bpay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nopay;
    private String name;
    private double saldo;
}
