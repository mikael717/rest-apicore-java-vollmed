package med.voll.apicore.controller;

import jakarta.validation.Valid;
import med.voll.apicore.patient.DataListingPatient;
import med.voll.apicore.patient.DataRegisterPatient;
import med.voll.apicore.patient.Patient;
import med.voll.apicore.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataRegisterPatient data){
        repository.save(new Patient(data));
    }

    @GetMapping
    public Page<DataListingPatient> listing(@PageableDefault(sort = {"name"}, size = 10) Pageable pagination){
        return repository.findAll(pagination).map(DataListingPatient::new);
    }
}
