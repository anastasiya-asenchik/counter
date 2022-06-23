package com.asn.counter;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CounterServiceTest {

    @Mock
    private CounterRepository counterRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CounterService counterService;

    @Test
    void testGetAllCounters() {
        Counter counter = mock(Counter.class);
        List<Counter> counters = List.of(counter, counter, counter);
        CounterDTO expected = mock(CounterDTO.class);
        when(counterRepository.findAll()).thenReturn(counters);
        when(modelMapper.map(counter, CounterDTO.class)).thenReturn(expected);

        assertEquals(List.of(expected, expected, expected), counterService.getAllCounters());
    }

    @Test
    void testGetAllCounters_whenNoCountersAdded() {
        when(counterRepository.findAll()).thenReturn(List.of());

        assertEquals(List.of(), counterService.getAllCounters());
    }

    @Test
    void testGetCounter() {
        Counter counter = mock(Counter.class);
        CounterDTO expected = mock(CounterDTO.class);

        when(counterRepository.findById(any())).thenReturn(Optional.of(counter));
        when(modelMapper.map(counter, CounterDTO.class)).thenReturn(expected);

        assertEquals(expected, counterService.getCounter(3L));
    }

    @Test
    void test_whenGetNonExistentCounter_thenExceptionIsThrown() {
        assertThrows(EntityNotFoundException.class, () -> counterService.getCounter(10000L));
    }

    @Test
    void testSaveCounter() {
        CounterDTO counterDTO = mock(CounterDTO.class);
        Counter counter = mock(Counter.class);

        when(modelMapper.map(counterDTO, Counter.class)).thenReturn(counter);
        counterService.save(counterDTO);

        verify(counterRepository, times(1)).save(counter);
    }

    @Test
    void testIncrementCounter() {
        Long counterId = 4L;
        CounterDTO expected = mock(CounterDTO.class);
        Counter counter = mock(Counter.class);

        when(counterRepository.getById(counterId)).thenReturn(counter);
        when(modelMapper.map(counter, CounterDTO.class)).thenReturn(expected);

        CounterDTO actual = counterService.incrementCounter(counterId);

        verify(counterRepository, times(1)).updateCounterValue(counterId);
        assertEquals(expected, actual);
    }

    @Test
    void testDeleteCounter() {
        counterService.deleteCounter(5L);

        verify(counterRepository, times(1)).deleteById(5L);
    }
}