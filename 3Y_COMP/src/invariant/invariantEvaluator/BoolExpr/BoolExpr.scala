package invariant.invariantEvaluator.BoolExpr


import invariant.invariantEvaluator.BoolExpr.{BoolAtomValue, BoolAtomOperator, BoolAtom}
import invariant.invariantEvaluator.Parser.{InvariantParserConstants, InvariantParserTreeConstants, SimpleNode}
import java.util.Vector
import java.util
import invariant.Invariant
import scala.collection.mutable.HashMap
import invariant.invariantEvaluator.VarDecExpr.VarDecExpr
;


class BoolExpr(rootNode:SimpleNode,variables:Array[Invariant],macros:HashMap[String,VarDecExpr]) extends InvariantParserConstants{
  var currentNode: SimpleNode = rootNode;

  var expr: Vector[BoolAtom] = new Vector[BoolAtom]();
  moveDown(currentNode);
  var a = 2;
  def evaluate(): Boolean = {
    var stack: util.Stack[Boolean] = new util.Stack[Boolean];

    for (i <- 0 to expr.size - 1) {
      if (expr.elementAt(i).isInstanceOf[BoolAtomOperator]) {
        val second = stack.pop;
        val first = stack.pop;
        stack push expr.elementAt(i).asInstanceOf[BoolAtomOperator].evaluate(first, second);
      }
      else {
        stack.push(expr.elementAt(i).asInstanceOf[BoolAtomValue].evaluate);
      }
    }
    return stack.pop()
  }

  def moveDown(node: SimpleNode) {

    if      (node.getID == InvariantParserTreeConstants.JJTOREXPR  ) {moveDownOrExpr(node); }
    else if (node.getID == InvariantParserTreeConstants.JJTANDEXPR ) {moveDownAndExpr(node);}
    else if (node.getID == InvariantParserTreeConstants.JJTOREXPR_ ) {moveDownOr_Expr(node);}
    else if (node.getID == InvariantParserTreeConstants.JJTANDEXPR_) {moveDownAnd_Expr(node);}
    else if (node.getID == InvariantParserTreeConstants.JJTATOMINV ) {moveDownAtomExpr(node);}

  }

  def moveDownOrExpr(node: SimpleNode) {
    if (node.jjtGetNumChildren() == 0) {/*Erro*/}


    moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode])
    if ( node.jjtGetNumChildren() == 2) {
      moveDown(node.jjtGetChild(1).asInstanceOf[SimpleNode])
    }
  }
  def moveDownOr_Expr(node: SimpleNode) {
    if (node.jjtGetNumChildren() == 0) {/*Erro*/}

    moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode])
    expr.add(new BoolAtomOperator(InvariantParserTreeConstants.JJTOREXPR));
    if (node.jjtGetNumChildren() > 1){
      moveDown(node.jjtGetChild(1).asInstanceOf[SimpleNode])
    }
  }

  def moveDownAndExpr(node: SimpleNode) {
    if (node.jjtGetNumChildren() == 0) {/*Erro*/}

    moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode])
    if (node.jjtGetNumChildren() > 1 ) {
      moveDown(node.jjtGetChild(1).asInstanceOf[SimpleNode])
    }
  }
  def moveDownAnd_Expr(node: SimpleNode) {
    if (node.jjtGetNumChildren() == 0) {/*Erro*/}

    moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode])
    expr.add(new BoolAtomOperator(InvariantParserTreeConstants.JJTANDEXPR));
    if (node.jjtGetNumChildren() > 1){
      moveDown(node.jjtGetChild(1).asInstanceOf[SimpleNode])
    }
  }

  def moveDownAtomExpr(node:SimpleNode){
    if (node.operators != null && node.operators.size() >= 0) {
      var negate=false;
      if (node.operators.get(0)== InvariantParserConstants.NOTEQUAL){
         negate = true;
      }
      expr.add(new BoolAtomValue(node.numVar,negate,node.localVar, node.jjtGetChild(0).asInstanceOf[SimpleNode], variables,macros));
    }
    else {
      moveDown(node.jjtGetChild(0).asInstanceOf[SimpleNode]);
    }
  }

}