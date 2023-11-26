package com.project.todotodo.dto.Goal;

import com.project.todotodo.domain.NodeDomain;
<<<<<<< HEAD
=======
import com.project.todotodo.model.Category;
import com.project.todotodo.model.Node;
>>>>>>> Service
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryListElement {
    private long NodeId;
<<<<<<< HEAD
    private long CategoryId;
    private String content;
=======
    private String content;

    public CategoryListElement ToDTO(Node node){
        CategoryListElement dto = new CategoryListElement();
        dto.setNodeId(node.getNodeId());
        dto.setContent(node.getContent());
        return dto;
    }
>>>>>>> Service
}
