package med.voll.apicore.patient;

import med.voll.apicore.address.Address;

public record DataDetailsPatient(String name, String email, String phone, String cpf, Address address) {
    public DataDetailsPatient(Patient patient){
        this(patient.getName(),patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress());
    }
}
