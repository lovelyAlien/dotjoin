package com.dangsan.dotjoin.infra.jwt;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RefreshableTwoUserIntegrationTest extends RefreshableIntegrationTest{

    @Autowired
    protected AccountService accountService;


    @Autowired
    protected AccountRepository accountRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    protected UserTestHelper userTestHelper;

    protected Account USER1;
    protected Account USER2;

    protected void prepareTwoUsers(){
        accountService.clearAllAccount();

        this.userTestHelper=new UserTestHelper(accountService, accountRepository, passwordEncoder);

        this.USER1=this.userTestHelper.createUser("user1", "USER");
        this.USER2=this.userTestHelper.createUser("user2", "USER");


    }


}
