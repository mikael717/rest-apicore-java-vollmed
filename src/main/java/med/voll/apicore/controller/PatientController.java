package med.voll.apicore.controller;

import jakarta.validation.Valid;
import med.voll.apicore.domain.patient.DataDetailsPatient;
import med.voll.apicore.domain.patient.DataListingPatient;
import med.voll.apicore.domain.patient.DataRegisterPatient;
import med.voll.apicore.domain.patient.DataUpdatePatient;
import med.voll.apicore.domain.patient.Patient;
import med.voll.apicore.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterPatient data, UriComponentsBuilder uriBuilder){
        var patient = new Patient(data);
        repository.save(patient);
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailsPatient(patient));
    }

    @GetMapping
    public ResponseEntity<Page<DataListingPatient>> listing(@PageableDefault(sort = {"name"}, size = 10) Pageable pagination){
        var page = repository.findAllByActiveTrue(pagination).map(DataListingPatient::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdatePatient data){
        var patient = repository.getReferenceById(data.id());
        patient.updateInformation(data);

        return ResponseEntity.ok(new DataDetailsPatient(patient));
    }

    @DeleteMapping("{id}")
    @Transactional
    @Secured("ROLE_ADMIN")
    public ResponseEntity delete(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.logicalDelete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity detail (@PathVariable Long id){
        var patient = repository.getReferenceById(id);

        return ResponseEntity.ok(new DataDetailsPatient(patient));
    }
}