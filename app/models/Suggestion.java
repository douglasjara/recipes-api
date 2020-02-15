package models;

import io.ebean.Finder;
import play.data.validation.Constraints;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class Suggestion extends baseModel {
    @Constraints.Required
    private String title;
    private String description;

    private static final Finder<Long, Suggestion> find = new Finder<>(Suggestion.class);

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Suggestion createSuggestion() {
        this.save();
        return this;
    }

    public Suggestion updateSuggestion() {
        this.update();
        return this;
    }

    public static Suggestion findById(Long id) {
        return find.byId(id);
    }

    public static List<Suggestion> findByRecipeId(Long recipeId) {
        return find.query().where().eq("recipe_id", recipeId).findList();
    }

    public void merge(Suggestion suggestion) {
        this.title = suggestion.title == null ? this.title : suggestion.title;
        this.description = suggestion.description == null ? this.description : suggestion.description;
    }

    public Boolean deleteSuggestion(Long id) {
        Suggestion suggestion = find.byId(id);
        return suggestion.delete();
    }
}
