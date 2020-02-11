
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

object _recipe extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[Recipe,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(recipe: Recipe):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.18*/("""
"""),format.raw/*2.1*/("""<recipe>
    <title>"""),_display_(/*3.13*/recipe/*3.19*/.title),format.raw/*3.25*/("""</title>
    <estimatedTime>"""),_display_(/*4.21*/recipe/*4.27*/.estimatedTime),format.raw/*4.41*/("""</estimatedTime>
    <imageUrl>"""),_display_(/*5.16*/recipe/*5.22*/.imageUrl),format.raw/*5.31*/("""</imageUrl>
    <howToMake>"""),_display_(/*6.17*/recipe/*6.23*/.howToMake),format.raw/*6.33*/("""</howToMake>
    """),_display_(/*7.6*/_ingredients(recipe.ingredients)),format.raw/*7.38*/("""
"""),format.raw/*8.1*/("""</recipe>"""))
      }
    }
  }

  def render(recipe:Recipe): play.twirl.api.XmlFormat.Appendable = apply(recipe)

  def f:((Recipe) => play.twirl.api.XmlFormat.Appendable) = (recipe) => apply(recipe)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-02-11T10:47:59.360
                  SOURCE: C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/app/views/_recipe.scala.xml
                  HASH: a3c3b3c75738570368aea72be8ed4637e254acda
                  MATRIX: 944->1|1054->17|1082->19|1130->41|1144->47|1170->53|1226->83|1240->89|1274->103|1333->136|1347->142|1376->151|1431->180|1445->186|1475->196|1519->215|1571->247|1599->249
                  LINES: 28->1|33->1|34->2|35->3|35->3|35->3|36->4|36->4|36->4|37->5|37->5|37->5|38->6|38->6|38->6|39->7|39->7|40->8
                  -- GENERATED --
              */
          