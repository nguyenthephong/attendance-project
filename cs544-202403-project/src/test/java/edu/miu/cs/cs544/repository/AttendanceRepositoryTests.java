package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AttendanceRepositoryTests {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    AttendanceRepository attendanceRepository;
    @Before
    public void setUp(){
        List<Account> accounts = new ArrayList<>();
        Account account = new Account("Davaasuren","Davaasuren class",AccountType.ATTENDANCE_POINTS);
        accounts.add(account);
        testEntityManager.persist(account);
        testEntityManager.flush();
        Member member = new Member("Davaasuren","Orgodol","dorgodol@miue.edu","xyz",null, accounts, null);
        testEntityManager.persist(member);
        testEntityManager.flush();
        Event event = new Event("CS544","EA",AccountType.ATTENDANCE_POINTS, LocalDateTime.now(),LocalDateTime.of(2024,03,31,12,00));
        Session session = new Session(LocalTime.of(12,30,00), LocalTime.of(12,30,00), LocalDate.now());
        event.addSchedule(session);
        event.addParticipant(member);
        testEntityManager.persist(event);
        testEntityManager.flush();
        Scanner scanner = new Scanner("97", null,AccountType.ATTENDANCE_POINTS,event);
        testEntityManager.persist(scanner);
        testEntityManager.flush();
        Attendance attendance = new Attendance(member
                ,scanner
                , LocalDateTime.now());
        testEntityManager.persist(attendance);
        testEntityManager.flush();
    }
    @Test
    public void findAllRecordsByScannerCode_passed() throws Exception {
        List<Attendance> found = attendanceRepository.findAllRecordsByScannerCode("97");
        assertThat(found).isNotEmpty();
    }
    @Test
    public void findAllByEventId_passed() throws Exception {
        List<Attendance> found = attendanceRepository.findAllByEventId("1");
        assertThat(found).isNotEmpty();
    }
    @Test
    public void findAllByAccountId_passed() throws Exception {
        List<Attendance> found = attendanceRepository.findAllByAccountId(1L,"2024-03-01","2024-03-30");
        assertThat(found).isNotEmpty();
    }
    @Test
    public void findAllByMemberIdAndEventId_passed() throws Exception {
        List<Attendance> found = attendanceRepository.findAllByMemberIdAndEventId(1L,1L);
        assertThat(found).isNotEmpty();
    }
    @Test
    public void findAllByMemberId_passed() throws Exception {
        List<Attendance> found = attendanceRepository.findAllByMemberId(1L);
        assertThat(found).isNotEmpty();
    }
}
