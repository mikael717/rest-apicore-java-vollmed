package med.voll.apicore.controller;

import jakarta.validation.Valid;
import med.voll.apicore.domain.consultation.DataDetailsConsultation;
import med.voll.apicore.domain.consultation.DataScheduleConsultation;
import med.voll.apicore.domain.consultation.ScheduleConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ScheduleConsultation scheduleConsultation;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid DataScheduleConsultation date){
        scheduleConsultation.scheduler(date);
        return ResponseEntity.ok(new DataDetailsConsultation(null, null, null, null));
    }
}
