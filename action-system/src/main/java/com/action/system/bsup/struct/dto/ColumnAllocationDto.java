package com.action.system.bsup.struct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnAllocationDto {
    private String dataId;
    private String type;
    private String contactId;
    private List<ColumnDto> columnDtoList;
}
