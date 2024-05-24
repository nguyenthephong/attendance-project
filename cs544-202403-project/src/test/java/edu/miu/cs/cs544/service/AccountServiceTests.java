package edu.miu.cs.cs544.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.repository.AttendanceRepository;
import edu.miu.cs.cs544.service.AccountService;
import edu.miu.cs.cs544.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTests {
    @MockBean
    AccountRepository accountRepository;
    @MockBean
    AttendanceRepository attendanceRepository;
    AccountService accountService;
    List<String> emails = new ArrayList<>(List.of("thephong.nguyen@miu.edu"));
    @MockBean
    Attendance attendance;
    @MockBean
    List<Attendance> attendances;



    @Before
    public void setup() {
        accountService = new AccountServiceImpl(accountRepository, attendanceRepository);
    }

    @Test
    public void findAccountsByBalanceCondition_passed() {
        Mockito.when(accountRepository.findAccountsByBalanceCondition()).thenReturn(emails);
        accountService.findAccountsByBalanceCondition();
        verify(accountRepository, times(1)).findAccountsByBalanceCondition();
    }

    @Test
    public void getAttendanceByAccountIdAndStartTimeAndEndTime_passed() throws Exception {
        Event event = new Event("CS544", "EA", AccountType.ATTENDANCE_POINTS,
                LocalDateTime.of(2024, 3, 17, 1, 0, 0), LocalDateTime.of(2024, 3, 22, 1, 0, 0));
        Session session1 = new Session(LocalTime.now(), LocalTime.now(), LocalDate.of(2024, 3, 18));
        Session session2 = new Session(LocalTime.now(), LocalTime.now(), LocalDate.of(2024, 3, 19));
        Session session3 = new Session(LocalTime.now(), LocalTime.now(), LocalDate.of(2024, 3, 20));
        Session session4 = new Session(LocalTime.now(), LocalTime.now(), LocalDate.of(2024, 3, 21));
        Session session5 = new Session(LocalTime.now(), LocalTime.now(), LocalDate.of(2024, 3, 22));
        event.addSchedule(session1);
        event.addSchedule(session2);
        event.addSchedule(session3);
        event.addSchedule(session4);
        event.addSchedule(session5);
        Scanner scanner = new Scanner("01",null, AccountType.ATTENDANCE_POINTS,event);

        attendances.add(attendance);
        Mockito.when(attendance.getScanner()).thenReturn(scanner);
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account("Phong", "Student", AccountType.DINING_POINTS,15000.00, 15000.00)));
        Mockito.when(attendanceRepository.findAllByAccountId(1L, "2024-01-01", "2024-02-15")).thenReturn(attendances);

        accountService.getAttendanceByAccountIdAndStartTimeAndEndTime(1L, "2024-01-01", "2024-02-15");
        verify(attendanceRepository, times(1)).findAllByAccountId(1L, "2024-01-01", "2024-02-15");
    }
}
