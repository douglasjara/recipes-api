
package views.xml

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.xml._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object recipe extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[Recipe,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(recipe: Recipe):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.18*/("""
"""),format.raw/*2.1*/("""<?xml version="1.0" encoding="UTF-8"?>
"""),_display_(/*3.2*/_recipe(recipe)))
      }
    }
  }

  def render(recipe:Recipe): play.twirl.api.XmlFormat.Appendable = apply(recipe)

  def f:((Recipe) => play.twirl.api.XmlFormat.Appendable) = (recipe) => apply(recipe)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-02-11T10:47:59.291
                  SOURCE: C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/app/views/recipe.scala.xml
                  HASH: 05b1f607ac25c51e18a7af2b9c0e081b139e1094
                  MATRIX: 943->1|1053->17|1081->19|1147->60
                  LINES: 28->1|33->1|34->2|35->3
                  -- GENERATED --
              */
          