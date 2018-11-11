package team5.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import team5.models.Vacancy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class VacancyControllerTest {

    @Test
    public void getFilteredEntitiesByPage() {
        List<Vacancy> vacancyList = new ArrayList<>();
        Vacancy vacancy = new Vacancy();
        vacancy.setPosition("Java");
        vacancy = new Vacancy();
        vacancyList.add(vacancy);

        LinkedList mockedList = mock(LinkedList.class);
        when(mockedList.get(anyInt())).thenReturn("element");
    }

    @Test
    public void getSortedEntitiesByPage() {

    }

    @Test
    public void getSortedFilteredEntitiesByPage() {

    }

    @Test
    public void saveNewEntity() {

    }

    @Test
    public void updateEntity() {

    }


}
