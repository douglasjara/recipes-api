// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/douglas.jara/Documents/personal/master/2. server side/2. rest play/Tareas/recipes/conf/routes
// @DATE:Tue Feb 11 10:47:58 COT 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
