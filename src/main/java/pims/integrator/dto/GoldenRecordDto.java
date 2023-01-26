package pims.integrator.dto;


import java.util.ArrayList;

public class GoldenRecordDto {
    private String auxId;
    private String city;
    private String dob;
    private String emrFingerprintCode;
    private String familyName;
    private String gender;
    private String givenName;
    private String natFingerprintCode;
    private String nationalId;
    private String phoneNumber;
    ArrayList< Object > sourceId = new ArrayList < Object > ();
    private String uid;


    // Getter Methods

    public String getAuxId() {
        return auxId;
    }

    public String getCity() {
        return city;
    }

    public String getDob() {
        return dob;
    }

    public String getEmrFingerprintCode() {
        return emrFingerprintCode;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGender() {
        return gender;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getNatFingerprintCode() {
        return natFingerprintCode;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUid() {
        return uid;
    }

    // Setter Methods

    public void setAuxId(String auxId) {
        this.auxId = auxId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmrFingerprintCode(String emrFingerprintCode) {
        this.emrFingerprintCode = emrFingerprintCode;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setNatFingerprintCode(String natFingerprintCode) {
        this.natFingerprintCode = natFingerprintCode;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
