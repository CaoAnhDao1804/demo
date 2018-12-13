package com.repository;

import com.entity.ActionTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActionTableRespository extends CrudRepository<ActionTable, Long> {

    @Query(value = "Select DISTINCT  action_table.*  from action_table inner JOIN permiss on permiss.id_action = action_table.id where permiss.id_user = ?1 \n" +
            "and permiss.id_resource = ?2 ;" , nativeQuery = true)
    List<ActionTable> getAllActionOfTableByUser(Long idUser, Long idResource);


}
