package med.voll.apicore.doctor;

import med.voll.apicore.address.Address;

public record DataDetailsDoctor(String name, String email, String phone, String crm, Specialty specialty, Address address){
    public DataDetailsDoctor(Doctor doctor){
        this(doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty(),doctor.getAddress());
    }
}
