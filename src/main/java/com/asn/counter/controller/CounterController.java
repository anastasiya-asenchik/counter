package com.asn.counter.controller;

import com.asn.counter.service.CounterValueService;
import com.asn.counter.bo.Counter;
import com.asn.counter.repository.CounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/counters")
@RequiredArgsConstructor
public class CounterController {

    private final CounterRepository counterRepository;
    private final CounterValueService counterValueService;

    @GetMapping()
    public List<Counter> getAllCounters(){
        return counterRepository.findAll();
    }

    @PatchMapping("/{id}")
    public Counter updateCounterValue(@PathVariable Long id){
        return counterValueService.updateCounterValue(id);
    }

    @GetMapping("/{id}")
    public Counter getCounter(@PathVariable Long id){
        return counterRepository.findById(id).orElseThrow();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCounter(@RequestBody Counter counter){
        counterRepository.save(counter);
    }

    @DeleteMapping("/{id}")
    public void deleteCounter(@PathVariable Long id){
        counterRepository.deleteById(id);
    }
}
