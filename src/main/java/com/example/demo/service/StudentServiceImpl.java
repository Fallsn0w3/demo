package com.example.demo.service;


import com.example.demo.converter.StudentConverter;
import com.example.demo.dao.Student;
import com.example.demo.dao.StudentRepositary;
import com.example.demo.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepositary studentRepositary;
    @Override
    public StudentDTO getStudentById(long id) {
        Student student= studentRepositary.findById(id).orElseThrow(RuntimeException::new);
        return StudentConverter.convertStudent(student);
    }

    @Override
    public Long addNewStudent(StudentDTO studentDTO) {
        List<Student> studentList= studentRepositary.findByEmail(studentDTO.getEmail());
        if(!CollectionUtils.isEmpty(studentList)){
            throw new IllegalStateException("eamil:" + studentDTO.getEmail() + "has been taken");
        }
        Student student= studentRepositary.save(StudentConverter.convertStudentDTO(studentDTO));
        return student.getId();
    }

    @Override
    public void deleteStudentById(long id) {
        studentRepositary.findById(id).orElseThrow(()->new IllegalArgumentException("id" + id + "does not exist"));
        studentRepositary.deleteById(id);
    }

    @Override
    @Transactional
    public StudentDTO updataStudnetById(long id, String name, String email) {
        Student studentInDB=studentRepositary.findById(id).orElseThrow(()->new IllegalArgumentException("id" + id + "does not exist"));
        if(StringUtils.hasLength(name) && !studentInDB.getName().equals(name)){
            studentInDB.setName(name);
        }
        if(StringUtils.hasLength(email) && !studentInDB.getEmail().equals(email)){
            studentInDB.setEmail(email);
        }
        Student student=studentRepositary.save(studentInDB);
        return StudentConverter.convertStudent(student);
    }
}
