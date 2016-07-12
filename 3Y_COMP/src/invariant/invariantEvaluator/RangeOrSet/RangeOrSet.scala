package invariant.invariantEvaluator.RangeOrSet

import invariant.invariantEvaluator.Parser.{InvariantParserConstants, InvariantParserTreeConstants, SimpleNode}
import java.util.Vector
import invariant.invariantEvaluator.AritmExpr.{AritmInfiniteException, AritmExpr}
import invariant.Invariant
import scala.collection.mutable.HashMap
import invariant.invariantEvaluator.VarDecExpr.VarDecExpr
import invariant.invariantEvaluator.Exceptions.InvariantExceptionIllegalInfiniteSet

class RangeOrSet(rootNode:SimpleNode,variables:Array[Invariant],macros:HashMap[String,VarDecExpr]) extends InvariantParserConstants with InvariantParserTreeConstants {

  var currentNode : SimpleNode = rootNode

  val isRange:Boolean = currentNode.getID == InvariantParserTreeConstants.JJTRANGE
  val isSet:  Boolean = currentNode.getID == InvariantParserTreeConstants.JJTSET

  var aritms:Vector[AritmExpr] = new Vector[AritmExpr](0);
  for (i <- 0 to currentNode.jjtGetNumChildren()-1) {
    aritms.add(new AritmExpr(currentNode.jjtGetChild(i).asInstanceOf[SimpleNode],variables,macros));
  }

  def evaluate(newValue : Double):Boolean= {
    if (isRange) {
      return evaluateRange(newValue)
    } else{
      return evaluateSet(newValue)
    }
  }

  def evaluateRange(newValue: Double):Boolean = {
    var a:Boolean=true;
    try {
      a = newValue > aritms.elementAt(0).evaluate()
      if (currentNode.operators.get(0).equals(InvariantParserConstants.REC_BRAC_O)) {
        a = newValue >= aritms.elementAt(0).evaluate()
      }
    }catch{case e:AritmInfiniteException => a=true;}

    var b:Boolean=true;
    try {
      b = newValue < aritms.elementAt(1).evaluate()
      if (currentNode.operators.get(1).equals(InvariantParserConstants.REC_BRAC_C)) {
        b = newValue <= aritms.elementAt(1).evaluate()
      }
    }catch{case e:AritmInfiniteException => b=true;}
    return a && b
  }

  def evaluateSet(newvalue:Double):Boolean = {
    var bool = false;
    try {
      for (i <- 0 to aritms.size() - 1) {
        if (newvalue == aritms.get(i).evaluate()) {
          bool= true;
        }
      }
      return bool;
    }catch{
      case e:AritmInfiniteException => throw new InvariantExceptionIllegalInfiniteSet();
    }
  }
}
