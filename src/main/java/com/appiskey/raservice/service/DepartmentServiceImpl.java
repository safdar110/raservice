package com.appiskey.raservice.service;

import com.appiskey.raservice.model.Department;
import com.appiskey.raservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by appiskey on 2/4/19.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(@RequestBody Department department){
        return departmentRepository.save(department);
    }

    @Override
    public Iterable<Department> getAllDepartments(){
        return departmentRepository.findAll();

    }

    @Override
    public Optional<Department> getDepartmentByID(UUID id){

        return departmentRepository.findById(id);

    }

    @Override
    public String deleteDepartment(UUID id){
        departmentRepository.deleteById(id);
        return "Deleted Succesfully!";
    }

    @Override
    public ResponseEntity<Department> editDepartment(@RequestBody Department department) {
        Optional<Department> mDepartment = departmentRepository.findById(department.getId());
        if (!mDepartment.isPresent())
            return ResponseEntity.notFound().build();
      //  department.setId(id);
        department.setCreatedAt(mDepartment.get().getCreatedAt());

        return new ResponseEntity<>(departmentRepository.save(department), HttpStatus.OK);
    }

    @Override
    public Iterable<Department> searchDepartment(String keyword){
        Iterable<Department> department = departmentRepository.findByDepartmentNameContainingIgnoreCase(keyword);
        return department;
    }

}
