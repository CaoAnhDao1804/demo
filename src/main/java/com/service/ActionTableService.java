package com.service;

import com.entity.ActionTable;
import com.repository.ActionTableRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ActionTableService {

    @Autowired
    ActionTableRespository actionTableRespository;

    public  List<ActionTable> getAllActions(){
        return (List<ActionTable>) actionTableRespository.findAll();
    }


}
