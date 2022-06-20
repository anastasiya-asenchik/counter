package com.asn.counter;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CounterValueService {

    private final CounterRepository counterRepository;

    public synchronized Counter incrementCounter(Long counterId) {
        Counter counter = counterRepository.findById(counterId)
                .orElseThrow(() -> new EntityNotFoundException("Counter not found for id " + counterId));
        var prev = counter.getValue();
        counter.setValue(prev + 1);
        counterRepository.save(counter);
        return counter;
    }
}
