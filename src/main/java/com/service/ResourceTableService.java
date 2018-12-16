package com.service;

import com.dto.APIResponseDTO;
import com.entity.ResourceTable;
import com.repository.ResourceTableRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service

public class ResourceTableService {

    @Autowired
    ResourceTableRespository resourceTableRespository;

    public List<ResourceTable> getAllResources(){
        return (List<ResourceTable>) resourceTableRespository.findAll();
    }
}
