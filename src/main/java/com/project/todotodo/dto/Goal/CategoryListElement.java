package com.project.todotodo.dto.Goal;

import com.project.todotodo.domain.NodeDomain;
import com.project.todotodo.model.Category;
import com.project.todotodo.model.Node;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryListElement {
    private long NodeId;
    private String content;

    public CategoryListElement ToDTO(Node node){
        CategoryListElement dto = new CategoryListElement();
        dto.setNodeId(node.getNodeId());
        dto.setContent(node.getContent());
        return dto;
    }
}
