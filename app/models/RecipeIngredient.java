package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.ebean.Finder;
import io.ebean.Model;
import io.ebean.annotation.CreatedTimestamp;
import io.ebean.annotation.UpdatedTimestamp;
import play.data.validation.Constraints;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

// N-M relation solution based on:
// https://stackoverflow.com/questions/27204672/custom-bridge-table-in-playframework-ebean

@Entity
@JsonIgnoreProperties({"_ebean_intercept", "_$dbName"})
public class RecipeIngredient extends Model {
    public RecipeIngredient() {
        recipeIngredientId = new RecipeIngredientId();
    }

    @EmbeddedId
    protected RecipeIngredientId recipeIngredientId;

    @ManyToOne
    @JoinColumn(name = "recipe_id", insertable = false, updatable = false)
    public Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", insertable = false, updatable = false)
    public Ingredient ingredient;

    @Version
    @JsonIgnore
    private Long version;
    @CreatedTimestamp
    @JsonIgnore
    private Timestamp whenCreated;
    @UpdatedTimestamp
    @JsonIgnore
    private Timestamp whenUpdated;
    @Constraints.Required
    private Float quantity;
    @Constraints.Required
    private String measureUnit;

    private static final Finder<Long, RecipeIngredient> find = new Finder<>(RecipeIngredient.class);

    public Long getVersion() {
        return version;
    }
    public void setVersion(Long version) {
        this.version = version;
    }
    public Timestamp getWhenCreated() {
        return whenCreated;
    }
    public void setWhenCreated(Timestamp whenCreated) {
        this.whenCreated = whenCreated;
    }
    public Timestamp getWhenUpdated() {
        return whenUpdated;
    }
    public void setWhenUpdated(Timestamp whenUpdated) {
        this.whenUpdated = whenUpdated;
    }
    public Float getQuantity() { return quantity; }
    public void setQuantity(Float quantity) { this.quantity = quantity; }
    public String getMeasureUnit() { return measureUnit; }
    public void setMeasureUnit(String measureUnit) { this.measureUnit = measureUnit; }

    @JsonIgnore
    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        recipeIngredientId.recipe_id = recipe.getId().intValue();
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
        recipeIngredientId.ingredient_id = ingredient.getId().intValue();
    }

    public RecipeIngredient createRecipeIngredient() {
        this.save();
        return this;
    }

    public RecipeIngredient updateRecipeIngredient() {
        this.update();
        return this;
    }

    public static RecipeIngredient findRecipeIngredient(Long recipeId, Long ingredientId) {
        return find.query()
                .where()
                .eq("recipe_id", recipeId)
                .eq("ingredient_id", ingredientId)
                .findOne();
    }

    public static List<RecipeIngredient> findByRecipeId(Long recipeId) {
        return find.query().where().eq("recipe_id", recipeId).findList();
    }

    public Boolean deleteRecipeIngredient() {
        return this.delete();
    }

    public void merge(RecipeIngredient recipeIngredient) {
        this.quantity = recipeIngredient.quantity == null ? this.quantity : recipeIngredient.quantity;
        this.measureUnit = recipeIngredient.measureUnit == null ? this.measureUnit : recipeIngredient.measureUnit;
    }
}
