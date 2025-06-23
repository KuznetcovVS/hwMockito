package ru.netology.geo;

import org.junit.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.Assert.assertEquals;

public class GeoServiceImplTest {

    private final GeoService geoService = new GeoServiceImpl();

    @Test
    public void testByIpForMoscow() {
        Location location = geoService.byIp("172.0.32.11");

        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    public void testByIpForNewYork() {
        Location location = geoService.byIp("96.44.183.149");

        assertEquals("New York", location.getCity());
        assertEquals(Country.USA, location.getCountry());
    }

}