package edu.miu.cs.cs544.service.contract;

import edu.miu.cs.cs544.domain.Member;
import edu.miu.cs.cs544.domain.Scanner;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AttendancePayload implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Member member;
    private Scanner scanner;
    private LocalDateTime dateTime;


    public AttendancePayload(Member member, Scanner scanner, LocalDateTime dateTime) {
        this.member = member;
        this.scanner = scanner;
        this.dateTime = dateTime;
    }
}
