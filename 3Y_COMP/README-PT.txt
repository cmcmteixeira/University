//aaaa
**PROJECT TITLE: #12 Scala Invariants 

**GROUP: T01G02

(Names, numbers, self assessment, and contribution of the members of the group according to:)

NAME1: André Duarte, 	NR1: 201100766,  GRADE1: 19.5, CONTRIBUTION: 25%

NAME2: Carlos Teixeira, NR2: 201107928 , GRADE2: 19.5, CONTRIBUTION: 25%

NAME3: Leonel Araújo, 	NR3: 201100640,  GRADE3: 19.5, CONTRIBUTION: 25%

NAME4: Sérgio Esteves, 	NR4: 201100784,  GRADE4: 19.5, CONTRIBUTION: 25%


** SUMMARY:
O nosso projeto implementa uma ferramenta que extende a linguagem Scala para que esta suporte invariantes. Invariantes são variáveis sobre as quais são aplicadas restrições ao valor que representa,isto é, consoante a vontade do utilizador,este poderá restringir uma determinada variável impedindo que ela tome valores indesejáveis em determinado contexto programático.
Quando a invariante é violada é lançada a respectiva exceção. 
	Ex:var abc = 2+$1;  $0 = ]2,100] && a={4+4^2,5*a,6-4}

**DEALING WITH SYNTACTIC ERRORS: (Describe how the syntactic error recovery of your tool does work. Does it exit after the first error?)
Os erros sintácticos são tratados pelo javacc. A ferramenta, quando deteta esta informação, lança a sua própria exceção indicando o erro.Esta exceção é apanhada pela class InvariantEvaluator a qual cria uma nova excepção indicando o erro obtido.

**SEMANTIC ANALYSIS: (Refer the possible semantic rules implemented by your tool.)
A ferramenta 



**INTERMEDIATE REPRESENTATIONS: A representação de alto nível é criada pelo javacc. A árvore criada pode ser dividida em 3 níveis. O primeiro nível possui a declaração das variáveis, o segundo relações entre conjuntos e intervalos e o terceiro é composto pelas expressões aritméticas.

  Esquema simplificado:
  	Invariant
  	/	|	\
 Decl  Decl  InvConj			---> Nivel 1
 			/	|	\
 		Conj  Inter  Conj 		---> Nivel 2
 		 |		|	   |
 	ExpAritm ExpAritm ExpArimt 	---> Nivel 3
  
  Exemplo da árvore para a expressão :
  	var abc = 2+3+$1;  $0 = ]2,100] && a!={4+4^2,5*a,6-4}

  					InvariantExpr
  			/								\
  		VAR_DECL  					  	OR_Expr
  		   /  \								|
  		 name Aritm 				 	 AND_Expr
		 		 | 						/			\
  		 	   SumExpr 			      Range  		Set
  		 	   /  \  				    /\		 	/	|	\
  		 	 atom  SumExpr_ 	   Aritm  Aritm  Aritm Aritm Aritm
  		 	 /     /\				|		|		|	|		|
  		 	2   atom SumExpr_		...		...		...	...	...	...
  		 		  |		|
  		 		  3	   atom
  		 		  		|
  		 		  		$1

**CODE GENERATION:
Começando pela raíz o processo desloca-se no sentido descendente de forma até encontrar uma folha do tipo "atom".A partir dessa folha o algoritmo desloca-se agora no sentido ascendente guardando o valor das folhas até encontrar um nó com mais do que um filho.Nestes nós (com mais do que um filho) o algoritmo explora os restantes filhos guardando para cada um deles o seu valor em ReversePolishNotation. Este processo repete-se até ser encontrado um nó do tipo Range ou Set. Estes nós passam agora a comportar-se como folhas e o processo é repetido até ser alcançada a raíz da árvore.
	Ex dp para a expressão:
	var abc = 2+3+$1;  $0 = ]@inf,100] && a!={4+4^2,5*a}

			Invariant [Range		,	    Set 	,&&]
						   /\		       /	\ 
					 [@inf] [100]	[4,2,^,4+][5,a,*]





**OVERVIEW:
A estratégia utilizada para percorrer a àrvore foi uma estratégia recursiva.
No que toca à avaliação das expressões (aritméticas e boleanas) foi utilizada a Reverse Polish Notation e para avaliar as expressões neste formato foi usado uma pilha a qual vai guardando os resultados intermédios do algoritmo.


**TESTSUITE AND TEST INFRASTRUCTURE: (Describe the content of your testsuite regarding the number of examples, the approach to automate the test, etc.)
Os testes utilizados passaram pela criação de várias expressões as quais foram corridas e os resultados analisados. 

**TASK DISTRIBUTION: (Identify the set of tasks done by each member of the project.)
Geração de código 	  		-> Carlos + Leonel
Definição da gramática		-> Sérgio + André
Controlo de erros   + testes-> Sérgio + André + Carlos + Leonel

**PROS:
Permite restringir variáveis a determinados valores escolhidos pelo utilizador.
Apresenta ao utilizador que restrição infringiu.

**CONS:
Requer tratamento de exceções nas operações com invariantes.