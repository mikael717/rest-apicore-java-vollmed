package med.voll.apicore.domain.consultation.validations;

import med.voll.apicore.domain.ValidationException;
import med.voll.apicore.domain.consultation.DataScheduleConsultation;
import med.voll.apicore.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientActive implements ValidatorScheduleConsultation{

    @Autowired
    private PatientRepository repository;

    public void validate(DataScheduleConsultation data) {
        var patientIsActive = repository.findActiveById(data.idPatient());
        if (!patientIsActive) {
            throw new ValidationException("Consultation cannot be scheduled with an excluded patient!");
        }
    }
}
