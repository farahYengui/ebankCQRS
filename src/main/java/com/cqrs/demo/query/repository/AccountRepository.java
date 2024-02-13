package com.cqrs.demo.query.repository;

import com.cqrs.demo.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
}
