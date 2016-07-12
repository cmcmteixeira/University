package invariant.invariantEvaluator.Exceptions

import java.io.{PrintWriter, PrintStream}


class InvariantExceptionIllegalMacro(variable:String,expression:String) extends InvariantException{
  var expr = expression;
  override def printStackTrace(){
    println("\nInvariant Illegal Argument Exception:\n");
    println("\nVariable :\" "+variable + "\" is being used withou being initialized\n");
    println("Expression provided: \n"+expr)
    println("\n");
    super.printStackTrace();
  }
  override def printStackTrace(s:PrintStream){
    s.print("\nInvariant Illegal Argument Exception:\n");
    s.print("\nVariable :\""+variable + "\" is being used withou being initialized\n");
    s.print("Expression provided: \n"+expr)
    s.print("\n");
    super.printStackTrace(s);
  }
  override def printStackTrace(s:PrintWriter) {
    s.print("\nInvariant Illegal Argument Exception:\n");
    s.print("\nVariable :\" "+variable + "\" is being used withou being initialized\n");
    s.print("Expression provided: \n" + expr)
    s.print("\n");
    super.printStackTrace(s);
  }
}
