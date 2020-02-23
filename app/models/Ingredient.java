package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.ebean.Finder;
import io.ebean.PagedList;
import play.data.validation.Constraints;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

// N-M relation solution based on:
// https://stackoverflow.com/questions/27204672/custom-bridge-table-in-playframework-ebean

@Entity
@JsonIgnoreProperties({"_ebean_intercept", "_$dbName"})
public class Ingredient extends baseModel {
    @Constraints.Required
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    public List<RecipeIngredient> recipeIngredients;

    private static final Finder<Long, Ingredient> find = new Finder<>(Ingredient.class);

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Ingredient createIngredient() {
        this.save();
        return this;
    }

    public Ingredient updateIngredient() {
        this.update();
        return this;
    }

    public static PagedList<Ingredient> getAll(int page, int maxRows) {
        page = page == 0 ? 1 : page;
        maxRows = maxRows>10 || maxRows == 0 ? 10 : maxRows;
        return find.query()
                .setFirstRow((page - 1) * maxRows)
                .setMaxRows(maxRows)
                .orderBy("name")
                .findPagedList();
    }

    public static Ingredient findById(Long id) {
        return find.byId(id);
    }

    public static List<Ingredient> findByName(String title) {
        return find.query().where().ilike("name", title).findList();
    }

    public static Boolean deleteIngredient(Long id) {
        Ingredient ingredient = find.byId(id);
        return ingredient.delete();
    }

    public void merge(Ingredient ingredient) {
        this.name = ingredient.name == null ? this.name : ingredient.name;
    }
}
