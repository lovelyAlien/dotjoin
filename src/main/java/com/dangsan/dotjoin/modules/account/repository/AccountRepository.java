package com.dangsan.dotjoin.modules.account.repository;

import com.dangsan.dotjoin.modules.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<Account> findByEmail(String email);

    Account findByNickname(String nickname);

    Optional<Account> findByProviderId(String providerId);




}
