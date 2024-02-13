package com.cqrs.demo.commonapi.events;

import com.cqrs.demo.commonapi.enums.AccountStatus;
import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent <String>{
    @Getter String currency;
    @Getter double balance;
    @Getter AccountStatus status;

    public AccountCreatedEvent(String id, String currency, double balance, AccountStatus status) {
        super(id);
        this.currency = currency;
        this.balance = balance;
        this.status = status;
    }
}
