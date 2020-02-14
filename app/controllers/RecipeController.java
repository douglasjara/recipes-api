package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.ebean.PagedList;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import play.twirl.api.Content;
import views.xml.*;
import javax.inject.Inject;
import java.util.List;

public class RecipeController extends Controller {
    @Inject
    FormFactory formFactory;

    Recipe recipeFinder = new Recipe();

    public Result getRecipes(Http.Request request, int page, int maxRows) {
        PagedList<Recipe> recipes = this.recipeFinder.getAll(page, maxRows);
        PagedRecipes pagedRecipes = new PagedRecipes(page, recipes.getPageSize(), recipes.getTotalCount(), recipes.getList());

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(pagedRecipes)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.pagedRecipes.render(pagedRecipes));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result createRecipe(Http.Request request) {
        Form<Recipe> form = formFactory.form(Recipe.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Recipe recipeReceived = form.get();

        if (this.recipeFinder.findByTitle(recipeReceived.getTitle()).size() > 0)
            return Results.status(CONFLICT, "Ese título ya existe");

        recipeReceived.createRecipe();

        if (request.accepts("application/json"))
            return Results.ok(play.libs.Json.toJson(recipeReceived));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.recipe.render(recipeReceived));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result updateRecipe(Http.Request request, Long id) {
        Form<Recipe> form = formFactory.form(Recipe.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Recipe recipeReceived = form.get();
        Recipe recipeToUpdate = Recipe.findById(id);

        if (recipeToUpdate==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = "La receta no existe";
            return Results.badRequest(play.libs.Json.toJson(error));
        }

        if(recipeReceived.additionalInformation != null) {
            AdditionalInformation additionalInformation = AdditionalInformation.findById(recipeToUpdate.additionalInformation.getId());
            additionalInformation.merge(recipeReceived.additionalInformation);
            additionalInformation.updateAdditionalInformation();
        }

        recipeToUpdate.merge(recipeReceived);
        recipeToUpdate.updateRecipe();

        if (request.accepts("application/json"))
            return Results.ok(play.libs.Json.toJson(recipeToUpdate));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.recipe.render(recipeToUpdate));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result deleteRecipe(Http.Request request, Long id) {
        Recipe recipeToDelete = Recipe.findById(id);

        if (recipeToDelete==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = "La receta no existe";

            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(play.libs.Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        recipeToDelete.deleteRecipe(id);

        return Results.ok();
    }
}
