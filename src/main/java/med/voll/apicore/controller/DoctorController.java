package med.voll.apicore.controller;

import jakarta.validation.Valid;
import med.voll.apicore.doctor.DataListingDoctor;
import med.voll.apicore.doctor.DataRegisterDoctor;
import med.voll.apicore.doctor.Doctor;
import med.voll.apicore.doctor.DoctorRepository;
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

import java.util.List;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataRegisterDoctor data){
        repository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DataListingDoctor> listing(@PageableDefault(sort = {"name"}, size = 10) Pageable pagination){
        return repository.findAll(pagination).map(DataListingDoctor::new);
    }
}
