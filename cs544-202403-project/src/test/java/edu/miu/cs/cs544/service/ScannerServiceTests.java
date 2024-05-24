package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.repository.AttendanceRepository;
import edu.miu.cs.cs544.service.contract.AttendancePayload;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ScannerServiceTests {
    @Autowired
    MockMvc mock;
    @TestConfiguration
    static class ScannerServiceImplTestContextConfiguration {
        @Bean
        public ScannerService scannerService() {
            return new ScannerServiceImpl();
        }
    }
    @Mock
    ScannerService scannerService;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void findAllRecords_passed() {
        List<AttendancePayload> found = Arrays.asList(new AttendancePayload(), new AttendancePayload());
        Mockito.when(scannerService.getAllRecords("01")).thenReturn(found);

        List<AttendancePayload> result = scannerService.getAllRecords("01");
        assertThat(result).isNotEmpty();
    }
}
