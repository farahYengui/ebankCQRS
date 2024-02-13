package com.cqrs.demo.query;

import com.cqrs.demo.commonapi.queries.GetAccountById;
import com.cqrs.demo.commonapi.queries.GetAccountTransactionById;
import com.cqrs.demo.commonapi.queries.GetAllAccounts;
import com.cqrs.demo.commonapi.queries.GetAllAccountsTransactions;
import com.cqrs.demo.query.entities.Account;
import com.cqrs.demo.query.entities.AccountTransaction;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accountTransaction")
public class AccountTransactionQueryController {
    private QueryGateway queryGateway;

    public AccountTransactionQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping("/list")
    public CompletableFuture<List<AccountTransaction>> accountTransactionList(){
        return queryGateway.query(new GetAllAccountsTransactions(), ResponseTypes.multipleInstancesOf(AccountTransaction.class));
    }
    @GetMapping("/{id}")
    public AccountTransaction getAccountTransaction(@PathVariable String id){
        return queryGateway.query(new GetAccountTransactionById(id), ResponseTypes.instanceOf(AccountTransaction.class)).join();
    }

}
