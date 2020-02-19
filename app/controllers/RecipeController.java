package controllers;

import actions.Timed;
import io.ebean.PagedList;
import models.*;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.Messages;
import play.i18n.MessagesApi;
import play.libs.Json;
import play.mvc.*;
import javax.inject.Inject;

@Timed
public class RecipeController extends Controller {
    @Inject
    FormFactory formFactory;
    @Inject
    MessagesApi messagesApi;

    public Result getRecipes(Http.Request request, int page, int maxRows) {
        PagedList<Recipe> recipes = Recipe.getAll(page, maxRows);
        PagedRecipes pagedRecipes = new PagedRecipes(page, recipes.getPageSize(), recipes.getTotalCount(), recipes.getList());

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(pagedRecipes)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.pagedRecipes.render(pagedRecipes));

        return Results.status(415);
    }

    public Result getRecipe(Http.Request request, Long id) {
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
    public Result createRecipe(Http.Request request) {
        Messages messages = this.messagesApi.preferred(request);
        Form<Recipe> recipeForm = formFactory.form(Recipe.class).bindFromRequest(request);
        if (recipeForm.hasErrors()) return Results.badRequest(recipeForm.errorsAsJson());
        Recipe recipeReceived = recipeForm.get();

        if (Recipe.findByTitle(recipeReceived.getTitle()).size() > 0) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("RECIPE_EXIST");
            if (request.accepts("application/xml"))
                return Results.status(CONFLICT, views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.status(CONFLICT, Json.toJson(error));
            else
                return Results.status(CONFLICT, error.error);
        }

        recipeReceived.createRecipe();

        if (request.accepts("application/json"))
            return Results.ok(play.libs.Json.toJson(recipeReceived));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.recipe.render(recipeReceived));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result updateRecipe(Http.Request request, Long id) {
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
            return Results.ok(play.libs.Json.toJson(recipeToUpdate));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.recipe.render(recipeToUpdate));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result deleteRecipe(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Recipe recipeToDelete = Recipe.findById(id);

        if (recipeToDelete==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NORECIPE");
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
