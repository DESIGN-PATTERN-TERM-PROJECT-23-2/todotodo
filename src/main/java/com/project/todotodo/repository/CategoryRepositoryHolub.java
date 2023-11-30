package com.project.todotodo.repository;

import com.holub.database.*;
import com.holub.text.ParseFailure;
import com.project.todotodo.Singleton.*;
import com.project.todotodo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;

@Repository
public class CategoryRepositoryHolub implements CategoryRepositoryInterface{

    @Autowired
    public CategoryRepositoryHolub() {

    }


    public Long saveCategoryAndGetId(Category category) {
        try {

            Long nodeListId = IndexSingleton.getInstance().getNodeListIndexAndAddOne();
            NodeListSingleton.getInstance().getNodeLists().insert(new Object[]{String.valueOf(nodeListId), "NULL"});
            System.out.println(NodeListSingleton.getNodeLists());
            Writer out = new FileWriter("node_lists.csv");
            NodeListSingleton.getNodeLists().export(new CSVExporter(out));
            out.close();


            Long nodeId = IndexSingleton.getInstance().getNodeIndexAndAddOne();
            NodeSingleton.getInstance().getNodes().insert(new Object[]{String.valueOf(nodeId), category.getContent(), "1", "0", String.valueOf(nodeListId)});
            System.out.println(NodeSingleton.getNodes());
            Writer out2 = new FileWriter("nodes.csv");
            NodeSingleton.getNodes().export(new CSVExporter(out2));
            out2.close();

            Long categoryId = IndexSingleton.getInstance().getCategoryIndexAndAddOne();
            CategorySingleton.getInstance().getCategories().insert(new Object[]{String.valueOf(categoryId), String.valueOf(nodeId), category.getContent()});
            System.out.println(CategorySingleton.getCategories());
            Writer out3 = new FileWriter("categories.csv");
            CategorySingleton.getCategories().export(new CSVExporter(out3));
            out3.close();

            Table table = null;
            table = DatagBaseSingleton.getInstance().getDatabase().execute("select * from nodes");
            //Writer out123 = new FileWriter("")
            System.out.println(table.toString());

            table = DatagBaseSingleton.getInstance().getDatabase().execute("select * from categories");


            System.out.println(table.toString());
            return nodeId;
        } catch (IOException | ParseFailure e) {
        e.printStackTrace();
        }
        return -1L;
    }

    public void removeCateogory(Long id) {
        // 추후 구현
    }

}
