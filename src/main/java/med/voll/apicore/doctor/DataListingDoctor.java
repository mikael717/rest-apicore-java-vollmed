package med.voll.apicore.doctor;

public record DataListingDoctor(String name, String email, String crm, Specialty specialty) {

    public DataListingDoctor(Doctor doctor){
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
