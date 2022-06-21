package com.asn.counter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CounterService {


    private final CounterRepository counterRepository;
    private final ModelMapper modelMapper;

    public List<CounterDTO> getAllCounters(){
        return counterRepository
                .findAll()
                .stream()
                .map(counter -> modelMapper.map(counter, CounterDTO.class))
                .collect(Collectors.toList());
    }

    public Counter incrementCounter(Long counterId) {
        counterRepository.updateCounterValue(counterId);
        return counterRepository.getById(counterId);
    }
}
