import scala.quoted._

object Companion extends Trait
trait Trait {
  def fun(first: String): String = "anything"
}

object Macro {
  inline def mac(inline tree: String): String = ${ macImpl('tree) }
  def macImpl(tree: Expr[String])(using qctx: QuoteContext): Expr[String] = {
    tree match {
        case vv @ '{ ($s: Trait).fun($arg) } => arg
        case _ => Expr("not matched")
    }
  }
}