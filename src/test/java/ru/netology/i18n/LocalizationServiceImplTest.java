package ru.netology.i18n;

import org.junit.Test;
import ru.netology.entity.Country;

import static org.junit.Assert.assertEquals;

public class LocalizationServiceImplTest {

    private final LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    public void testLocaleForRussia() {
        String message = localizationService.locale(Country.RUSSIA);

        assertEquals("Добро пожаловать", message);
    }

    @Test
    public void testLocaleForUSA() {
        String message = localizationService.locale(Country.USA);

        assertEquals("Welcome", message);
    }

    @Test
    public void testLocaleForGermany() {
        String message = localizationService.locale(Country.GERMANY);

        assertEquals("Welcome", message);
    }

    @Test
    public void testLocaleForBrazil() {
        String message = localizationService.locale(Country.BRAZIL);

        assertEquals("Welcome", message);
    }
}