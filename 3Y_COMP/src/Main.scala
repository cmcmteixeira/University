import invariant._
import invariant.invariantEvaluator.Exceptions._
import java.util.Scanner

object Main {
  def test(){
    var x1:InvariantDouble = new InvariantDouble(2);
    var x2:InvariantDouble = new InvariantDouble(2);
    var x3:InvariantDouble = new InvariantDouble(2);
    var x4:InvariantDouble = new InvariantDouble(2);
    var x5:InvariantDouble = new InvariantDouble(2);
    var x6:InvariantDouble = new InvariantDouble(2);
    /*Definicao com varios () nas operacoes aritmeticas*/
    x1.restrict("" +
      "var a = $1;" +
      "var b = $2;" +
      "$0=]@inf,( ((2014^b)+2)-((2014^b)+2))]",x1,x2,x3,x4,x5,x6);
     println("1-Esperado - Sem excecoes");
    /*Utilizacao de @inf e de () nos conjuntos*/
    x2.restrict("" +
      "var c = 2;" +
      "var b = 0;" +
      "($0=[-2,10] && $0=]@inf,2] ) && $0 = {-1,0,1}");
     println("2-Esperado - Sem excecoes");
    /*Utilizacao de () em conjuntos e exp. aritm.*/
    x3.restrict("" +
      "var c = 2;" +
      "var b = 0;" +
      "($0=[-2,10] && $0=]@inf,((2014^b)*22)-((2014^b)*14)] ) && $0 = {-1,0,1}");
    /*Expressao de avaliacao nao fornecida*/
    try {
      x3.restrict("" +
        "var a = $1;" +
        "var b = $2;")
    }catch{
      case e:InvariantExceptionSyntax => println("3-Esperado-Falta expressao de avaliacao");
    }
    /*() nao fechados corretamente*/
    try {
      x3.restrict("" +
        "var c = (12+$0+(40-12)" +
        "$0=[1,2]"
      );
    }catch{
      case e:InvariantExceptionSyntax => println("4-Esperado - nao fechou ");
    }
    /*() macro nao foi definida*/
    try {
      x3.restrict("" +
        "var c = (12+$0+(40-12));" +
        "$0=[1,$0]"
      );
    }catch{
      case e:InvariantExceptionIllegalMacro => println("5-Esperado - macro nao definida ");
    }
    /*Variavel $ nao defininida*/
    try {
      x3.restrict("" +
        "var c = (12+$1+(40-12));" +
        "$3=[1,d]"
      );
    }catch{
      case e:InvariantExceptionIllegalVariable => println("5-Esperado - var nao definida ");
    }
    /*Macro nao pode ser infinito*/
    try {
      x3.restrict("" +
        "var c = @inf;" +
        "$0=[1,d]"
      );
    }catch{
      case e:InvariantExceptionIllegalInfiniteVar => println("6-Esperado - Infinito usado incorretaomente ");
    }
    try {
      x3.restrict("" +
        "var c =2;" +
        "$0={@inf,2}"
      );
    }catch{
      case e:InvariantExceptionIllegalInfiniteSet=> println("7-Esperado - Infinito usado incorretaomente ");
    }

    x1 is 0;
    x1 is -1;
    /*Invariante violada*/
    try {
      x1 is 0.1
    }catch{
      case e:InvariantExceptionRestr => println("8-Esperado - Invariante violada");
    }









  }
  def main(args: Array[String]) {

    test();
}
}