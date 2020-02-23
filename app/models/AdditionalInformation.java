package models;

import io.ebean.Finder;
import play.data.validation.Constraints;
import validators.DifficultyOptions;

import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
public class AdditionalInformation extends baseModel {
    @Constraints.Required
    @DifficultyOptions
    private String difficulty;
    @Min(1)
    private Integer guests;
    @Constraints.Required
    private Float price;
    @Constraints.Required
    private Integer kal;

    private static final Finder<Long, AdditionalInformation> find = new Finder<>(AdditionalInformation.class);
    private static final Finder<Long, Recipe> recipeFind = new Finder<>(Recipe.class);

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public Integer getGuests() { return guests; }
    public void setGuests(Integer guests) { this.guests = guests; }
    public Float getPrice() { return price; }
    public void setPrice(Float price) { this.price = price; }
    public Integer getKal() { return kal; }
    public void setKal(Integer kal) { this.kal = kal; }

    public AdditionalInformation createAdditionalInformation() {
        this.save();
        return this;
    }

    public AdditionalInformation updateAdditionalInformation() {
        this.update();
        return this;
    }

    public static AdditionalInformation findById(Long id) {
        return find.byId(id);
    }

    public static AdditionalInformation findByRecipeId(Long recipeId) {
        Recipe recipe = recipeFind.byId(recipeId);
        return find.byId(recipe.additionalInformation.getId());
    }

    public void merge(AdditionalInformation additionalInformation) {
        this.difficulty = additionalInformation.difficulty == null ? this.difficulty : additionalInformation.difficulty;
        this.guests = additionalInformation.guests == null ? this.guests : additionalInformation.guests;
        this.price = additionalInformation.price == null ? this.price : additionalInformation.price;
        this.kal = additionalInformation.kal == null ? this.kal : additionalInformation.kal;
    }

    public static Boolean deleteAdditionalInformation(Long id) {
        AdditionalInformation additionalInformation = find.byId(id);
        return additionalInformation.delete();
    }
}
