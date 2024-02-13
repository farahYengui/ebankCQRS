package com.cqrs.demo.query.services;

import com.cqrs.demo.commonapi.enums.TransactionType;
import com.cqrs.demo.commonapi.events.AccountCreatedEvent;
import com.cqrs.demo.commonapi.events.AccountCreditedEvent;
import com.cqrs.demo.commonapi.events.AccountDebitedEvent;
import com.cqrs.demo.commonapi.queries.GetAccountById;
import com.cqrs.demo.commonapi.queries.GetAccountTransactionById;
import com.cqrs.demo.commonapi.queries.GetAllAccountsTransactions;
import com.cqrs.demo.query.entities.Account;
import com.cqrs.demo.commonapi.queries.GetAllAccounts;
import com.cqrs.demo.query.entities.AccountTransaction;
import com.cqrs.demo.query.repository.AccountRepository;
import com.cqrs.demo.query.repository.AccountTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@Transactional
public class AccountEventHandlerService {
    private AccountRepository accountRepository;
    private AccountTransactionRepository accountTransactionRepository;

    public AccountEventHandlerService(AccountRepository accountRepository,AccountTransactionRepository accountTransactionRepository) {
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }
    @EventHandler
    public void handle(AccountCreatedEvent event, EventMessage<AccountCreatedEvent> eventMessage) {
        log.info("**********************");
        log.info("AccountRepository received");
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getBalance());
        account.setStatus(event.getStatus());
        account.setCurrency(event.getCurrency());
        account.setCreatedAt(eventMessage.getTimestamp());
        accountRepository.save(account);
    }
    @EventHandler
    public void handle(AccountCreditedEvent event, EventMessage<AccountCreatedEvent> eventMessage) {
        log.info("*********************");
        log.info("AccountRepository received accountCreditedEvent");
        log.info("*********************");
        log.info("*********************");
        AccountTransaction accountTransaction= new AccountTransaction();
        accountTransaction.setTimestamp(eventMessage.getTimestamp());
        accountTransaction.setAmount(event.getAmount());
        accountTransaction.setType(TransactionType.CREDIT);
        Account account = accountRepository.findById(event.getId())
                .orElseThrow(() -> new NoSuchElementException("Aucun compte trouvé avec l'identifiant spécifié"));
        accountTransaction.setAccount(account);
        accountTransactionRepository.save(accountTransaction);
    }
    @EventHandler
    public void handle(AccountDebitedEvent event, EventMessage<AccountCreatedEvent> eventMessage) {
        log.info("*********************");
        log.info("AccountRepository received accountCreditedEvent");
        log.info("*********************");
        log.info("*********************");
        AccountTransaction accountTransaction= new AccountTransaction();
        accountTransaction.setTimestamp(eventMessage.getTimestamp());
        accountTransaction.setAmount(event.getAmount());
        accountTransaction.setType(TransactionType.DEBIT);
        Account account = accountRepository.findById(event.getId())
                .orElseThrow(() -> new NoSuchElementException("Aucun compte trouvé avec l'identifiant spécifié"));
        accountTransaction.setAccount(account);
        accountTransactionRepository.save(accountTransaction);
    }

    @QueryHandler
    public List<Account> on(GetAllAccounts query){
        return accountRepository.findAll();
    }
    @QueryHandler
    public Account on(GetAccountById query){
        return accountRepository.findById(query.getId()).get();
    }
    @QueryHandler
    public List<AccountTransaction> on(GetAllAccountsTransactions query){
        return accountTransactionRepository.findAll();
    }
    @QueryHandler
    public AccountTransaction on(GetAccountTransactionById query){
        return accountTransactionRepository.findById(query.getId()).get();
    }

}
