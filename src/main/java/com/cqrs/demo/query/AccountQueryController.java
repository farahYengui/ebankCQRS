package com.cqrs.demo.query;

import com.cqrs.demo.commonapi.queries.GetAccountById;
import com.cqrs.demo.query.entities.Account;
import com.cqrs.demo.commonapi.queries.GetAllAccounts;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@RestController
@RequestMapping("/query/account")
public class AccountQueryController {
    private QueryGateway queryGateway;

    public AccountQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping("/list")
    public CompletableFuture<List<Account>> accountList(){
        return queryGateway.query(new GetAllAccounts(), ResponseTypes.multipleInstancesOf(Account.class));
    }
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id){
        return queryGateway.query(new GetAccountById(id), ResponseTypes.instanceOf(Account.class)).join();
    }

}
