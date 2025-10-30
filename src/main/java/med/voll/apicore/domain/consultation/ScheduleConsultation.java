package med.voll.apicore.domain.consultation;

import med.voll.apicore.domain.ValidationException;
import med.voll.apicore.domain.doctor.Doctor;
import med.voll.apicore.domain.doctor.DoctorRepository;
import med.voll.apicore.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleConsultation {

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    public void scheduler(DataScheduleConsultation data) {

        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("Patient id not exists!");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationException("Doctor id not exists!");
        }

        var doctor = chooseDoctor(data);
        var patient = patientRepository.getReferenceById(data.idPatient());
        var consultation = new Consultation(null, doctor, patient, data.dateTime(), null);
        consultationRepository.save(consultation);
    }

    private Doctor chooseDoctor(DataScheduleConsultation data) {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }

        if (data.specialty() == null) {
            throw new ValidationException("Specialty is mandatory when doctor is not selected!");
        }

        return doctorRepository.chooseFreeRandomDoctorOnTheDate(data.specialty(), data.dateTime());
    }

    private void cancel (DataCancellationConsultation data){
        if(!consultationRepository.existsById(data.idConsultation())){
            throw new ValidationException("Consultation Id provided does not exist");
        }

        var consultation = consultationRepository.getReferenceById(data.idConsultation());
        consultation.cancel(data.reason());
    }
}
