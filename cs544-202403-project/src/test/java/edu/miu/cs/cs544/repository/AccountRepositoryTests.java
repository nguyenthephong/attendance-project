package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Account;
import edu.miu.cs.cs544.domain.AccountType;
import edu.miu.cs.cs544.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void findAccountsByBalanceCondition_passed() throws Exception {
        Account acc = new Account("Phong", "Student", AccountType.DINING_POINTS, 15000.00, 10.00);
        List<Account> accs = new ArrayList<Account>(List.of(acc));
        Member member = new Member(1L,"The Phong","Nguyen","thephong.nguyen@miu.edu","123456789",null,accs,null);
        memberRepository.save(member);
        entityManager.flush();

        List<String> expected = accountRepository.findAccountsByBalanceCondition();
        assertEquals(expected.size(), 1);
    }
}
