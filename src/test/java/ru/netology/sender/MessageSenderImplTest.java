package ru.netology.sender;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MessageSenderImplTest {

    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSender messageSender;

    @Before
    public void setUp() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void testSendMessageInRussianForRussianIp() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        when(geoService.byIp("172.123.12.19")).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String result = messageSender.send(headers);

        assertEquals("Добро пожаловать", result);
    }

    @Test
    public void testSendMessageInEnglishForAmericanIp() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        String result = messageSender.send(headers);

        assertEquals("Welcome", result);
    }

    @Test
    public void testSendMessageInRussianForAnotherRussianIp() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "203.0.113.0");  // Пример другого российского IP

        when(geoService.byIp("203.0.113.0")).thenReturn(new Location("Saint Petersburg", Country.RUSSIA, "Nevsky", 1));
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String result = messageSender.send(headers);

        assertEquals("Добро пожаловать", result);
    }

    @Test
    public void testSendMessageInRussianForNonRussianIp() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "192.0.2.1");

        when(geoService.byIp("192.0.2.1")).thenReturn(new Location("Unknown City", Country.RUSSIA, "Unknown Street", 0));
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String result = messageSender.send(headers);

        assertEquals("Добро пожаловать", result);
    }

    @Test
    public void testSendMessageInEnglishForNonAmericanIp() {
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "203.0.113.1");

        when(geoService.byIp("203.0.113.1")).thenReturn(new Location("Unknown City", Country.USA, "Unknown Street", 0));
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        String result = messageSender.send(headers);

        assertEquals("Welcome", result);
    }
}