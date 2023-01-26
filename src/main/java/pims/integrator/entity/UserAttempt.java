package pims.integrator.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class UserAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name =  "failed_attempt")
    private int failedAttempt;
    @Column(name = "lock_time")
    private Date lockTime;

}
