package com.cqrs.demo.query.repository;

import com.cqrs.demo.query.entities.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,String> {
}
