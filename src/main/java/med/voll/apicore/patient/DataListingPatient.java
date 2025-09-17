package med.voll.apicore.patient;

public record DataListingPatient(String name, String email, String cpf) {
    public DataListingPatient(Patient patient){
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
