package com.example.carmanagement.commons.data.request;


import com.example.carmanagement.commons.data.model.Filter;
import com.example.carmanagement.commons.data.model.paging.PageableCustom;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SearchRequest {
    private String keyword;
    private List<Filter> filters;
    private PageableCustom pageable;
    List<String> searchColumns;
}
