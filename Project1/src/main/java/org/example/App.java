package org.example;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        Javalin app = Javalin.create().start(7050);
        FlowerRepo flowerrepo = new FlowerRepo();
        Handler selectAll = context -> {
            context.res.addHeader("Access-Control-Allow-Origin", "*");
            List<Flower> flowers = flowerrepo.selectAll();
            context.json(flowers);
        };

        app.get("/flower", selectAll);

        Handler selectTitle = new Handler() {
            @Override
            public void handle(@NotNull Context context) throws SQLException {
                context.res.addHeader("Access-Control-Allow-Origin", "*");
                String title = context.req.getParameter("name");
                List<Flower> flowers = flowerrepo.selectByTitle(title);
                context.json(flowers);
            }
        };

        app.get("/flowerByTitle", selectTitle);

        Handler selectID = new Handler() {
            @Override
            public void handle(@NotNull Context context) throws SQLException {
                context.res.addHeader("Access-Control-Allow-Origin", "*");
                int id = Integer.parseInt(context.pathParam("id"));
                List<Flower> flowers = flowerrepo.selectById(id);
                context.json(flowers);

            }
        };

        app.get("/flower/{id}", selectID);

        Handler selectCategory = new Handler() {
            @Override
            public void handle(@NotNull Context context) throws SQLException {
                context.res.addHeader("Access-Control-Allow-Origin", "*");
                String type = context.req.getParameter("type");
                List<Flower> flowers = flowerrepo.selectByCategory(type);
                context.json(flowers);

            }
        };

        app.get("/flowerByCategory", selectCategory);

    }
}