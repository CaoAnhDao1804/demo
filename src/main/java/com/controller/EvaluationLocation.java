package com.controller;

import com.dto.APIResponseDTO;
import com.dto.EvaluationDTO;
import com.entity.Evaluation;
import com.exception.CustomException;
import com.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/app")
public class EvaluationLocation {

//    @Autowired
//    EvaluationService evaluationService;
//
//    @PostMapping(value = "/evaluation")
//    public APIResponseDTO evaluationLocation (HttpServletRequest request, @RequestBody() EvaluationDTO evaluationDTO) throws CustomException{
//        if (evaluationDTO.getScore() == null) {
//            throw new CustomException("Core not found!",400);
//        }
//        return new APIResponseDTO(200,"Evaluation Successfull!", evaluationService.save(evaluationDTO, request));
//    }



}
