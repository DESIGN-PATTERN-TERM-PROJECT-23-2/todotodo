package com.project.todotodo.repository;

import com.holub.database.CSVImporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.text.ParseFailure;
import com.project.todotodo.Singleton.CategorySingleton;
import com.project.todotodo.Singleton.IndexSingleton;
import com.project.todotodo.Singleton.NodeListSingleton;
import com.project.todotodo.Singleton.NodeSingleton;
import com.project.todotodo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
@Repository
public class CategoryRepositoryHolub {

    @Autowired
    public CategoryRepositoryHolub() {

    }


    public Long saveCategoryAndGetId(Category category) {
        try {
            Long nodeListId = IndexSingleton.getInstance().getNodeListIndex();
            IndexSingleton.getInstance().setNodeListIndex(nodeListId+1);
            String sql1 = "INSERT INTO node_lists (node_list_id, parent_id) VALUES ("+nodeListId+", NULL)";
            NodeListSingleton.getInstance().getDatabase().execute(sql1);
            System.out.println(NodeListSingleton.getNodeLists());


            Long nodeId = IndexSingleton.getInstance().getNodeIndex();
            IndexSingleton.getInstance().setNodeIndex(nodeId+1);
            String sql2 = "INSERT INTO nodes (node_id, content, is_category, level, node_list_id) VALUES ("
                    +nodeId+", "+category.getContent()+", 1, 0,"+nodeListId+  ")";
            NodeSingleton.getInstance().getDatabase().execute(sql2);

            System.out.println(NodeListSingleton.getInstance().getDatabase().execute("select * from nodes"));

            Long categoryId = IndexSingleton.getInstance().getCategoryIndex();
            IndexSingleton.getInstance().setCategoryIndex(categoryId+1);
            String sql3 = "INSERT INTO categories (category_id, content, node_id) VALUES ("
                    +categoryId+", "+category.getContent()+", "+nodeId+")";
            CategorySingleton.getInstance().getDatabase().execute(sql3);

            return nodeId;

        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }


        return 0L;
    }

    public void removeCateogory(Long id) {
        try {
            String sql = "DELETE FROM categories WHERE node_id = ?";
            CategorySingleton.setCategories(CategorySingleton.getDatabase().execute(sql));
            String sql2 = "DELETE FROM nodes WHERE node_id = ?";
            NodeSingleton.setNodes(NodeSingleton.getDatabase().execute(sql2));

        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
        return;
    }
}
