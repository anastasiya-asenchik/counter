package com.asn.counter;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
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

    public void save(CounterDTO counter) {
        counterRepository.save(modelMapper.map(counter, Counter.class));
    }

    public CounterDTO getCounter(Long id){
        return modelMapper.map(counterRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Counter not found for id " + id)), CounterDTO.class);
    }

    public CounterDTO incrementCounter(Long counterId) {
        counterRepository.incrementCounterValueById(counterId);
        return modelMapper.map(counterRepository.getById(counterId), CounterDTO.class);
    }

    public void deleteCounter(Long id){
        counterRepository.deleteById(id);
    }
}
