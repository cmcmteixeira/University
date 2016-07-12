package invariant.invariantEvaluator.Exceptions

import java.io.{PrintWriter, PrintStream}


class InvariantExceptionSyntax(expr:String,description:String) extends InvariantException {
  override def printStackTrace(){
    println(expr);
    println(description);
    println(super.printStackTrace)
  }
  override def printStackTrace(s:PrintStream){
    s.print("\nInvariant Syntax Exception:\n");
    s.print("Expression provided: \n"+expr)
    s.print("\n");
    s.print(description+"\n");

  }
  override def printStackTrace(s:PrintWriter){
    s.print("\nInvariant Syntax Exception:\n");
    s.print("Expression provided: \n"+expr)
    s.print("\n");
    s.print(description+"\n");
  }

}
