package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.dto.ErrorResponseDTO;
import edu.miu.cs.cs544.repository.EventRepository;
import edu.miu.cs.cs544.repository.SessionRepository;
import edu.miu.cs.cs544.service.SessionService;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class SessionServiceImplTest {
    @Mock
    private EventRepository eventRepository;

    @Mock
    private SessionRepository sessionRepository;

    private SessionService sessionService;

    @BeforeEach
    public void setUp(){
        sessionService = new SessionServiceImpl(eventRepository,sessionRepository);
    }

    @Test
    void createSessionByEventId() {
        long eventId = 1;
        Session session = new Session(); // Provide necessary session object
        Event event = new Event(); // Provide necessary event object
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        when(sessionRepository.save(session)).thenReturn(session);

        ResponseEntity<?> responseEntity = sessionService.createSessionByEventId(session, eventId);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        // Add more assertions as needed
    }

    @Test
    void findSessionsByEventId() {
        long eventId = 1; // Provide necessary eventId
        Event event = new Event(); // Provide necessary event object
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        ResponseEntity<?> responseEntity = sessionService.findSessionsByEventId(eventId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Add more assertions as needed
    }

}


