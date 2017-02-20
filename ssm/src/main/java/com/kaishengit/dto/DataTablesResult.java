package com.kaishengit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataTablesResult {

    private String draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private Object data;

}
