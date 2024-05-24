package edu.miu.cs.cs544.repository;

import edu.miu.common.domain.AuditData;
import edu.miu.cs.cs544.domain.Member;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    private Member createTestMember(
            String firstName,
            String lasName,
            String barCode
    ){
        return new Member(
                firstName,
                lasName,
                firstName + lasName + "@gmail.com",
                barCode,
                new HashSet<>(),
                new ArrayList<>(),
                new AuditData()
        );
    }

    @Transactional
    @Test
    public void whenBarCodeSupplied_ThenFindMember(){
        final var member1 = createTestMember(
                "Kerim", "Amansaryyev", "KerimBarCode"
        );

        final var member2 = createTestMember(
                "Nazar", "Amanov", "NazarBarCode"
        );

        memberRepository.save(member1);
        memberRepository.save(member2);

        memberRepository.flush();

        memberRepository.findMemberByBarCode("KerimBarCode")
                .ifPresentOrElse(
                        (member) -> Assertions.assertEquals(member.getFirstName(), "Kerim"),
                        Assertions::fail);
    }

    @Test
    @Transactional
    public void whenSameBarCode_ThenThrowException(){
        final var sameBarCode = "Same BarCode";

        final var member1 = createTestMember(
                "Kerim", "Amansaryyev", sameBarCode
        );

        final var member2 = createTestMember(
                "Nazar", "Amanov", sameBarCode
        );

        memberRepository.save(member1);
        memberRepository.save(member2);

        Assertions.assertThrows(
                DataIntegrityViolationException.class,
                () -> memberRepository.flush()
        );
    }
}
