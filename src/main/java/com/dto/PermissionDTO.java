package com.dto;

import lombok.Data;

import java.util.List;

@Data

public class PermissionDTO {
    private Long idTable;
    private String nameTable;
    private List<ActionTableDTO> listAction;

}
