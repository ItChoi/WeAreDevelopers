package com.wearedevs.common.dto.list;

import com.wearedevs.common.dto.page.CommonPageDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonListSearchConditionDto extends CommonPageDto {
    private String searchCondition;
    private String searchVal;
}
