package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {
        Set<Visit> visits = new HashSet<>();
        visits.add(new Visit(1L, LocalDate.now()));
        visits.add(new Visit(2L, LocalDate.now()));
        visits.add(new Visit(3L, LocalDate.now()));

        when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> returnedVisits = service.findAll();

        assertEquals(visits.size(), returnedVisits.size());
        verify(visitRepository, times(1)).findAll();

    }

    @Test
    void findById() {
        Visit visit = new Visit(1L, LocalDate.now());
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        Visit returned = service.findById(1L);

        assertEquals(visit.getId(), returned.getId());
        verify(visitRepository, times(1)).findById(1L);
    }

    @Test
    void save() {
        Visit visit = new Visit(1L, LocalDate.now());
        when(visitRepository.save(visit)).thenReturn(visit);

        Visit result = service.save(visit);
        assertThat(result).isNotNull();
        verify(visitRepository, times(1)).save(any(Visit.class));
        verify(visitRepository, never()).findById(1L);

    }

    @Test
    void delete() {
        service.delete(new Visit());
        verify(visitRepository, times(1)).delete(any(Visit.class));
        verify(visitRepository, never()).deleteById(anyLong());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository, times(1)).deleteById(anyLong());
    }
}