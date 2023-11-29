package com.project.todotodo.repository;

import com.amazonaws.util.FakeIOException;
import com.holub.database.CSVImporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.text.ParseFailure;
import com.project.todotodo.Singleton.CategoryIndexSingleton;
import com.project.todotodo.Singleton.IndexSingleton;
import com.project.todotodo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
@Repository
public class CategoryRepositoryHolub {
    Table categories;
    Database database;

    @Autowired
    public CategoryRepositoryHolub() {
        try {
            insertData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void insertData() throws IOException {
        try {

            Reader in_category = new FileReader("categories.csv");
            CSVImporter csvImporter = new CSVImporter(in_category);
            categories = TableFactory.create(csvImporter);

            database = new Database(new File("."));
            Table table = database.execute("select * from categories");

            System.out.println(IndexSingleton.getInstance().getNodeIndex());


        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
        return;
    }

    public Long saveCategoryAndGetId(Category category) {
        return 0L;
    }

    public void removeCateogory(Long id) {
        try {
            String sql = "DELETE FROM categories WHERE node_id = ?";
            categories = database.execute(sql);

            String sql2 = "DELETE FROM nodes WHERE node_id = ?";

        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
        return;
    }
}
