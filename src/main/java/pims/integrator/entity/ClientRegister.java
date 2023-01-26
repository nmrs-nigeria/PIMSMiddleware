package pims.integrator.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class ClientRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    private String gender;
    private Date dob;
    private String address;
    private String city;
    @Column(name = "lga_code")
    private int lgaCode;
    @Column(name = "state_code")
    private int stateCode;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "datim_code")
    private String datimCode;
    @Column(name = "patient_id")
    private String patientID;
    @Column(name = "finger_id")
    private String fingerID;
    @Column(name = "patient_unique_id")
    private String patientUniqueID;

}
