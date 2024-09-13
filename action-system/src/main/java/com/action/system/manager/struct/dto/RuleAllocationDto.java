package com.action.system.manager.struct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleAllocationDto {
    private String menuId;
    private List<String> ruleIds;
}
