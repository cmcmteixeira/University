package invariant.invariantEvaluator.AritmExpr

import invariant.invariantEvaluator.Parser.InvariantParserConstants

/**
 * Created by Papa Formigas on 25-04-2014.
 */
class AritmAtomOperator(name:Int) extends AritmAtom {
  var operator = name;

  def getType(): Int={return OPR;}
  def evaluate(val1:AritmAtomValue,val2:AritmAtomValue):Double={
    if      (name == InvariantParserConstants.OP_SUM)
    {return val1.evaluate + val2.evaluate;}
    else if (name == InvariantParserConstants.OP_DIFF)
    {return val1.evaluate - val2.evaluate;}
    else if (name == InvariantParserConstants.OP_MUL)
    {return val1.evaluate * val2.evaluate;}
    else if (name == InvariantParserConstants.OP_DIV)
    {return val1.evaluate / val2.evaluate;}
    else if (name == InvariantParserConstants.OP_EXP)
    {return Math.pow(val1.evaluate,val2.evaluate);}
    return 0;
  }
  def evaluate(val1:Double,val2:Double):Double={
    if      (name == InvariantParserConstants.OP_SUM)
    {return val1 + val2;}
    else if (name == InvariantParserConstants.OP_DIFF)
    {return val1 - val2;}
    else if (name == InvariantParserConstants.OP_MUL)
    {return val1 * val2;}
    else if (name == InvariantParserConstants.OP_DIV)
    {return val1 / val2;}
    else if (name == InvariantParserConstants.OP_EXP)
    {return Math.pow(val1,val2);}
    return 0;
  }
}
