package invariant.invariantEvaluator

import invariant.invariantEvaluator.Parser.{SimpleNode, InvariantParser,ParseException}
import java.io.ByteArrayInputStream
import invariant.invariantEvaluator.BoolExpr.BoolExpr
import invariant.Invariant
import invariant.invariantEvaluator.VarDecExpr.VarDecExpr
import scala.collection.mutable.HashMap
import invariant.invariantEvaluator.Exceptions._
import invariant.invariantEvaluator.AritmExpr.{AritmInfiniteException, AritmExpr}


class InvariantEvaluator(expression : String,variables:Array[Invariant]){

  var expr:String  = expression
	var parser = new InvariantParser(new ByteArrayInputStream(expr.getBytes));
  var tree:SimpleNode = null;
  try {
    tree = parser.Inv_exp
  }catch{case e:ParseException => throw new InvariantExceptionSyntax(expr,e.toString);}

  var macros:HashMap[String,VarDecExpr]=new HashMap[String,VarDecExpr];

  for (i<-0 to tree.jjtGetNumChildren()-2){
    try{
      var temp = new VarDecExpr(tree.jjtGetChild(i).asInstanceOf[SimpleNode],variables,macros);
      macros.put(temp.variable,temp);
    }
    catch{
      case e:java.util.NoSuchElementException => throw new InvariantExceptionIllegalMacro(tree.jjtGetChild(i).asInstanceOf[SimpleNode].numVar,expr);
      case e:InvariantExceptionIllegalVariable => e.expr=expr;throw e;
      case e:InvariantExceptionIllegalInfiniteVar=> e.expr=expr;throw e;
      case e:InvariantExceptionIllegalMacro      => e.expr=expr;throw e;
    }
  }
  var nexpr:BoolExpr = new BoolExpr(tree.jjtGetChild(tree.jjtGetNumChildren()-1).asInstanceOf[SimpleNode],variables,macros)
  try {
    nexpr.evaluate()
  }catch {
    case e:InvariantExceptionIllegalVariable => e.expr=expr;throw e;
    case e:InvariantExceptionIllegalInfiniteSet => e.expr=expr;throw e;
    case e:InvariantExceptionIllegalMacro      => e.expr=expr;throw e;
  }

  def evaluate():Boolean={
      return nexpr.evaluate();
  }

}
