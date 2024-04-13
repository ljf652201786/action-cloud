package com.action.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnAllocationVo {
    private String dataId;
    private String type;
    private String contactId;
    private List<ColumnVo> columnVoList;
}
