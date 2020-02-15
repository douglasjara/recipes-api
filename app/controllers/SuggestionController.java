package controllers;

import models.ErrorResponse;
import models.Recipe;
import models.Suggestion;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import javax.inject.Inject;
import java.util.List;

public class SuggestionController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result getSuggestionsByRecipeId(Http.Request request, Long recipeId) {
        Recipe recipe = Recipe.findById(recipeId);
        if (recipe == null) {
            ErrorResponse error = new ErrorResponse();
            error.error = "La receta no existe";
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        List<Suggestion> suggestions = Suggestion.findByRecipeId(recipeId);

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(suggestions)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.suggestions.render(suggestions));

        return Results.status(415);
    }

    public Result getSuggestion(Http.Request request, Long id) {
        Suggestion suggestion = Suggestion.findById(id);
        if (suggestion == null) {
            ErrorResponse error = new ErrorResponse();
            error.error = "El consejo no existe";
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(suggestion)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.suggestion.render(suggestion));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result createSuggestion(Http.Request request, Long recipeId) {
        Form<Suggestion> form = formFactory.form(Suggestion.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Suggestion suggestionReceived = form.get();

        Recipe recipe = Recipe.findById(recipeId);
        if (recipe == null) {
            ErrorResponse error = new ErrorResponse();
            error.error = "La receta no existe";
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }
        recipe.addSuggestion(suggestionReceived);
        recipe.updateRecipe();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(suggestionReceived));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.suggestion.render(suggestionReceived));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result updateSuggestion(Http.Request request, Long id) {
        Form<Suggestion> form = formFactory.form(Suggestion.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Suggestion suggestionReceived = form.get();
        Suggestion suggestionToUpdate = Suggestion.findById(id);

        if (suggestionToUpdate==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = "El consejo no existe";
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        suggestionToUpdate.merge(suggestionReceived);
        suggestionToUpdate.updateSuggestion();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(suggestionToUpdate));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.suggestion.render(suggestionToUpdate));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result deleteSuggestion(Http.Request request, Long id) {
        Suggestion suggestionToDelete = Suggestion.findById(id);

        if (suggestionToDelete==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = "El consejo no existe";
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        suggestionToDelete.deleteSuggestion(id);

        return Results.ok();
    }
}
