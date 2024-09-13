package br.com.danielchipolesch.domain.services;

import br.com.danielchipolesch.domain.dtos.basicSubjectDtos.BasicSubjectRequestCreateDto;
import br.com.danielchipolesch.domain.dtos.basicSubjectDtos.BasicSubjectRequestUpdateDto;
import br.com.danielchipolesch.domain.dtos.basicSubjectDtos.BasicSubjectResponseDto;
import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.domain.exceptions.BasicSubjectException;
import br.com.danielchipolesch.infrastructure.repositories.BasicSubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicSubjectService {

    @Autowired
    BasicSubjectRepository basicSubjectRepository;

    @Autowired
    ModelMapper modelMapper;

    public BasicSubjectResponseDto create(BasicSubjectRequestCreateDto request) throws Exception {
        if(basicSubjectRepository.existsByNumber(request.getBasicNumber())){
            throw new Exception(BasicSubjectException.ALREADY_EXISTS.getMessage());
        }

        BasicSubject basicSubject = modelMapper.map(request, BasicSubject.class);
        basicSubjectRepository.save(basicSubject);
        return modelMapper.map(basicSubject, BasicSubjectResponseDto.class);
    }


    public BasicSubjectResponseDto update(Long id, BasicSubjectRequestUpdateDto request) throws Exception{

        BasicSubject basicSubject = basicSubjectRepository.findById(id)
                .orElseThrow(() -> new Exception(BasicSubjectException.NOT_FOUND.getMessage()));

        basicSubject.setBasicNumber(request.getBasicNumber().isBlank() ? basicSubject.getBasicNumber() : request.getBasicNumber());
        basicSubject.setName(request.getName().isBlank() ? basicSubject.getName() : request.getName());
        basicSubject.setDescription(request.getDescription().isBlank() ? basicSubject.getDescription() : request.getDescription());

        basicSubjectRepository.save(basicSubject);

        return modelMapper.map(basicSubject, BasicSubjectResponseDto.class);
    }

    public BasicSubjectResponseDto delete(Long id) throws Exception {
        BasicSubject basicSubject = basicSubjectRepository.findById(id)
                .orElseThrow(() -> new Exception(BasicSubjectException.NOT_FOUND.getMessage()));

        basicSubjectRepository.delete(basicSubject);

        return modelMapper.map(basicSubject, BasicSubjectResponseDto.class);
    }

    public BasicSubjectResponseDto getById(Long id) throws Exception {
        BasicSubject basicSubject = basicSubjectRepository.findById(id)
                .orElseThrow(() -> new Exception(BasicSubjectException.NOT_FOUND.getMessage()));

        return modelMapper.map(basicSubject, BasicSubjectResponseDto.class);
    }

    public BasicSubjectResponseDto getByNumber(String number) throws Exception {
        BasicSubject basicSubject = basicSubjectRepository.findByNumber(number);
//                .orElseThrow(() -> new Exception(BasicSubjectException.NOT_FOUND.getMessage()));

        return modelMapper.map(basicSubject, BasicSubjectResponseDto.class);
    }

    public List<BasicSubjectResponseDto> getAll(Pageable pageable) throws Exception {
        Page<BasicSubject> basicSubjects = basicSubjectRepository.findAll(pageable);

        List<BasicSubjectResponseDto> responseDtos = basicSubjects.stream().map(basicSubject -> modelMapper.map(basicSubject, BasicSubjectResponseDto.class)).toList();

        return responseDtos;
    }
}
