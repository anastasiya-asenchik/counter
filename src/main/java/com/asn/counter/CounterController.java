package com.asn.counter;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    private final CounterRepository counterRepository;
    private final CounterService counterService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<CounterDTO> getAllCounters(){
        return counterService.getAllCounters();
    }

    @PostMapping
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
        return modelMapper.map(counterService.incrementCounter(id), CounterDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCounter(@PathVariable Long id){
        counterRepository.deleteById(id);
    }
}
