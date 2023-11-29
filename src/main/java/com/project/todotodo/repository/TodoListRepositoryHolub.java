package com.project.todotodo.repository;

import com.holub.database.CSVImporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.text.ParseFailure;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class TodoListRepositoryHolub {
    Table todoList;
    Database database;

    public TodoListRepositoryHolub() {
        try {
            insertData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void insertData() throws IOException {
        try {

            Reader in_name = new FileReader("todo_lists.csv");
            CSVImporter csvImporter = new CSVImporter(in_name);
            todoList = TableFactory.create(csvImporter);

            database = new Database(new File("."));
            Table table = database.execute("select * from todo_lists");

        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
    }
}
