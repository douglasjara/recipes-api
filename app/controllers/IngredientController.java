package controllers;

import io.ebean.PagedList;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;

public class IngredientController extends Controller {
    @Inject
    FormFactory formFactory;
    @Inject
    MessagesApi messagesApi;

    public Result getIngredients(Http.Request request, int page, int maxRows) {
        PagedList<Ingredient> ingredients = Ingredient.getAll(page, maxRows);
        PagedIngredients pagedIngredients = new PagedIngredients(page, ingredients.getPageSize(), ingredients.getTotalCount(), ingredients.getList());

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(pagedIngredients)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.pagedIngredients.render(pagedIngredients));

        return Results.status(415);
    }

    public Result getIngredientsByRecipeId(Http.Request request, Long recipeId) {
        Messages messages = this.messagesApi.preferred(request);
        Ingredient recipe = Ingredient.findById(recipeId);
        if (recipe == null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NORECIPE");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        /*List<Ingredient> ingredients = Ingredient.findByRecipeId(recipeId);

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(ingredients)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.ingredients.render(ingredients));*/

        return Results.status(415);
    }

    public Result getIngredient(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Recipe recipe = Recipe.findById(id);
        if (recipe == null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NORECIPE");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(recipe)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.recipe.render(recipe));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result createIngredient(Http.Request request, Long recipeId) {
        Messages messages = this.messagesApi.preferred(request);
        Form<Recipe> form = formFactory.form(Recipe.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Recipe recipeReceived = form.get();

        if (Recipe.findByTitle(recipeReceived.getTitle()).size() > 0) {
            ErrorResponse error = new ErrorResponse();
            error.error = "Ese título ya existe";
            if (request.accepts("application/xml"))
                return Results.status(CONFLICT, views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.status(CONFLICT, Json.toJson(error));
            else
                return Results.status(CONFLICT, error.error);
        }

        recipeReceived.createRecipe();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(recipeReceived));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.recipe.render(recipeReceived));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result updateIngredient(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Form<Recipe> form = formFactory.form(Recipe.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Recipe recipeReceived = form.get();
        Recipe recipeToUpdate = Recipe.findById(id);

        if (recipeToUpdate==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NORECIPE");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        recipeToUpdate.merge(recipeReceived);
        recipeToUpdate.updateRecipe();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(recipeToUpdate));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.recipe.render(recipeToUpdate));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result deleteIngredient(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Recipe recipeToDelete = Recipe.findById(id);

        if (recipeToDelete==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NORECIPE");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        recipeToDelete.deleteRecipe(id);

        return Results.ok();
    }
}
