import language.experimental.macros
import scala.reflect.macros.blackbox.Context

object Main {

  def printAsAST[T](x: T): Unit = macro printAsASTImpl[T]

  def printAsASTImpl[T: c.WeakTypeTag](c: Context)(x: c.Expr[T]): c.Tree = {
    import c.universe._

    val str = reify(x.splice).tree
    val ast = showRaw(str)
    q"""print($str + " => " + $ast)"""
  }

}
