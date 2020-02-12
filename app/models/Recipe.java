package models;

import io.ebean.Finder;
import play.data.validation.Constraints;
import java.util.ArrayList;
import java.util.*;
import javax.persistence.*;

@Entity
public class Recipe extends baseModel {
    //crear constructor

    @Constraints.Required
    private String title;
    private String estimatedTime;
    private String imageUrl;
    //public List<Ingredient> ingredients = new ArrayList<Ingredient>() {};
    private String howToMake;

    private static final Finder<Long, Recipe> find = new Finder<>(Recipe.class);

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHowToMake() {
        return howToMake;
    }

    public void setHowToMake(String howToMake) {
        this.howToMake = howToMake;
    }

    public static List<Recipe> getAll(int page, int maxRows) {
        page = page == 0 ? 1 : page;
        maxRows = maxRows>10 || maxRows == 0 ? 10 : maxRows;
        return find.query()
                .setFirstRow((page - 1) * maxRows)
                .setMaxRows(maxRows)
                .orderBy("title")
                .findList();
    }

    public static Recipe findById(Long id) {
        return find.byId(id);
    }

    public static List<Recipe> findByTitle(String title) {
        List<Recipe> recetas = find.query().where().like("title", title).findList();

        for (Recipe receta:recetas) {
            System.out.println(receta.getId()+":"+receta.title);
        }

        return find.query().where().like("title", title).findList();
    }

    @play.db.ebean.Transactional
    public Boolean deleteRecipe(Long id) {
        Recipe recipe = find.byId(id);
        //antes borrar dependencias
        return recipe.delete();
    }
}
