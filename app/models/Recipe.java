package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.ebean.*;
import io.ebean.Query;
import org.hibernate.validator.constraints.URL;
import play.data.validation.Constraints.*;
import validators.EstimatedTimeFormat;
import java.util.*;
import javax.persistence.*;

// N-M relation solution based on:
// https://stackoverflow.com/questions/27204672/custom-bridge-table-in-playframework-ebean

@Entity
@JsonIgnoreProperties({"_ebean_intercept", "_$dbName"})
public class Recipe extends baseModel {
    @Required
    private String title;
    @EstimatedTimeFormat
    private String estimatedTime;
    @URL
    private String imageUrl;
    @MinLength(50)
    @Column(columnDefinition = "TEXT") //funciona con PostgreSQL y MySQL
    private String howToMake;

    @OneToOne(cascade = CascadeType.ALL)
    public AdditionalInformation additionalInformation;

    @OneToMany(cascade = CascadeType.ALL)
    public List<RecipeIngredient> recipeIngredients;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Suggestion> suggestions;

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

    public Recipe createRecipe() {
        this.save();
        return this;
    }

    public Recipe updateRecipe() {
        this.update();
        return this;
    }

    public static PagedList<Recipe> getAll(int page, int maxRows) {
        page = page == 0 ? 1 : page;
        maxRows = maxRows>10 || maxRows == 0 ? 10 : maxRows;
        return find.query()
                .setFirstRow((page - 1) * maxRows)
                .setMaxRows(maxRows)
                .orderBy("title")
                .findPagedList();
    }

    public static PagedList<Recipe> searchRecipes(int page, int maxRows, String title, String ingredients, Float price, Integer kal) {
        page = page == 0 ? 1 : page;
        maxRows = maxRows>10 || maxRows == 0 ? 10 : maxRows;

        List<String> ingredientsToSearch= Arrays.asList(ingredients.toLowerCase().split("\\s*(=>|,|\\s)\\s*"));

        return find.query()
                .where()
                .ilike("title", "%"+title+"%")
                .and()
                .in("recipeIngredients.ingredient.name", ingredientsToSearch)
                .gt("additionalInformation.price", price)
                .gt("additionalInformation.kal", kal)
                .setFirstRow((page - 1) * maxRows)
                .setMaxRows(maxRows)
                .orderBy("title")
                .findPagedList();
    }

    public static PagedList<Recipe> searchRecipes(int page, int maxRows, String title, Float price, Integer kal) {
        page = page == 0 ? 1 : page;
        maxRows = maxRows>10 || maxRows == 0 ? 10 : maxRows;

        return find.query()
                .where()
                .ilike("title", "%"+title+"%")
                .and()
                .gt("additionalInformation.price", price)
                .gt("additionalInformation.kal", kal)
                .setFirstRow((page - 1) * maxRows)
                .setMaxRows(maxRows)
                .orderBy("title")
                .findPagedList();
    }

    public static Recipe findById(Long id) {
        return find.byId(id);
    }

    public static List<Recipe> findByTitle(String title) {
        return find.query().where().ilike("title", title).findList();
    }

    public Boolean deleteRecipe(Long id) {
        Recipe recipe = find.byId(id);
        recipe.delete();
        return true;
    }

    public void merge(Recipe recipe) {
        this.title = recipe.title == null ? this.title : recipe.title;
        this.estimatedTime = recipe.estimatedTime == null ? this.estimatedTime : recipe.estimatedTime;
        this.imageUrl = recipe.imageUrl == null ? this.imageUrl : recipe.imageUrl;
        this.howToMake = recipe.howToMake == null ? this.howToMake : recipe.howToMake;
    }

    public void addSuggestion(Suggestion suggestion) {
        this.suggestions.add(suggestion);
    }
}
