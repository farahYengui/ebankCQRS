package com.cqrs.demo.commonapi.events;

import com.cqrs.demo.commonapi.enums.AccountStatus;
import lombok.Getter;

public class AccountCreditedEvent extends BaseEvent <String>{
    @Getter String currency;
    @Getter double amount;

    public AccountCreditedEvent(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
