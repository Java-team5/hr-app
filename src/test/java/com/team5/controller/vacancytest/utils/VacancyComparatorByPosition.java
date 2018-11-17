package com.team5.controller.vacancytest.utils;


import com.team5.models.Vacancy;

import java.util.Comparator;

public class VacancyComparatorByPosition implements Comparator<Vacancy> {
    public int compare (Vacancy vacancy1, Vacancy vacancy2) {
        return vacancy1.getPosition().compareTo(vacancy2.getPosition());
    }
}
