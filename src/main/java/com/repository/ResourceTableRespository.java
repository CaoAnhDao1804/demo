package com.repository;

import com.entity.ResourceTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface ResourceTableRespository extends CrudRepository<ResourceTable, Long> {

    @Query(value = "Select DISTINCT  resource_table.*  from resource_table inner JOIN permiss on permiss.id_resource = resource_table.id where permiss.id_user = ?1 ;" , nativeQuery = true)
    public List<ResourceTable> findAllTableOfUserCanDo(Long idUser);

}
