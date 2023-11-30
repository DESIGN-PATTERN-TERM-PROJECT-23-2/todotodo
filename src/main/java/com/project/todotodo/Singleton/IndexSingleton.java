package com.project.todotodo.Singleton;

import com.holub.database.CSVImporter;
import com.holub.database.Database;
import com.holub.database.Table;
import com.holub.database.TableFactory;
import com.holub.text.ParseFailure;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class IndexSingleton {
        private static volatile IndexSingleton instance;

        private IndexSingleton() {}



    // setter하고 csv에도 저장하는 코드 필요함
    @Getter
    @Setter
    private static Long nodeIndex;
        @Getter
        @Setter
        private static Long nodeListIndex;
        @Getter
        @Setter
        private static Long todoListIndex;
        @Getter
        @Setter
        private static Long categoryIndex;
        @Getter
        @Setter
        private static Long nodeArrayListIndex;

        private static Table keys;
        static Database database;

        public static IndexSingleton getInstance() {
            if (instance == null) {
                synchronized(IndexSingleton.class){
                    if (instance == null) {
                        instance = new IndexSingleton();
                        try {insertData();}
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
            return instance;
        }

    private static void insertData() throws IOException {
        try {

            Reader in_category = new FileReader("keys.csv");
            CSVImporter csvImporter = new CSVImporter(in_category);
            keys = TableFactory.create(csvImporter);

            database = new Database(new File("."));
            Table table = database.execute("select * from keys");

            // 탭을 기준으로 문자열을 분할
            String[] lines = keys.toString().split("\n");
            String[] _keys = lines[1].split("\t"); // 키 추출
            String[] values = lines[3].split("\t"); // 값 추출

            // 키-값 쌍 출력
            for (int i = 0; i < _keys.length; i++) {
                System.out.println("(" + _keys[i] + ", " + values[i] + ")");
            }

            nodeIndex = Long.parseLong(values[0]);
            nodeListIndex = Long.parseLong(values[1]);
            todoListIndex = Long.parseLong(values[2]);
            categoryIndex = Long.parseLong(values[3]);
            nodeArrayListIndex = Long.parseLong(values[4]);

        } catch (IOException | ParseFailure e){
            e.printStackTrace();
        }
        return;
    }


    // csv 처리 필요 (edit이 없나?)
    public static Long getNodeIndexAndAddOne() {
        return nodeIndex++;
    }

    public static Long getNodeListIndexAndAddOne() {
        return nodeListIndex++;
    }

    public static Long getTodoListIndexAndAddOne() {
        return todoListIndex++;
    }

    public static Long getCategoryIndexAndAddOne() {
        return categoryIndex++;
    }

    public static Long getNodeArrayListIndexAndAddOne() {
            return nodeArrayListIndex++;
    }


}
