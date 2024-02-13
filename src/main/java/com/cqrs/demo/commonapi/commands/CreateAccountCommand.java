package com.cqrs.demo.commonapi.commands;

import lombok.Getter;

public class CreateAccountCommand extends BaseCommand<String>{

    @Getter private String currency;
    @Getter private double initialBalance;

    public CreateAccountCommand(String id, double initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }
}
