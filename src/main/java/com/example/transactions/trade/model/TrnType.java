package com.example.transactions.trade.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "trn_type")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrnType {

    @Id
    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "refundable")
    private Boolean refundable;

    @Column(name = "direction")
    private String direction;

    @Column(name = "ins_time")
    private Date insTime;
}
