package med.voll.apicore.domain.patient;

public record DataListingPatient(Long id, String name, String email, String cpf) {
    public DataListingPatient(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
