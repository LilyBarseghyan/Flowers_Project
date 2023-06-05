package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerRepo {
    private String url = "jdbc:postgresql://10.212.20.233:5445/postgres?user=postgres&password=123";
    Connection conn = DriverManager.getConnection(url);

    public FlowerRepo() throws SQLException {
    }


    public List<Flower> selectAll() {
        ArrayList<Flower> array = null;
        try {
            conn.setSchema("flowers");
            try (Statement statement = conn.createStatement()) {
                ResultSet resultSet = statement.executeQuery(
                        "SELECT f_id, name, picture_url, price, category.type, description\n" +
                                "FROM flower\n" +
                                "    INNER JOIN category\n" +
                                "        ON flower.category_id = category.c_id;"
                );
                array = new ArrayList<>();
                while (resultSet.next()) {
                    array.add(new Flower(resultSet.getInt("f_id"),
                            resultSet.getString("name"),
                            resultSet.getString("picture_url"),
                            resultSet.getString("price"),
                            resultSet.getString("type"),
                            resultSet.getString("description")));

                }
                resultSet.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return array;

    }

    public List<Flower> selectByTitle(String title) throws SQLException {
        ArrayList<Flower> flowers = null;
        conn.setSchema("flowers");
        try (PreparedStatement statement = conn.prepareStatement("SELECT f_id, name, picture_url, price, category.type, description " +
                "FROM flower INNER JOIN category ON flower.category_id = category.c_id WHERE lower(name) LIKE lower(?)")) {
            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();
            flowers = new ArrayList<>();
            while (resultSet.next()) {
                flowers.add(new Flower(resultSet.getInt("f_id"),
                        resultSet.getString("name"),
                        resultSet.getString("picture_url"),
                        resultSet.getString("price"),
                        resultSet.getString("type"),
                        resultSet.getString("description")));
            }
        }
        return flowers;
    }


    public List<Flower> selectById(int id) throws SQLException {
        List<Flower> flowers;
        conn.setSchema("flowers");
        try (PreparedStatement statement = conn.prepareStatement("SELECT f_id, name, picture_url, price, category.type, description " +
                "FROM flower INNER JOIN category ON flower.category_id = category.c_id WHERE flower.f_id= ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            flowers = new ArrayList<>();
            while (resultSet.next()) {
                flowers.add(new Flower(resultSet.getInt("f_id"),
                        resultSet.getString("name"),
                        resultSet.getString("picture_url"),
                        resultSet.getString("price"),
                        resultSet.getString("type"),
                        resultSet.getString("description")));
            }
        }
        return flowers;
    }

    public List<Flower> selectByCategory(String type) throws SQLException {
        ArrayList<Flower> flowers = null;
        conn.setSchema("flowers");
        try (PreparedStatement statement = conn.prepareStatement("SELECT f_id, name, picture_url, price, category.type, " +
                "description FROM flower INNER JOIN category ON flower.category_id = category.c_id WHERE lower(category.type) LIKE ?")) {
            statement.setString(1, "%" + type + "%");
            ResultSet resultSet = statement.executeQuery();
            flowers = new ArrayList<>();
            while (resultSet.next()) {
                flowers.add(new Flower(resultSet.getInt("f_id"),
                        resultSet.getString("name"),
                        resultSet.getString("picture_url"),
                        resultSet.getString("price"),
                        resultSet.getString("type"),
                        resultSet.getString("description")));
            }
        }
        return flowers;
    }
}
