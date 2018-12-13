package com.repository;

import com.entity.Permiss;
import com.entity.ResourceTable;
import com.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissRespository extends CrudRepository<Permiss, Long> {

    List<Permiss> findByIdUser(Long idUser);
    Permiss findByIdActionAndIdResourceAndIdUser(Long idAction, Long idResource, Long idUser);




}
