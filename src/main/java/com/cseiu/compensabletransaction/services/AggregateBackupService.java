package com.cseiu.compensabletransaction.services;

import com.cseiu.compensabletransaction.models.Account;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AggregateBackupService {
    private final Map<String, Account> backupStorage = new HashMap<>();

    public Account getAccount(String accountId) {
        return backupStorage.get(accountId);
    }

    public void store(Account account){
        backupStorage.put(account.getId(), account);
    }

    public void remove(Account account) {
        backupStorage.remove(account.getId());
    }
}
