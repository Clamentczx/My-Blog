package com.clament.czx.service;


import com.clament.czx.DAO.ClassificationRepository;
import com.clament.czx.DataEntity.Classification;
import com.clament.czx.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClassificationServiceImpl implements ClassificationService{
    @Autowired
    private ClassificationRepository classificationRepository;

    @Transactional
    @Override
    public Classification saveClassification(Classification classification) {
        return classificationRepository.save(classification);
    }
    @Transactional
    @Override
    public Classification getClassification(Long id) {
        return classificationRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Classification> listClassification(Pageable pageable) {
        return classificationRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Classification updateClassification(Long id, Classification classification) {
        Classification c = classificationRepository.getOne(id);
        if(c==null){
            throw new NotFoundException("this blog is not exist");
        }
        BeanUtils.copyProperties(classification,c);
        return classificationRepository.save(c);
    }

    @Transactional
    @Override
    public void deleteClassification(Long id) {
        classificationRepository.deleteById(id);
    }

    @Override
    public Classification getClassificationByName(String name) {
        return classificationRepository.findByName(name);
    }

}
