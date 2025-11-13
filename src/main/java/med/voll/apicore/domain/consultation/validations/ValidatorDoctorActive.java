package med.voll.apicore.domain.consultation.validations;

import med.voll.apicore.domain.ValidationException;
import med.voll.apicore.domain.consultation.DataScheduleConsultation;
import med.voll.apicore.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorDoctorActive implements ValidatorScheduleConsultation{

    @Autowired
    private DoctorRepository repository;

    public void validate(DataScheduleConsultation data) {
        //optional doctor choice
        if (data.idDoctor() == null) {
            return;
        }

        var doctorIsActive = repository.findActiveById(data.idDoctor());
            if (!doctorIsActive) {
                throw new ValidationException("Consultation cannot be scheduled with an inactive doctor!");
            }

    }
}
