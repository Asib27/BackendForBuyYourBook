package com.asib27.authentication.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "select tx_id from transaction where added_time = (select max(added_time) from transaction)",nativeQuery = true )
    Long getLatestTransactionId();
}
