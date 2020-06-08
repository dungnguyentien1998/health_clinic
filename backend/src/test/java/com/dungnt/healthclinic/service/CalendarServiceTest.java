//package com.dungnt.healthclinic.service;
//
//import com.dungnt.healthclinic.dto.CalendarRequest;
//import com.dungnt.healthclinic.model.Calendar;
//import com.dungnt.healthclinic.model.ClinicService;
//import com.dungnt.healthclinic.repository.CalendarRepository;
//import com.dungnt.healthclinic.repository.ClinicServiceRepository;
//import com.dungnt.healthclinic.service.impl.CalendarServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class CalendarServiceTest {
//    @Mock
//    CalendarRepository calendarRepository;
//
//    @Mock
//    ClinicServiceRepository clinicServiceRepository;
//
//    @InjectMocks
//    CalendarService calendarService;
//
//    @Before
//    public void setUp() {
//        calendarRepository = mock(CalendarRepository.class);
//        clinicServiceRepository = mock(ClinicServiceRepository.class);
//        calendarService = new CalendarServiceImpl(calendarRepository, clinicServiceRepository);
//    }
//
//    @Test
//    public void testFindByIdWhenIdIsNull() {
//        Long id = null;
//        try {
//            calendarService.findById(id);
//        } catch (Exception e) {
//            assertEquals("", e.getMessage());
//        }
//    }
//
//    @Test
//    public void testFindByIdWhenTrue() throws Exception {
//        Long id = 1L;
//        Calendar calendar = new Calendar();
//        Optional<Calendar> optionalCalendar = Optional.of(calendar);
//        when(calendarRepository.findById(id)).thenReturn(optionalCalendar);
//        Optional<Calendar> calendarTest = calendarService.findById(id);
//        assertNotNull(calendarTest);
//    }
//
//    @Test
//    public void testSaveWhenServiceIsNull() {
//        Calendar calendar = null;
//        try {
//            calendarService.save(calendar);
//        } catch (Exception e) {
//            assertEquals("", e.getMessage());
//        }
//    }
//
//    @Test
//    public void testSaveWhenServiceNameIsNull() {
//        Calendar calendar = new Calendar();
//        try {
//            calendarService.save(calendar);
//        } catch (Exception e) {
//            assertEquals("", e.getMessage());
//        }
//    }
//
//    @Test
//    public void testFindSuitableCalendarWhenInputIsNull() {
//        CalendarRequest calendarRequest = null;
//        Integer state = null;
//        try {
//            calendarService.findSuitableCalendar(calendarRequest, state);
//        } catch (Exception e) {
//            assertEquals("", e.getMessage());
//        }
//    }
//
//    @Test
//    public void testFindSuitableCalendarWhenTrue() throws Exception {
//        CalendarRequest calendarRequest = new CalendarRequest();
//        calendarRequest.setDate("2019-01-01");
//        calendarRequest.setTime("09:00:00");
//        calendarRequest.setServiceId("1");
//        Integer state = 0;
//        LocalDate date = LocalDate.parse(calendarRequest.getDate());
//        LocalTime timeStart = LocalTime.parse(calendarRequest.getTime());
//        Long serviceId = Long.parseLong(calendarRequest.getServiceId());
//        List<Calendar> calendars = new ArrayList<>();
//        when(calendarRepository.findSuitableCalendars(date, timeStart, serviceId, state)).thenReturn(calendars);
//        List<Calendar> testCalendars = calendarService.findSuitableCalendar(calendarRequest, state);
//        assertNotNull(testCalendars);
//    }
//
//    @Test
//    public void testRecommendCalendarWhenInputIsNull() {
//        CalendarRequest calendarRequest = null;
//        Integer state = null;
//        try {
//            calendarService.recommendCalendars(calendarRequest, state);
//        } catch (Exception e) {
//            assertEquals("", e.getMessage());
//        }
//    }
//
//    @Test
//    public void testRecommendCalendarWhenTrue() throws Exception {
//        CalendarRequest calendarRequest = new CalendarRequest();
//        calendarRequest.setDate("2019-01-01");
//        calendarRequest.setTime("09:00:00");
//        calendarRequest.setServiceId("1");
//        Integer state = 0;
//        LocalDate date = LocalDate.parse(calendarRequest.getDate());
//        LocalTime timeStart = LocalTime.parse(calendarRequest.getTime());
//        Long serviceId = Long.parseLong(calendarRequest.getServiceId());
//        List<Calendar> calendars = new ArrayList<>();
//        when(calendarRepository.findRecommendedCalendars(date, timeStart, serviceId, state)).thenReturn(calendars);
//        List<Calendar> testCalendars = calendarService.recommendCalendars(calendarRequest, state);
//        assertNotNull(testCalendars);
//    }
//
//
//
//
//
//
//}