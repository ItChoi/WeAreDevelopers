package com.wearedevs.common.dto.list;

import com.wearedevs.common.dto.page.CommonPagination;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommonListPageWrapperResponseDto {
    private String code;
    private String message;
    private String listType;
    // 피드백 사항 - 제네릭 타입 와일드카드 지정
    private List<?> list;
    private CommonPagination pagination;

    @Builder
    public CommonListPageWrapperResponseDto(String code, String message, List<?> list, CommonPagination pagination) {
        this.code = code;
        this.message = message;
        this.list = list;
        this.pagination = pagination;
        changeGenericListType();
    }

    private void changeGenericListType() {
        if (this.list == null || this.list.isEmpty()) return;
        Object o = this.list.get(0);
        this.listType = o.getClass().getSimpleName();
    }
}
