package com.asn.counter;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE counter SET value = value + 1 WHERE id = ?1", nativeQuery = true)
    void incrementCounterValueById(Long id);
}
