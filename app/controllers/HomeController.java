package controllers;

import play.libs.Json;
import play.mvc.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomeController extends Controller {
    public Result index() {
        return ok(views.html.index.render());
    }
    
    public Result postman() {
        try (FileInputStream fis = new FileInputStream("public/docs/recipes.postman_collection.json");) {
            return ok(Json.parse(fis)).as("application/json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Results.badRequest("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Results.badRequest("File can't be read");
    }

    public Result methods() {
        return ok(views.html.methods.render());
    }
}
