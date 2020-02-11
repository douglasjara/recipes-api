
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

object _ingredients extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[List[Ingredient],play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(ingredients: List[Ingredient]):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.33*/("""
"""),format.raw/*2.1*/("""<ingredients>
    """),_display_(/*3.6*/for(ingredient <- ingredients) yield /*3.36*/ {_display_(Seq[Any](format.raw/*3.38*/("""
        """),_display_(/*4.10*/_ingredient(ingredient)),format.raw/*4.33*/("""
    """)))}),format.raw/*5.6*/("""
"""),format.raw/*6.1*/("""</ingredients>"""))
      }
    }
  }

  def render(ingredients:List[Ingredient]): play.twirl.api.XmlFormat.Appendable = apply(ingredients)

  def f:((List[Ingredient]) => play.twirl.api.XmlFormat.Appendable) = (ingredients) => apply(ingredients)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-02-11T10:47:59.347
                  SOURCE: C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/app/views/_ingredients.scala.xml
                  HASH: 4b227cc46aa8e9ae02c783fc5536212c01fb1fba
                  MATRIX: 959->1|1084->32|1112->34|1157->54|1202->84|1241->86|1278->97|1321->120|1357->127|1385->129
                  LINES: 28->1|33->1|34->2|35->3|35->3|35->3|36->4|36->4|37->5|38->6
                  -- GENERATED --
              */
          