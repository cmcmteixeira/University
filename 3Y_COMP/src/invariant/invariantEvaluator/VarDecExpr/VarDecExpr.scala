package invariant.invariantEvaluator.VarDecExpr

import invariant.invariantEvaluator.Parser.SimpleNode
import invariant.Invariant
import invariant.invariantEvaluator.AritmExpr.{AritmInfiniteException, AritmExpr}
import scala.collection.mutable.HashMap
import invariant.invariantEvaluator.Exceptions.InvariantExceptionIllegalInfiniteVar


class VarDecExpr(node: SimpleNode, variables: Array[Invariant], macros: HashMap[String, VarDecExpr]) {
  var variable: String = node.numVar;
  var aritm: AritmExpr = new AritmExpr(node.jjtGetChild(0).asInstanceOf[SimpleNode], variables, macros);
  evaluate;

  def evaluate: Double = {
    try {
      aritm.evaluate();
    } catch {
      case e:AritmInfiniteException => throw new InvariantExceptionIllegalInfiniteVar();
    }
  }



}
