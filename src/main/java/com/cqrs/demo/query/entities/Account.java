package com.cqrs.demo.query.entities;

import com.cqrs.demo.commonapi.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    @Id
    private String id;
    private Instant createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<AccountTransaction> transactions;

}
