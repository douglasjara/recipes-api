
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

object errorResponse extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[ErrorResponse,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(errorResponse: ErrorResponse):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.32*/("""
"""),format.raw/*2.1*/("""<?xml version="1.0" encoding="UTF-8"?>
<response>
    <error>"""),_display_(/*4.13*/errorResponse/*4.26*/.error),format.raw/*4.32*/("""</error>
</response>"""))
      }
    }
  }

  def render(errorResponse:ErrorResponse): play.twirl.api.XmlFormat.Appendable = apply(errorResponse)

  def f:((ErrorResponse) => play.twirl.api.XmlFormat.Appendable) = (errorResponse) => apply(errorResponse)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-02-11T10:47:59.200
                  SOURCE: C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/app/views/errorResponse.scala.xml
                  HASH: c383f1ee0f55e350c6f3b385215628f4f6d772eb
                  MATRIX: 957->1|1081->31|1109->33|1199->97|1220->110|1246->116
                  LINES: 28->1|33->1|34->2|36->4|36->4|36->4
                  -- GENERATED --
              */
          