package controllers;

import models.AdditionalInformation;
import models.ErrorResponse;
import models.Recipe;
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

public class AdditionalInformationController extends Controller {
    @Inject
    FormFactory formFactory;
    @Inject
    MessagesApi messagesApi;

    public Result getAdditionalInformationByRecipeId(Http.Request request, Long recipeId) {
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

        AdditionalInformation additionalInformation = AdditionalInformation.findByRecipeId(recipeId);

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(additionalInformation)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.additionalInformation.render(additionalInformation));

        return Results.status(415);
    }

    public Result getAdditionalInformation(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        AdditionalInformation additionalInformation = AdditionalInformation.findById(id);
        if (additionalInformation == null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NOADDINFO");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(additionalInformation)).as("application/json");
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.additionalInformation.render(additionalInformation));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result createAdditionalInformation(Http.Request request, Long recipeId) {
        Messages messages = this.messagesApi.preferred(request);
        Form<AdditionalInformation> form = formFactory.form(AdditionalInformation.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        AdditionalInformation additionalInformationReceived = form.get();

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

        additionalInformationReceived.createAdditionalInformation();

        Long additionalInformationToDeleteId = null;
        if (recipe.additionalInformation!=null)
            additionalInformationToDeleteId = recipe.additionalInformation.getId();

        recipe.additionalInformation = additionalInformationReceived;
        recipe.updateRecipe();

        if(additionalInformationToDeleteId!=null)
            AdditionalInformation.deleteAdditionalInformation(additionalInformationToDeleteId);

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(additionalInformationReceived));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.additionalInformation.render(additionalInformationReceived));

        return Results.status(415);
    }

    @play.db.ebean.Transactional
    public Result updateAdditionalInformation(Http.Request request, Long id) {
        Messages messages = this.messagesApi.preferred(request);
        Form<AdditionalInformation> form = formFactory.form(AdditionalInformation.class).bindFromRequest(request);
        if (form.hasErrors()) return Results.badRequest(form.errorsAsJson());
        AdditionalInformation additionalInformationReceived = form.get();
        AdditionalInformation additionalInformationToUpdate = AdditionalInformation.findById(id);
        if (additionalInformationToUpdate==null) {
            ErrorResponse error = new ErrorResponse();
            error.error = messages.at("NOADDINFO");
            if (request.accepts("application/xml"))
                return Results.badRequest(views.xml.errorResponse.render(error));
            if (request.accepts("application/json"))
                return Results.badRequest(Json.toJson(error));
            else
                return Results.badRequest(error.error);
        }

        additionalInformationToUpdate.merge(additionalInformationReceived);
        additionalInformationToUpdate.updateAdditionalInformation();

        if (request.accepts("application/json"))
            return Results.ok(Json.toJson(additionalInformationToUpdate));
        if (request.accepts("application/xml"))
            return Results.ok(views.xml.additionalInformation.render(additionalInformationToUpdate));

        return Results.status(415);
    }
}
