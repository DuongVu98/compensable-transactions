package com.cseiu.compensabletransaction.domain.repositories;

import com.cseiu.compensabletransaction.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
