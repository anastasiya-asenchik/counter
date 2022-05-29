package com.asn.counter.service;

import com.asn.counter.bo.Counter;
import com.asn.counter.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CounterValueService {

    private final CounterRepository counterRepository;

    public Counter updateCounterValue(Long counterId) {
        Counter counter = counterRepository.findById(counterId).orElseThrow();
        var prev = counter.getValue();
        counter.setValue(prev + 1);
        counterRepository.save(counter);
        return counter;
    }
}
