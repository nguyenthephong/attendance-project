package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.dto.AttendanceListDTO;
import edu.miu.cs.cs544.dto.ErrorResponseDTO;
import edu.miu.cs.cs544.service.AttendanceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(AttendanceController.class)
public class AttendanceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AttendanceService attendanceService;

    @Test
    public void whenAttendanceByMemberByEventAndMemberNotFound_giveErrorResponse() throws Exception {
        var entity =  new ResponseEntity(new ErrorResponseDTO(404, "Member not found "), HttpStatus.NOT_FOUND);
        Mockito.when(attendanceService.attendanceByMemberByEvent(1l,1l))
                .thenReturn(entity);
        mockMvc.perform(
                MockMvcRequestBuilders.get(
                                "/members/1/events/1/attendance")
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "123"))
        ).andExpect(
                MockMvcResultMatchers.status().is(404)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value("Member not found ")
        );
    }

    @Test
    public void whenAttendanceByMemberByEventAndEventNotFound_giveErrorResponse() throws Exception {
        var entity =  new ResponseEntity(new ErrorResponseDTO(404, "Event not found "), HttpStatus.NOT_FOUND);
        Mockito.when(attendanceService.attendanceByMemberByEvent(1l,1l))
                .thenReturn(entity);
        mockMvc.perform(
                MockMvcRequestBuilders.get(
                                "/members/1/events/1/attendance")
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "123"))
        ).andExpect(
                MockMvcResultMatchers.status().is(404)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value("Event not found ")
        );
    }

    @Test
    public void whenAttendanceByMemberByEventAndMemberNotRegistered_giveErrorResponse() throws Exception {
        var entity =  new ResponseEntity(new ErrorResponseDTO(404, "Member is not registered for event"), HttpStatus.NOT_FOUND);
        Mockito.when(attendanceService.attendanceByMemberByEvent(1l,1l))
                .thenReturn(entity);
        mockMvc.perform(
                MockMvcRequestBuilders.get(
                                "/members/1/events/1/attendance")
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "123"))
        ).andExpect(
                MockMvcResultMatchers.status().is(404)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value("Member is not registered for event")
        );
    }

    @Test
    public void testAttendanceByMemberByEvent() throws Exception {
        var dateNow = LocalDate.now();
        List<AttendanceListDTO> attendanceListDTOS = List.of(
                new AttendanceListDTO("Kerim", dateNow,true)
        );
        var entity = new ResponseEntity(attendanceListDTOS, HttpStatus.OK);

        Mockito.when(attendanceService.attendanceByMemberByEvent(1l,1l))
                .thenReturn(entity);
        mockMvc.perform(
                MockMvcRequestBuilders.get(
                                "/members/1/events/1/attendance")
                        .with(csrf())
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user", "123"))
        ).andExpect(
                MockMvcResultMatchers.status().is(200)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.[0].name").value("Kerim")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.[0].date").value(dateNow.toString())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.[0].attended").value(true)
        );
    }
}
