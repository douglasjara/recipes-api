// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/conf/routes
// @DATE:Tue Feb 11 10:47:58 COT 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_2: controllers.HomeController,
  // @LINE:11
  RecipeController_0: controllers.RecipeController,
  // @LINE:17
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_2: controllers.HomeController,
    // @LINE:11
    RecipeController_0: controllers.RecipeController,
    // @LINE:17
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_2, RecipeController_0, Assets_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_2, RecipeController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """explore""", """controllers.HomeController.explore"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tutorial""", """controllers.HomeController.tutorial"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes""", """controllers.RecipeController.getRecipes()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe""", """controllers.RecipeController.createRecipe()"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/""" + "$" + """recipeId<[^/]+>""", """controllers.RecipeController.updateRecipe(recipeId:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/""" + "$" + """recipeId<[^/]+>""", """controllers.RecipeController.deleteRecipe(recipeId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_2.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_HomeController_explore1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("explore")))
  )
  private[this] lazy val controllers_HomeController_explore1_invoker = createInvoker(
    HomeController_2.explore,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "explore",
      Nil,
      "GET",
      this.prefix + """explore""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_tutorial2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tutorial")))
  )
  private[this] lazy val controllers_HomeController_tutorial2_invoker = createInvoker(
    HomeController_2.tutorial,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "tutorial",
      Nil,
      "GET",
      this.prefix + """tutorial""",
      """""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_RecipeController_getRecipes3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes")))
  )
  private[this] lazy val controllers_RecipeController_getRecipes3_invoker = createInvoker(
    RecipeController_0.getRecipes(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "getRecipes",
      Nil,
      "GET",
      this.prefix + """recipes""",
      """Recipes""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_RecipeController_createRecipe4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe")))
  )
  private[this] lazy val controllers_RecipeController_createRecipe4_invoker = createInvoker(
    RecipeController_0.createRecipe(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "createRecipe",
      Nil,
      "POST",
      this.prefix + """recipe""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_RecipeController_updateRecipe5_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/"), DynamicPart("recipeId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_updateRecipe5_invoker = createInvoker(
    RecipeController_0.updateRecipe(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "updateRecipe",
      Seq(classOf[Long]),
      "PATCH",
      this.prefix + """recipe/""" + "$" + """recipeId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_RecipeController_deleteRecipe6_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/"), DynamicPart("recipeId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_deleteRecipe6_invoker = createInvoker(
    RecipeController_0.deleteRecipe(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "deleteRecipe",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """recipe/""" + "$" + """recipeId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_Assets_versioned7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned7_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_2.index)
      }
  
    // @LINE:7
    case controllers_HomeController_explore1_route(params@_) =>
      call { 
        controllers_HomeController_explore1_invoker.call(HomeController_2.explore)
      }
  
    // @LINE:8
    case controllers_HomeController_tutorial2_route(params@_) =>
      call { 
        controllers_HomeController_tutorial2_invoker.call(HomeController_2.tutorial)
      }
  
    // @LINE:11
    case controllers_RecipeController_getRecipes3_route(params@_) =>
      call { 
        controllers_RecipeController_getRecipes3_invoker.call(RecipeController_0.getRecipes())
      }
  
    // @LINE:12
    case controllers_RecipeController_createRecipe4_route(params@_) =>
      call { 
        controllers_RecipeController_createRecipe4_invoker.call(RecipeController_0.createRecipe())
      }
  
    // @LINE:13
    case controllers_RecipeController_updateRecipe5_route(params@_) =>
      call(params.fromPath[Long]("recipeId", None)) { (recipeId) =>
        controllers_RecipeController_updateRecipe5_invoker.call(RecipeController_0.updateRecipe(recipeId))
      }
  
    // @LINE:14
    case controllers_RecipeController_deleteRecipe6_route(params@_) =>
      call(params.fromPath[Long]("recipeId", None)) { (recipeId) =>
        controllers_RecipeController_deleteRecipe6_invoker.call(RecipeController_0.deleteRecipe(recipeId))
      }
  
    // @LINE:17
    case controllers_Assets_versioned7_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned7_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
