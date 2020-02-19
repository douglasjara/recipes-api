package controllers;

import actions.Timed;
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

@Timed
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

    public Result getIngredient(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Ingredient ingredient = Ingredient.findById(id);
        if (ingredient == null) {
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
            return Results.ok(Json.toJson(ingredient)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.ingredient.render(ingredient));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result createIngredient(Http.Request request) {
        Messages messages = this.messagesApi.preferred(request);
        Form<Ingredient> form = formFactory.form(Ingredient.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Ingredient ingredientReceived = form.get();

        if (Ingredient.findByName(ingredientReceived.getName()).size() > 0) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("INGREDIENT_EXIST");
            if (request.accepts("application/xml"))
                return Results.status(CONFLICT, views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.status(CONFLICT, Json.toJson(error));
            else
                return Results.status(CONFLICT, error.error);
        }

        ingredientReceived.createIngredient();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(ingredientReceived));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.ingredient.render(ingredientReceived));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result updateIngredient(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Form<Ingredient> form = formFactory.form(Ingredient.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Ingredient ingredientReceived = form.get();
        Ingredient ingredientToUpdate = Ingredient.findById(id);

        if (ingredientToUpdate==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NOINGREDIENT");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        ingredientToUpdate.merge(ingredientReceived);
        ingredientToUpdate.updateIngredient();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(ingredientToUpdate));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.ingredient.render(ingredientToUpdate));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result deleteIngredient(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Ingredient ingredientToDelete = Ingredient.findById(id);

        if (ingredientToDelete==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NORECIPE");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        ingredientToDelete.deleteIngredient(id);

        return Results.ok();
    }
}
