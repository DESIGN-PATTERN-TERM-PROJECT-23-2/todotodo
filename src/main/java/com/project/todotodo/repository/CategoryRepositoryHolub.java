package com.project.todotodo.repository;

import com.amazonaws.util.FakeIOException;
import com.holub.database.CSVImporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.text.ParseFailure;
import com.project.todotodo.Singleton.CategoryIndexSingleton;
import com.project.todotodo.model.Category;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CategoryRepositoryHolub {
    Table categories;
    Database database;

    public CategoryRepositoryHolub() {
        try {
            insertData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void insertData() throws IOException {
        try {

        Reader in_name = new FileReader("categories.csv");
        CSVImporter csvImporter = new CSVImporter(in_name);
        categories = TableFactory.create(csvImporter);

        database = new Database(new File("."));
        Table table = database.execute("select * from categories");
        Long nextIdx = 2L +1L;
        // 수정
            CategoryIndexSingleton.getInstance().setIndex(nextIdx);
        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
    }

    public Long saveCategoryAndGetId(Category category) {
        return 0L;
    }

    public void removeCateogory(Long id) {
        String sql = "DELETE FROM categories WHERE node_id = ?";


        String sql2 = "DELETE FROM nodes WHERE node_id = ?";

        return;
    }
}
