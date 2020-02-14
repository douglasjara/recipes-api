// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/conf/routes
// @DATE:Thu Feb 13 19:58:46 COT 2020

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:11
  class ReverseRecipeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def getRecipes: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.getRecipes",
      """
        function(page0,maxRows1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[Int]].javascriptUnbind + """)("page", page0), (""" + implicitly[play.api.mvc.QueryStringBindable[Int]].javascriptUnbind + """)("maxRows", maxRows1)])})
        }
      """
    )
  
    // @LINE:12
    def createRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.createRecipe",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe"})
        }
      """
    )
  
    // @LINE:13
    def updateRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.updateRecipe",
      """
        function(recipeId0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("recipeId", recipeId0))})
        }
      """
    )
  
    // @LINE:14
    def deleteRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.deleteRecipe",
      """
        function(recipeId0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("recipeId", recipeId0))})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:8
    def tutorial: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.tutorial",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tutorial"})
        }
      """
    )
  
    // @LINE:7
    def explore: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.explore",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "explore"})
        }
      """
    )
  
  }

  // @LINE:17
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
