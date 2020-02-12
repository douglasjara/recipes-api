
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

object pagedRecipes extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[PagedRecipes,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(recipes: PagedRecipes):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.25*/("""
"""),format.raw/*2.1*/("""<?xml version="1.0" encoding="UTF-8"?>
<page>"""),_display_(/*3.8*/recipes/*3.15*/.getPage()),format.raw/*3.25*/("""</page>
<rows>"""),_display_(/*4.8*/recipes/*4.15*/.getRows()),format.raw/*4.25*/("""</rows>
<total>"""),_display_(/*5.9*/recipes/*5.16*/.getTotal()),format.raw/*5.27*/("""</total>
<recipes>
        """),_display_(/*7.10*/for(recipe <- recipes.getRecipes()) yield /*7.45*/ {_display_(Seq[Any](format.raw/*7.47*/("""
                """),_display_(/*8.18*/_recipe(recipe)),format.raw/*8.33*/("""
        """)))}),format.raw/*9.10*/("""
"""),format.raw/*10.1*/("""</recipes>"""))
      }
    }
  }

  def render(recipes:PagedRecipes): play.twirl.api.XmlFormat.Appendable = apply(recipes)

  def f:((PagedRecipes) => play.twirl.api.XmlFormat.Appendable) = (recipes) => apply(recipes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-02-11T23:32:43.560
                  SOURCE: C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/app/views/pagedRecipes.scala.xml
                  HASH: b3d3fbe9828eb9cafee617bb01ce035069c4f4c0
                  MATRIX: 955->1|1072->24|1100->26|1172->73|1187->80|1217->90|1258->106|1273->113|1303->123|1345->140|1360->147|1391->158|1447->188|1497->223|1536->225|1581->244|1616->259|1657->270|1686->272
                  LINES: 28->1|33->1|34->2|35->3|35->3|35->3|36->4|36->4|36->4|37->5|37->5|37->5|39->7|39->7|39->7|40->8|40->8|41->9|42->10
                  -- GENERATED --
              */
          