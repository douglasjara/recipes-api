
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

object _ingredient extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[Ingredient,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(ingredient: Ingredient):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.26*/("""
"""),format.raw/*2.1*/("""<ingredient>
    <name>"""),_display_(/*3.12*/ingredient/*3.22*/.name),format.raw/*3.27*/("""</name>
</ingredient>"""))
      }
    }
  }

  def render(ingredient:Ingredient): play.twirl.api.XmlFormat.Appendable = apply(ingredient)

  def f:((Ingredient) => play.twirl.api.XmlFormat.Appendable) = (ingredient) => apply(ingredient)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-02-11T10:47:59.336
                  SOURCE: C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/app/views/_ingredient.scala.xml
                  HASH: 189411789afbac8bdbb92c14a75d0a8689deebfd
                  MATRIX: 952->1|1070->25|1098->27|1149->52|1167->62|1192->67
                  LINES: 28->1|33->1|34->2|35->3|35->3|35->3
                  -- GENERATED --
              */
          