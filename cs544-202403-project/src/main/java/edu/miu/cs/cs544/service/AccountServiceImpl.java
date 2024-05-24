package edu.miu.cs.cs544.service;

import java.time.LocalDate;
import java.util.*;

import edu.miu.common.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.domain.Event;
import edu.miu.cs.cs544.domain.Session;
import edu.miu.cs.cs544.dto.AttendanceListDTO;
import edu.miu.cs.cs544.dto.ErrorResponseDTO;
import edu.miu.cs.cs544.service.exception.MemberNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import edu.miu.common.service.BaseReadWriteServiceImpl;
import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.Attendance;
import edu.miu.cs.cs544.repository.AccountRepository;
import edu.miu.cs.cs544.repository.AttendanceRepository;
import edu.miu.cs.cs544.service.contract.AccountPayload;
import edu.miu.cs.cs544.service.contract.AttendancePayload;

@Service
@AllArgsConstructor
public class AccountServiceImpl extends BaseReadWriteServiceImpl<AccountPayload, Account, Long> implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AttendanceRepository attendanceRepository;

	@Override
	public List<String> findAccountsByBalanceCondition() {
		return accountRepository.findAccountsByBalanceCondition();
	}

	public List<AttendanceListDTO> getAttendanceByAccountIdAndStartTimeAndEndTime(Long accountId, String startTime, String endTime) throws Exception {
		Optional<Account> acc = accountRepository.findById(accountId);
		String memberName;
		if (acc.isPresent()) {
			memberName = acc.get().getName();
		} else {
			throw new Exception("Could not find the account with id " + accountId);
		}
		List<Attendance> attendanceList = attendanceRepository.findAllByAccountId(accountId, startTime, endTime);

		// Get list of events
		Set<Event> events = new HashSet<Event>();
		attendanceList.forEach(a -> {
			events.add(a.getScanner().getEvent());
		});

		List<AttendanceListDTO> attendanceListDTOS = new ArrayList<>();
		for (Event event : events) {
			Collection<Session> sessions = event.getSchedule();
			for (Session s : sessions) {
				LocalDate sessionDate = s.getDate();
				boolean attended = false;

				for (Attendance attendance : attendanceList) {
					LocalDate attendanceDate = attendance.getDateTime().toLocalDate();
					if (attendanceDate.isEqual(sessionDate)) {
						attended = true;
						break;
					}
				}
				attendanceListDTOS.add(new AttendanceListDTO(memberName,sessionDate,attended));
			}
		}
		return attendanceListDTOS;
	}
}

