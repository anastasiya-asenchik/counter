package com.asn.counter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counters")
@RequiredArgsConstructor
public class CounterController {

    private final CounterService counterService;

    @GetMapping
    public List<CounterDTO> getAllCounters(){
        return counterService.getAllCounters();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addCounter(@RequestBody CounterDTO counter){
        counterService.save(counter);
    }

    @GetMapping("/{id}")
    public CounterDTO getCounter(@PathVariable Long id){
        return counterService.getCounter(id);
    }

    @PostMapping("/{id}/increment")
    public CounterDTO incrementCounter(@PathVariable Long id){
        return counterService.incrementCounter(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCounter(@PathVariable Long id){
        counterService.deleteCounter(id);
    }
}
