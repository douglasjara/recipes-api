
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

object recipes extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[List[Recipe],play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(recipes: List[Recipe]):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.25*/("""
"""),format.raw/*2.1*/("""<?xml version="1.0" encoding="UTF-8"?>
<recipes>
    """),_display_(/*4.6*/for(recipe <- recipes) yield /*4.28*/ {_display_(Seq[Any](format.raw/*4.30*/("""
        """),_display_(/*5.10*/_recipe(recipe)),format.raw/*5.25*/("""
    """)))}),format.raw/*6.6*/("""
"""),format.raw/*7.1*/("""</recipes>"""))
      }
    }
  }

  def render(recipes:List[Recipe]): play.twirl.api.XmlFormat.Appendable = apply(recipes)

  def f:((List[Recipe]) => play.twirl.api.XmlFormat.Appendable) = (recipes) => apply(recipes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-02-11T10:47:59.308
                  SOURCE: C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/app/views/recipes.scala.xml
                  HASH: 839a56d1600297213dd76224461f2d4ea8be89a2
                  MATRIX: 950->1|1067->24|1095->26|1176->82|1213->104|1252->106|1289->117|1324->132|1360->139|1388->141
                  LINES: 28->1|33->1|34->2|36->4|36->4|36->4|37->5|37->5|38->6|39->7
                  -- GENERATED --
              */
          