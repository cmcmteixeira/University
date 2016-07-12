package invariant.invariantEvaluator.BoolExpr

import invariant.invariantEvaluator.RangeOrSet.RangeOrSet
import invariant.invariantEvaluator.Parser.SimpleNode
import invariant.Invariant
import scala.collection.mutable.HashMap
import invariant.invariantEvaluator.VarDecExpr.VarDecExpr
import invariant.invariantEvaluator.Exceptions.InvariantExceptionIllegalMacro


class BoolAtomValue(strVar:String,neg:Boolean,localVar:Boolean,ros:SimpleNode,variables:Array[Invariant],macros:HashMap[String,VarDecExpr]) extends BoolAtom{

  var rangeOrSet:RangeOrSet = new RangeOrSet(ros,variables,macros)
  var negate:Boolean = neg;
  var variableString:String = strVar;
  var index = 0;
  if (!localVar) {
    variableString = variableString.replace("$", "");
    index = variableString.toInt;
  }



  def getType(): Int={return VAL;}

  def evaluate:Boolean={

    var evalVariable:Double = variables(index).getValue();
    try {
      if (localVar) {
        evalVariable = macros.get(variableString).get.evaluate;
      }
    }catch{
      case e:NoSuchElementException => throw new InvariantExceptionIllegalMacro(variableString,"");
    }
    if (negate){return !rangeOrSet.evaluate(evalVariable);}
    return rangeOrSet.evaluate(evalVariable)
  }
}
