package med.voll.apicore.domain.doctor;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import med.voll.apicore.domain.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@EqualsAndHashCode(of = "id")
public class Doctor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private boolean active;

    public Doctor() {
    }

    public Doctor(Long id, String name, String email, String crm, Specialty specialty, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.crm = crm;
        this.specialty = specialty;
        this.address = address;
    }

    public Doctor(DataRegisterDoctor data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCrm() {
        return crm;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public Address getAddress() {
        return address;
    }

    public void updateInformation(@Valid DataUpdateDoctor data) {
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.phone() != null){
            this.phone = data.phone();
        }
        if(data.address() != null){
            this.address.updateInformation(data.address());
        }
    }

    public void logicalDelete() {
        this.active = false;
    }
}
