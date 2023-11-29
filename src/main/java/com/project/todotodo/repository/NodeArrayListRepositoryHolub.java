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

public class NodeArrayListRepositoryHolub {

    Table nodeArrayList;
    Database database;

    public NodeArrayListRepositoryHolub() {
        try {
            insertData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void insertData() throws IOException {
        try {

            Reader in_category = new FileReader("node_array_list.csv");
            CSVImporter csvImporter = new CSVImporter(in_category);
            nodeArrayList = TableFactory.create(csvImporter);

            database = new Database(new File("."));
            Table table = database.execute("select * from node_array_list");




        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
    }
}
