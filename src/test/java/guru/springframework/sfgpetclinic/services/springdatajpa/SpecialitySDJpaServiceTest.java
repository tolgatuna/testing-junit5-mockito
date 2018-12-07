package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @Test
    void findByIdTest() {
        Speciality speciality = new Speciality(1L, "Description");
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
        Speciality founded = specialitySDJpaService.findById(1L);
        assertNotNull(founded);
        assertEquals(founded.getId(), speciality.getId());
        verify(specialtyRepository, times(1)).findById(1L);
    }

    @Test
    void deleteById() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(2L);

        verify(specialtyRepository, times(2)).deleteById(anyLong());
    }

    @Test
    void deleteByIdAtLeastOnce() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(2L);

        verify(specialtyRepository, atLeastOnce()).deleteById(anyLong());
    }

    @Test
    void deleteByIdAtMostFiveTimes() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(2L);

        verify(specialtyRepository, atMost(5)).deleteById(anyLong());
    }

    @Test
    void findByIdNever() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(2L);

        verify(specialtyRepository, never()).findById(anyLong());
    }

    @Test
    void deleteByObject() {
        Speciality speciality = new Speciality();
        specialitySDJpaService.delete(speciality);
        verify(specialtyRepository, times(1)).delete(any(Speciality.class));
    }

}