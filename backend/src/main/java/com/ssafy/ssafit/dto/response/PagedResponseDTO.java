package com.ssafy.ssafit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedResponseDTO<T> {
    private int currentPage;   // 현재 페이지
    private int pageSize;      // 페이지 크기
    private int totalPages;    // 총 페이지 수
    private int totalItems;    // 총 항목 수
    private List<T> data;      // 페이징된 데이터 리스트
}
