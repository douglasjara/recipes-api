package models;

import io.ebean.Finder;
import io.ebean.PagedList;
import play.data.validation.Constraints;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Ingredient extends baseModel {
    @Constraints.Required
    private String name;

    //@ManyToMany(mappedBy = "ingredients")
    //public Set<Recipe> recipes;

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

    public static Boolean deleteIngredient(Long id) {
        Ingredient ingredient = find.byId(id);
        return ingredient.delete();
    }
}
