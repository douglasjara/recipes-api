package models;

import javax.persistence.Embeddable;
import java.io.Serializable;

// N-M relation solution based on:
// https://stackoverflow.com/questions/27204672/custom-bridge-table-in-playframework-ebean

@Embeddable
public class RecipeIngredientId implements Serializable
{
    public Integer recipe_id;
    public Integer ingredient_id;

    public int hashCode() {
        return recipe_id + ingredient_id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        RecipeIngredientId b = (RecipeIngredientId)obj;
        if(b == null) return false;
        if (b.recipe_id == recipe_id && b.ingredient_id == ingredient_id) return true;
        return false;
    }
}
