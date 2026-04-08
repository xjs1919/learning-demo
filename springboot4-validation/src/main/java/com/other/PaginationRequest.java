package com.other;

import lombok.Data;

@Data
public class PaginationRequest {
    private Integer pageIndex;
    private Integer pageSize;
}
