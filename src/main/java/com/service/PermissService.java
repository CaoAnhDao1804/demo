package com.service;
import com.dto.ActionTableDTO;
import com.dto.PermissionDTO;
import com.entity.ActionTable;
import com.entity.Permiss;
import com.entity.ResourceTable;
import com.exception.CustomException;
import com.model.ActionUser;
import com.repository.ActionTableRespository;
import com.repository.PermissRespository;
import com.repository.ResourceTableRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service

public class PermissService  {

    @Autowired
    PermissRespository permissRespository;

    @Autowired
    ActionTableRespository actionTableRespository;

    @Autowired
    ResourceTableRespository resourceTableRespository;



    public  List<ActionUser> getAllActionUser(Long idUser){
        List<ActionUser> listActionOfUser = new ArrayList<>();
        if (idUser!=null){
            List<Permiss> listPermissOfUser =  permissRespository.findByIdUser(idUser);
            for (Permiss permiss: listPermissOfUser){
                String action = getActionById(permiss.getIdAction());
                String tableName = getTableNameByid(permiss.getIdResource());
                ActionUser actionUser = new ActionUser();
                actionUser.setName(action+ "_" + tableName);
                listActionOfUser.add(actionUser);
            }
            return listActionOfUser;

        } else throw new CustomException("Null IdUser", 500);
    }

    String getActionById(Long idAction){
        if (idAction!= 0){
            ActionTable actionTable = actionTableRespository.findById(idAction).orElse(new ActionTable());
            if (actionTable !=null) {
              return actionTable.getName();
            } else throw new CustomException("Null Action Object", 500);
        } else throw new CustomException( "Null values", 500);
    }
    String getTableNameByid(Long idTable){
        if (idTable!= 0){
            ResourceTable resourceTable = resourceTableRespository.findById(idTable).orElse(new ResourceTable());
            if (resourceTable !=null){
                return resourceTable.getName();
            } else throw new CustomException("Null Table Object", 500);
        } else throw new CustomException("Null IdTable Value", 500);
    }

    public List<Permiss> getAllPermissOfUser(Long idUser) {
        return permissRespository.findByIdUser(idUser);
    }

    public Permiss addPermissionForUser(Permiss permiss) {
        return permissRespository.save(permiss);
    }

    public boolean deletePermissionOfUser(Permiss permiss) {
        Permiss permissCurrent = permissRespository.findByIdActionAndIdResourceAndIdUser(permiss.getIdAction(), permiss.getIdResource(), permiss.getIdUser());
        if (permissCurrent != null){
            permissRespository.delete(permissCurrent);
            return true;
        } else throw new CustomException("Not found permission!", 500);
    }

    public List<ResourceTable> getAllTableOfUser(Long idUser){
        return resourceTableRespository.findAllTableOfUserCanDo(idUser);
    }

    public List<ActionTable> getAllActionOfTableByUser(long idUser, long idTable) {
        return  actionTableRespository.getAllActionOfTableByUser( idUser,  idTable);
    }
    public List<ActionTableDTO> getAllActionDTOOfTableByUser(long idUser, long idTable) {
        List<ActionTableDTO> actionTableDTOS = new ArrayList<>();
        List<Permiss> permissList =   permissRespository.findByIdUserAndIdResource(idUser, idTable);
        for (Permiss permiss: permissList){
            ActionTableDTO actionTableDTO = new ActionTableDTO();
            actionTableDTO.setId(permiss.getIdAction());
            actionTableDTO.setIdPermiss(permiss.getId());
            actionTableDTO.setName(getActionById(permiss.getIdAction()));
            actionTableDTOS.add(actionTableDTO);
        }
        return actionTableDTOS;
    }

    public boolean checkExistedPermiss(Permiss permiss) {
        Permiss permissCurrent = permissRespository.findByIdActionAndIdResourceAndIdUser(permiss.getIdAction(), permiss.getIdResource(), permiss.getIdUser());
        if (permissCurrent !=null )
            return true;
        else return false;
    }

    public void deletePermissById(Long idPermiss){
         permissRespository.deleteById(idPermiss);
    }

    public List<PermissionDTO> getAllPermissionDTOOfUser(Long idUser) {
        List<ResourceTable> listTableOfUser = resourceTableRespository.findAllTableOfUserCanDo(idUser);
        List<PermissionDTO> listPermissionDTO = getAllPermissionDTOFromResourcesAndIdUser(listTableOfUser, idUser);
        return listPermissionDTO;
    }

    private List<PermissionDTO> getAllPermissionDTOFromResourcesAndIdUser(List<ResourceTable> listTableOfUser, Long idUser) {
        List<PermissionDTO> permissionDTOList = new ArrayList<>();
        for (ResourceTable resourceTable: listTableOfUser){
            PermissionDTO permissionDTO = new PermissionDTO();
            permissionDTO.setIdTable(resourceTable.getId());
            permissionDTO.setNameTable(resourceTable.getName());
            List<ActionTableDTO> actionTableDTOList = new ArrayList<>();
            actionTableDTOList = getAllActionDTOOfTableByUser(idUser, resourceTable.getId());
            permissionDTO.setListAction(actionTableDTOList);
            permissionDTOList.add(permissionDTO);
        }
        return permissionDTOList;
    }
}
