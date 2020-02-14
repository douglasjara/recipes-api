// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/conf/routes
// @DATE:Thu Feb 13 19:58:46 COT 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:11
  class ReverseRecipeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def getRecipes(page:Int, maxRows:Int): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[Int]].unbind("page", page)), Some(implicitly[play.api.mvc.QueryStringBindable[Int]].unbind("maxRows", maxRows)))))
    }
  
    // @LINE:12
    def createRecipe(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "recipe")
    }
  
    // @LINE:13
    def updateRecipe(recipeId:Long): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "recipe/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("recipeId", recipeId)))
    }
  
    // @LINE:14
    def deleteRecipe(recipeId:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "recipe/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("recipeId", recipeId)))
    }
  
  }

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:8
    def tutorial(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "tutorial")
    }
  
    // @LINE:7
    def explore(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "explore")
    }
  
  }

  // @LINE:17
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:17
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
