package com.azu.demohaider.user.securirty.token;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface TokenRepository extends JpaRepository<Token, Long> {





    @Query("""
            select t from Token t inner join User u on t.users.id = u.id
             where u.id = :userId
            """)
    List<Token> getAllTokens(Integer userId);

    Optional<Token> findByToken(String token);

    @Query("SELECT t FROM Token t WHERE t.users.id = :id")
    Optional<Token> findTokenByUserId(@Param("id") Long id);

    @Query("SELECT t FROM Token t " +
            "WHERE t.users.email = ?1 ")
    List<Token> findAllByUserEmail(String email);








}
