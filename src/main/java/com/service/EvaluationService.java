package com.service;

import com.config.JwtTokenProvider;
import com.dto.EvaluationDTO;
import com.entity.Evaluation;
import com.entity.PlaceCategory;
import com.entity.Traveler;
import com.exception.CustomException;
import com.repository.EvaluationRepository;
import com.repository.PlaceCategoryRepository;
import com.repository.TravelerResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    TravelerResponsitory travelerResponsitory;


    public List<Evaluation> findAllEvaluation(){
        return (List<Evaluation>) evaluationRepository.findAll();
    }

    public Optional<Evaluation> findById(Long id){
        return evaluationRepository.findById(id);
    }

    public List<Evaluation> findAllEvaluationByIdUser(Long idUser) {
        return evaluationRepository.findAllByIdUser(idUser);
    }

    public  void createEvaluation(Evaluation evaluation){
        evaluationRepository.save(evaluation);
    }

    public List<Evaluation> findAllRatingWithTheSameLocationOfTwoUser(Long idUserRecommend, Long idUser2) {
        return evaluationRepository.findAllRatingWithTheSameLocationOfTwoUser(idUserRecommend,idUser2);
    }

    public void updateEvaluation(Evaluation evaluation){
        evaluationRepository.save(evaluation);
    }

    public void deleteEvaluation(Long id){
        evaluationRepository.deleteById(id);
    }

    public Evaluation save(EvaluationDTO evaluationDTO, HttpServletRequest request) {

        Traveler travelerCurrent = travelerResponsitory.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(request)));
        Evaluation evaluationNew = new Evaluation();
        evaluationNew.setIdLocation(evaluationDTO.getIdLocation());
        evaluationNew.setScore(evaluationDTO.getScore());
        evaluationNew.setIdUser(travelerCurrent.getId());
        return  evaluationRepository.save(evaluationNew);
    }
}
