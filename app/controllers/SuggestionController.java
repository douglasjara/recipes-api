package controllers;

import actions.Timed;
import models.ErrorResponse;
import models.Recipe;
import models.Suggestion;
import play.cache.Cached;
import play.cache.SyncCacheApi;
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
public class SuggestionController extends Controller {
    @Inject
    FormFactory formFactory;
    @Inject
    MessagesApi messagesApi;
    @Inject
    SyncCacheApi cache;

    @Cached(key="getSuggestionsByRecipeId", duration = 5)
    public Result getSuggestionsByRecipeId(Http.Request request, Long recipeId) {
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

        List<Suggestion> suggestions = Suggestion.findByRecipeId(recipeId);

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(suggestions)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.suggestions.render(suggestions));

        return Results.status(415);
    }

    @Cached(key="getSuggestion", duration = 5)
    public Result getSuggestion(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Suggestion suggestion = Suggestion.findById(id);
        if (suggestion == null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NOSUGGESTION");
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
        Messages messages = this.messagesApi.preferred(request);
        Form<Suggestion> form = formFactory.form(Suggestion.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Suggestion suggestionReceived = form.get();

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
        recipe.addSuggestion(suggestionReceived);
        recipe.updateRecipe();
        removeCache();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(suggestionReceived));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.suggestion.render(suggestionReceived));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result updateSuggestion(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Form<Suggestion> form = formFactory.form(Suggestion.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        Suggestion suggestionReceived = form.get();
        Suggestion suggestionToUpdate = Suggestion.findById(id);

        if (suggestionToUpdate==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NOSUGGESTION");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        suggestionToUpdate.merge(suggestionReceived);
        suggestionToUpdate.updateSuggestion();
        removeCache();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(suggestionToUpdate));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.suggestion.render(suggestionToUpdate));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result deleteSuggestion(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Suggestion suggestionToDelete = Suggestion.findById(id);

        if (suggestionToDelete==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NOSUGGESTION");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        suggestionToDelete.deleteSuggestion(id);
        removeCache();

        return Results.ok();
    }

    private void removeCache() {
        this.cache.remove("getSuggestionsByRecipeId");
        this.cache.remove("getSuggestion");
    }
}
