package med.voll.apicore.domain.consultation.validations;

import med.voll.apicore.domain.ValidationException;
import med.voll.apicore.domain.consultation.ConsultationRepository;
import med.voll.apicore.domain.consultation.DataScheduleConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorDoctorWithAnotherConsultationAtTheSameTime implements ValidatorScheduleConsultation{

    @Autowired
    private ConsultationRepository repository;

    public void validate (DataScheduleConsultation data){
        var doctorHaveAnotherConsultationAtTheSameTime = repository.existsByDoctorIdAndDateTime(data.idDoctor(), data.dateTime());
        if(doctorHaveAnotherConsultationAtTheSameTime){
            throw new ValidationException("Doctor already has another consultation scheduled for that same time!");
        }
    }
}
