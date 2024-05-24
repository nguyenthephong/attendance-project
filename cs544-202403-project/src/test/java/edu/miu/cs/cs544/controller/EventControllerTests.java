package edu.miu.cs.cs544.controller;

import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.service.SessionService;
import edu.miu.cs.cs544.service.contract.SessionPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class EventControllerTests {

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }

    @Test
    void createSessionByEventId() {
//        long eventId = 1;
//        Session session = new Session(); // Provide necessary session object
//        when(sessionService.createSessionByEventId(session, eventId)).thenReturn(ResponseEntity.ok().build());
//
//        ResponseEntity<?> responseEntity = eventController.createSessionByEventId(eventId, session);
//
//        assertNotNull(responseEntity);
        // Add more assertions as needed
    }

    @Test
    void findSessionsByEventId() {
//        long eventId = 1; // Provide necessary eventId
//        when(sessionService.findSessionsByEventId(eventId)).thenReturn(ResponseEntity.ok().build());
//
//        ResponseEntity<?> responseEntity = eventController.findSessionsByEventId(eventId);
//
//        assertNotNull(responseEntity);
        // Add more assertions as needed
    }

    @Test
    void updateSession() {
//        long eventId = 1; // Provide necessary eventId
//        long sessionId = 1; // Provide necessary sessionId
//        SessionPayload sessionPayload = new SessionPayload(); // Provide necessary SessionPayload object
//        when(sessionService.updateSessionByEventIdAndSessionId(eventId, sessionId, sessionPayload)).thenReturn(ResponseEntity.ok().build());
//
//        ResponseEntity<?> responseEntity = eventController.UpdateSession(eventId, sessionId, sessionPayload);
//
//        assertNotNull(responseEntity);
        // Add more assertions as needed
    }

    @Test
    void deleteSessionById() {
//        long eventId = 1; // Provide necessary eventId
//        long sessionId = 1; // Provide necessary sessionId
//        when(sessionService.deleteSessionByEventIdAndSessionId(eventId, sessionId)).thenReturn(ResponseEntity.noContent().build());
//
//        ResponseEntity<?> responseEntity = eventController.deleteSessionById(eventId, sessionId);
//
//        assertNotNull(responseEntity);
        // Add more assertions as needed
    }
}
