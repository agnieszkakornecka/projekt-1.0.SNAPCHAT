package pl.lodz.p.it.spjava.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.lodz.p.it.spjava.dao.MealRepository;
import pl.lodz.p.it.spjava.model.Meal;
import pl.lodz.p.it.spjava.model.MealType;
import pl.lodz.p.it.spjava.model.Week;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class MealServiceTest {

    @InjectMocks
    private MealService sut = new MealService();

    @Mock
    MealRepository mealRepository;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void shouldReturnMeals() {

        //given
        List<Meal> meals = new ArrayList<Meal>();
        Meal meal = mock(Meal.class);
        meals.add(meal);
        given(mealRepository.getMeals()).willReturn(meals);

        //when
        List<Meal> result = sut.getMeals();

        //then
        assertEquals(1, result.size());
        assertEquals(meals.get(0), result.get(0));
    }

    @Test
    public void shouldAddMeal() {

        //given
        Meal meal = mock(Meal.class);

        //when
        sut.addMeal(meal);

        //then
        verify(mealRepository).addMeal(meal);
    }

    @Test
    public void shouldNotAddMealIfMealIsNull() {

        //given
        Meal meal = null;

        //when
        sut.addMeal(meal);

        //then
        verifyZeroInteractions(mealRepository);
    }

    @Test
    public void shouldDeleteMeal() {

        //given
        Meal meal = mock(Meal.class);

        //when
        sut.deleteMeal(meal);

        //then
        verify(mealRepository).deleteMeal(meal);
    }

    @Test
    public void shouldNotDeleteMealIfMealIsNull() {

        //given
        Meal meal = null;

        //when
        sut.deleteMeal(meal);

        //then
        verifyZeroInteractions(mealRepository);
    }


    @Test
    public void shouldUpdateMeal() {

        //given
        Meal meal = mock(Meal.class);

        //when
        sut.updateMeal(meal);

        //then
        verify(mealRepository).updateMeal(meal);
    }


    @Test
    public void shouldNotUpdateMealIfMealIsNull() {

        //given
        Meal meal = null;

        //when
        sut.updateMeal(meal);

        //then
        verifyZeroInteractions(mealRepository);
    }

    @Test
    public void shouldReturnMealTypes() {

        //given
        List<MealType> mealTypes = new ArrayList<MealType>();
        MealType mealType = mock(MealType.class);
        mealTypes.add(mealType);
        given(mealRepository.getMealsTypes()).willReturn(mealTypes);

        //when
        List<MealType> result = sut.getMealTypes();

        //then
        assertEquals(1, result.size());
        assertEquals(mealTypes.get(0), result.get(0));
    }

    @Test
    public void shouldReturnAllWeeks() {

        //given
        List<Week> weeks = new ArrayList<Week>();
        Week week = mock(Week.class);
        weeks.add(week);
        given(mealRepository.getAllWeeks()).willReturn(weeks);

        //when
        List<Week> result = sut.getAllWeeks();

        //then
        assertEquals(1, result.size());
        assertEquals(weeks.get(0), result.get(0));
    }
}