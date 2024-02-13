package com.cqrs.demo.commonapi.events;

import com.cqrs.demo.commonapi.enums.AccountStatus;
import lombok.Getter;

public class AccountDebitedEvent extends BaseEvent <String>{
    @Getter String currency;
    @Getter double amount;

    public AccountDebitedEvent(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
