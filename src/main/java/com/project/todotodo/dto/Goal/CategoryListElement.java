package com.project.todotodo.dto.Goal;

import com.project.todotodo.domain.NodeDomain;
import com.project.todotodo.model.Category;
import com.project.todotodo.model.Node;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryListElement {
    private Long nodeId;
    private Long categoryId;
    private String content;

    public CategoryListElement ToDTO(Category node){
        CategoryListElement dto = new CategoryListElement();
        dto.setNodeId(node.getNodeId());
        dto.setContent(node.getContent());
        dto.setCategoryId(node.getCategoryId());
        return dto;
    }
}
