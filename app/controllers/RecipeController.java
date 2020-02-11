package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.ErrorResponse;
import models.Ingredient;
import models.Recipe;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import play.twirl.api.Content;
import views.xml.*;

import javax.inject.Inject;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class RecipeController extends Controller {
    @Inject
    FormFactory formFactory;

    Recipe recipeFinder = new Recipe();
    //private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

    public Result getRecipes(int page, int maxRows) {
        JsonNode recipes = Json.toJson(this.recipeFinder.getAll(page, maxRows));
        ObjectNode node = play.libs.Json.newObject();
        node.put("recipes" , recipes);

        if (request().accepts("application/json"))
            return Results.ok(node).as("application/json");

        if (request().accepts("application/xml"))
            return Results.ok(views.xml.recipes.render(recipes));

        return Results.status(415);
        //return Results.ok(node).as("application/json").withHeader("hola", "pepito");
    }

    public Result createRecipe() {
        JsonNode node = request().body().asJson();
        Form<Recipe> form = formFactory.form(Recipe.class).bind(node);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Recipe newRecipe = form.get();

        if (this.recipeFinder.findByTitle(node.get("title").asText()).size() > 0) return Results.status(CONFLICT, "Ese título ya existe");

        this.recipes.add(newRecipe);
        newRecipe.save();

        if (request().accepts("application/xml")) {
            return Results.ok(views.xml.recipe.render(newRecipe));
        } else if (request().accepts("application/json")) {
            return Results.ok(play.libs.Json.toJson(newRecipe));
        }

        return Results.status(415);
    }

    public Result updateRecipe(Long id) {
        JsonNode node = request().body().asJson();
        Form<Recipe> form = formFactory.form(Recipe.class).bind(node);
        if (form.hasErrors()) {
            return Results.badRequest(form.errorsAsJson());
        }
        Recipe recipe = Recipe.findById(id);
        if (recipe==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = "La receta no existe";
            return Results.badRequest(play.libs.Json.toJson(error));
        }
        recipe.setTitle(node.get("title").asText());
        recipe.setEstimatedTime(node.get("estimatedTime").asText());
        recipe.setImageUrl(node.get("imageUrl").asText());
        recipe.setHowToMake(node.get("howToMake").asText());
        recipe.update();
        if (request().accepts("application/xml")) {
            return Results.ok(views.xml.recipe.render(recipe));
        } else if (request().accepts("application/json")) {
            return Results.ok(play.libs.Json.toJson(recipe));
        }
        return Results.status(415);
    }

    public Result deleteRecipe(Long id) {
        Recipe recipe = Recipe.findById(id);
        if (recipe==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = "La receta no existe";
            if (request().accepts("application/xml")) {
                return Results.badRequest(views.xml.errorResponse.render(error));
            } else if (request().accepts("application/json")) {
                return Results.badRequest(play.libs.Json.toJson(error));
            } else {
                return Results.badRequest(error.error);
            }
        }
        recipe.deleteRecipe(id);
        return Results.ok();
    }
}
