package com.action.system.manager.struct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermAllocationDto {
    private String type;
    private String contactId;
    private List<String> menuIds;
}
