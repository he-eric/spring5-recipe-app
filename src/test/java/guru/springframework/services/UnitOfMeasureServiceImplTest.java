package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand command;
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository repository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        command = new UnitOfMeasureToUnitOfMeasureCommand();
        service = new UnitOfMeasureServiceImpl(repository, command);
    }

    @Test
    public void listAllUoms() {
        Set<UnitOfMeasure> uoms = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom1.setId(1L);
        uom2.setId(2L);
        uoms.add(uom1);
        uoms.add(uom2);

        when(repository.findAll()).thenReturn(uoms);

        Set<UnitOfMeasureCommand> commands = service.listAllUoms();

        assertEquals(2, commands.size());
        verify(repository).findAll();
    }
}