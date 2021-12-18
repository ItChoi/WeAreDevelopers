package com.wearedevs.common.dto.page;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonPagination {
    public static final int PER_PAGE = 10;
    public static final int PER_SIZE = 10;

    private int perPage = PER_PAGE; // 페이지당 보여질 아이템 개수
    private int pageSize = PER_SIZE; // 이전, 다음을 눌렀을 때 보여지는 페이지 수

    private int curPage; // 현재 페이지
    private int totalPages; // 총 페이지 수
    private int totalItems; // 총 아이템 수

    private boolean prevPage; // 이전 페이지 여부
    private boolean nextPage; // 다음 페이지 여부

    @Builder
    public CommonPagination(int perPage, int pageSize, int curPage, int totalPages, int totalItems, boolean prevPage, boolean nextPage) {
        this.perPage = perPage;
        this.pageSize = pageSize;
        this.curPage = curPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.prevPage = prevPage;
        this.nextPage = nextPage;
    }
}
