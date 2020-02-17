package models;

import io.ebean.Finder;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class RecipeIngredient extends baseModel {
    @Constraints.Required
    private Float quantity;
    @Constraints.Required
    private String measureUnit;

    private static final Finder<Long, RecipeIngredient> find = new Finder<>(RecipeIngredient.class);

    public Float getQuantity() { return quantity; }
    public void setQuantity(Float quantity) { this.quantity = quantity; }
    public String getMeasureUnit() { return measureUnit; }
    public void setMeasureUnit(String measureUnit) { this.measureUnit = measureUnit; }

    public RecipeIngredient createRecipeIngredient() {
        this.save();
        return this;
    }

    public RecipeIngredient updateRecipeIngredient() {
        this.update();
        return this;
    }

    public static RecipeIngredient findById(Long id) {
        return find.byId(id);
    }

    public static List<RecipeIngredient> findByRecipeId(Long recipeId) {
        return find.query().where().eq("recipe_id", recipeId).findList();
    }

    public void merge(RecipeIngredient recipeIngredient) {
        this.quantity = recipeIngredient.quantity == null ? this.quantity : recipeIngredient.quantity;
        this.measureUnit = recipeIngredient.measureUnit == null ? this.measureUnit : recipeIngredient.measureUnit;
    }

    public Boolean deleteRecipeIngredient(Long id) {
        RecipeIngredient recipeIngredient = find.byId(id);
        return recipeIngredient.delete();
    }
}
