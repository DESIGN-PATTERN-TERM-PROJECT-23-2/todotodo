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

        Long nodeListId = IndexSingleton.getInstance().getNodeListIndexAndAddOne();
        // String sql1 = "INSERT INTO node_lists (node_list_id, parent_id) VALUES ("+nodeListId+", NULL)";
        // NodeListSingleton.getInstance().getDatabase().execute(sql1);
        NodeListSingleton.getInstance().getNodeLists().insert(new Object[]{String.valueOf(nodeListId), "NULL"});
        System.out.println(NodeListSingleton.getNodeLists());


        Long nodeId = IndexSingleton.getInstance().getNodeIndexAndAddOne();
//            String sql2 = "INSERT INTO nodes (node_id, content, is_category, level, node_list_id) VALUES ("
//                    +nodeId+", "+category.getContent()+", 1, 0,"+nodeListId+  ")";
//            NodeSingleton.getInstance().getDatabase().execute(sql2);
        NodeSingleton.getInstance().getNodes().insert(new Object[]{String.valueOf(nodeId), category.getContent(), "1", "0", String.valueOf(nodeListId)});
        System.out.println(NodeSingleton.getNodes());

        Long categoryId = IndexSingleton.getInstance().getCategoryIndexAndAddOne();
        IndexSingleton.getInstance().setCategoryIndex(categoryId+1);
//            String sql3 = "INSERT INTO categories (category_id, content, node_id) VALUES ("
//                    +categoryId+", "+category.getContent()+", "+nodeId+")";
//            CategorySingleton.getInstance().getDatabase().execute(sql3);
        CategorySingleton.getInstance().getCategories().insert(new Object[]{String.valueOf(categoryId), String.valueOf(nodeId), category.getContent()});
        System.out.println(CategorySingleton.getCategories());

        // ********* table 에는 저장이 되는디 csv에선 저장이 안 됑
        Table table = null;
        try {table = NodeSingleton.getInstance().getDatabase().execute("select * from nodes");
        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }

        System.out.println(table.toString());
        return nodeId;
    }

    public void removeCateogory(Long id) {
        // ******* delete 어케함?
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
