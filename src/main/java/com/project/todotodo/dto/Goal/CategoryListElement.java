package com.project.todotodo.dto.Goal;

import com.project.todotodo.domain.NodeDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryListElement {
    private long NodeId;
    private long CategoryId;
    private String content;
}
