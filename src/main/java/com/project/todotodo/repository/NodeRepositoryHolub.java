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

public class NodeRepositoryHolub {
    Table node;
    Database database;

    public NodeRepositoryHolub() {
        try {
            insertData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void insertData() throws IOException {
        try {

            Reader in_name = new FileReader("nodes.csv");
            CSVImporter csvImporter = new CSVImporter(in_name);
            node = TableFactory.create(csvImporter);

            database = new Database(new File("."));
            Table table = database.execute("select * from nodes");

        } catch (IOException | ParseFailure e) {
            e.printStackTrace();
        }
    }

    public void removeNodeByNodeId(Long id) {
        try {
            String sql2 = "DELETE FROM nodes WHERE node_id = ?";
            node = database.execute(sql2);

        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
        return;
    }
}
