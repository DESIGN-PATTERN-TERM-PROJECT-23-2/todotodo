package com.project.todotodo.repository;

import com.project.todotodo.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class CategoryRepositoryClass {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryRepositoryClass(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long saveCategoryAndGetId(Category category) {
        String sql1 = "INSERT INTO node_lists (node_id) VALUES (NULL)";
        KeyHolder keyHolder1 = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            return ps;
        }, keyHolder1);

        String sql2 = "INSERT INTO nodes (content, is_category, level, node_list_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder2 = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getContent());
            ps.setInt(2, 1);
            ps.setInt(3, 0);
            ps.setLong(4, keyHolder1.getKey().longValue());
            return ps;
        }, keyHolder2);

        String sql3 = "INSERT INTO categories (content, node_id) VALUES (?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, category.getContent());
            ps.setLong(2, keyHolder2.getKey().longValue());
            return ps;
        }, keyHolder2);

        return keyHolder2.getKey().longValue();

    }

    public void removeCateogory(Long id){

        String sql = "DELETE FROM categories WHERE node_id = ?";
        jdbcTemplate.update(sql, id);

        String sql2 = "DELETE FROM nodes WHERE node_id = ?";
        jdbcTemplate.update(sql2, id);
    }


}
