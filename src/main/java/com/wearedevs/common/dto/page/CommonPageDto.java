package com.wearedevs.common.dto.page;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class CommonPageDto {
    private int page = 0;
    private int pageSize = CommonPagination.PER_SIZE;

    public static Pageable getPageable(int page, int pageSize) {
        if (pageSize == 0) pageSize = CommonPagination.PER_SIZE;
        return PageRequest.of(page, pageSize);
    }
}
