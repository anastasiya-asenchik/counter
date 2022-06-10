package com.asn.counter.controller;

import com.asn.counter.dto.CounterDTO;
import com.asn.counter.service.CounterValueService;
import com.asn.counter.bo.Counter;
import com.asn.counter.repository.CounterRepository;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/counters")
@RequiredArgsConstructor
public class CounterController {

    private final CounterRepository counterRepository;
    private final CounterValueService counterValueService;
    private final ModelMapper modelMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CounterDTO> getAllCounters(){
        return counterRepository
                .findAll()
                .stream()
                .map(counter -> modelMapper.map(counter, CounterDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addCounter(@RequestBody CounterDTO counter){
        counterRepository.save(modelMapper.map(counter, Counter.class));
    }

    @GetMapping("/{id}")
    public CounterDTO getCounter(@PathVariable Long id){
        return modelMapper.map(counterRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Counter not found for id " + id)), CounterDTO.class);
    }

    @PostMapping("/{id}/increment")
    public CounterDTO incrementCounter(@PathVariable Long id){
        return modelMapper.map(counterValueService.incrementCounter(id), CounterDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCounter(@PathVariable Long id){
        counterRepository.deleteById(id);
    }
}
