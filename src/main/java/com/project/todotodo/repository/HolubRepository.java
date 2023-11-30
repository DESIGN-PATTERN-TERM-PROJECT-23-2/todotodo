package com.project.todotodo.repository;


import com.holub.database.Database;

import java.io.File;
import java.io.IOException;

import com.holub.database.CSVImporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.text.ParseFailure;

public class HolubRepository {
    Database database;


    public HolubRepository(){
        try {
            database = new Database(new File("Dbase"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Table getTable(String sqlQuery){
        try {
            return database.execute(sqlQuery);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseFailure e) {
            throw new RuntimeException(e);
        }
    }

}
