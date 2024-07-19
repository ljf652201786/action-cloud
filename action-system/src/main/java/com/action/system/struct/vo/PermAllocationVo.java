package com.action.system.struct.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermAllocationVo {
    private String type;
    private String contactId;
    private List<String> menuIds;
}
