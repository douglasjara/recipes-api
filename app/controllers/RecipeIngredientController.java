package controllers;

import actions.Timed;
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
import java.util.List;

@Timed
public class RecipeIngredientController extends Controller {
    @Inject
    FormFactory formFactory;
    @Inject
    MessagesApi messagesApi;

    public Result getRecipeIngredientsByRecipeId(Http.Request request, Long recipeId) {
        Messages messages = this.messagesApi.preferred(request);
        Recipe recipe = Recipe.findById(recipeId);
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

        List<RecipeIngredient> recipeIngredients = RecipeIngredient.findByRecipeId(recipeId);

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(recipeIngredients)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.recipeIngredients.render(recipeIngredients));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result createRecipeIngredient(Http.Request request, Long recipeId, Long ingredientId) {
        Messages messages = this.messagesApi.preferred(request);
        Form<RecipeIngredient> form = formFactory.form(RecipeIngredient.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        RecipeIngredient recipeIngredientReceived = form.get();

        Recipe recipe = Recipe.findById(recipeId);
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

        Ingredient ingredient = Ingredient.findById(ingredientId);
        if (ingredient == null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NOINGREDIENT");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        if (RecipeIngredient.findRecipeIngredient(recipeId, ingredientId)!=null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("RECIPE_INGREDIENT_EXIST");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        recipeIngredientReceived.setRecipe(recipe);
        recipeIngredientReceived.setIngredient(ingredient);
        recipeIngredientReceived.createRecipeIngredient();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(recipeIngredientReceived));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.recipeIngredient.render(recipeIngredientReceived));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result updateRecipeIngredient(Http.Request request, Long recipeId, Long ingredientId) {
        Messages messages = this.messagesApi.preferred(request);
        Form<RecipeIngredient> form = formFactory.form(RecipeIngredient.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        RecipeIngredient recipeIngredientReceived = form.get();
        RecipeIngredient recipeIngredientToUpdate = RecipeIngredient.findRecipeIngredient(recipeId, ingredientId);

        Recipe recipe = Recipe.findById(recipeId);
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

        Ingredient ingredient = Ingredient.findById(ingredientId);
        if (ingredient == null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NOINGREDIENT");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        if (recipeIngredientToUpdate==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NORECIPE_INGREDIENT");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        recipeIngredientToUpdate.setRecipe(recipe);
        recipeIngredientToUpdate.setIngredient(ingredient);
        recipeIngredientToUpdate.merge(recipeIngredientReceived);
        recipeIngredientToUpdate.updateRecipeIngredient();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(recipeIngredientToUpdate));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.recipeIngredient.render(recipeIngredientToUpdate));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result deleteRecipeIngredient(Http.Request request, Long recipeId, Long ingredientId) {
        Messages messages = this.messagesApi.preferred(request);
        Recipe recipe = Recipe.findById(recipeId);
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

        Ingredient ingredient = Ingredient.findById(ingredientId);
        if (ingredient == null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NOINGREDIENT");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        RecipeIngredient recipeIngredientToDelete = new RecipeIngredient();
        recipeIngredientToDelete.setRecipe(recipe);
        recipeIngredientToDelete.setIngredient(ingredient);
        recipeIngredientToDelete.deleteRecipeIngredient();

        return Results.ok();
    }
}
