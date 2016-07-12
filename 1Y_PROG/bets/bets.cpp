// falta actualizar prize_key
// falta lidar com erros de entrada em que o utilizador insira mais do que 7 numeros
/*
main() -> chama as funcoes
in_num_jogador -> recebe o num de jogador e verifica se este existe
reg_bet_jogador -> recebe a apostas e guarda a num vector (chama direta ou indiretamente todas as funcoes acima dela)
aposta_automatica -> recebe um vector por referencia ao qual adiciona uma aposta aleatoria
verifica_apostas  -> verifica a validade das apostas manuais
out_apostas -> exporta para o ficheiro bets.txt as apostas
actualiza_saldo -> decrementa o saldo do jogador
str_int_saldo_players -> retorna  o saldo de um linha do ficheiro ("converte" a string para double)
str_int_num_player -> retorna o numero do jogador ("converte" a string para inteiro)

*/


#include "bets.h"
using namespace std;
void actualiza_prize_key(int valor)
{
	string word;
	ifstream myfile;
	myfile.open("prize_key.txt");
	if (!myfile.is_open()) {
		ofstream myfile2;
		myfile2.open ("prize_key.txt");
		myfile2 << "Montante total de apostas: " << valor << endl;
		myfile2.close();
		return;}
	myfile >> word >> word >> word >> word >> word;
	int montante_inicial = atoi (word.c_str());
	myfile.close();
	ofstream myfile3;
	myfile3.open ("prize_key.txt");
	myfile3 << "Montante total de apostas: " << montante_inicial + valor << endl;
	myfile3.close();

}
void bubblesort(int a[], int n) // ordena array de n elementos
{
	for(int j=0; j<n; j++){
		for(int i=0; i<n-1; i++){
			if(a[i+1] < a[i])
				swap(a[i+1], a[i]);
		}
	}
}
int str_int_num_player (string linha) // funcao de conversao da linha do ficheiro num inteiro
{
	if (linha.size() < 7) {return 0;}
	int i=6; // contador de iteracoes
	double a;

	int numero=0;
	while (i > 0)
	{  
		a = linha[i] - '0'; // converte o caracter para um numero
		numero =numero + a * pow (10.0 , 6-i);
		i--;
	}
	return numero;
}
double str_int_saldo_players(string linha)// funcao de conversao do saldo de string para inteiro
{
	int i=41; // a funcao "varre" a linha da esquerda para a direita o 42ª carcter e o ultimo caracter
	char a;// caracter analisado
	double b; // o caracter a e convertido num inteiro e guardado em b, esta variavel e depois convertido numa de tipo double
	double expoente= 0.01;
	double numero=0.0;
	while (i > 27)
	{
		a = linha[i];
		if ( a == '.' ) // garante que o '.' nao e considerado
		{i--;continue; }
		if ( a == ' ') // quando o caracter for um espaco o nr foi lido na totalidade
		{break;}
		b= a - '0';
		b=b*1.0;
		numero = numero + b * expoente;
		i--;
		expoente = expoente*10.0;
	}
	return numero;
} 
void actualiza_saldo (int jogador,int apostas)
{
	string line;
	string word;
	string codigojogador;
	int codigojogadorint;
	double saldo;
	ifstream myfile;
	myfile.open ("players.txt");
	ofstream myfile2;
	myfile2.open ("players_tmp.txt");
	if (myfile.is_open() && myfile2.is_open ())
	{
		getline (myfile, line);
		myfile2 << line << endl;
		while(!myfile.eof())
		{
			getline (myfile,line);
			codigojogador = line;
			codigojogador.resize (7);
			codigojogadorint = atoi (codigojogador.c_str());// converter string c++ para inteiro
			if (codigojogadorint == jogador)
			{
				saldo = str_int_saldo_players(line);  
				saldo = saldo - 1.0*apostas;
				line.resize (29);
				myfile2 << line;
				myfile2.setf(ios::fixed,ios::floatfield);
				myfile2.precision(2);
				myfile2 << setw (13) << right << saldo << endl;
			}
			else myfile2 << line << endl;
		}
		myfile2.close();
		myfile.close();
	}
	char oldname[] ="players_tmp.txt";
	char newname[] ="players.txt";
	remove(newname);
	rename(oldname,newname);
	return ;
}
bool verifica_apostas (int n1, int n2,int n3,int n4,int n5,int e1,int e2)
{
	if (n1 == n2 || n1 == n3 || n1 == n4 || n1 == n5 || n2 == n3 || n2 == n4 || n2== n5 || n3 == n4 || n3 == n5 || n4 == n5 || e1 == e2) // entrada de dados com numeros repetidos
	{return true;}
	if ( n1 > 50 || n1 < 0 || n2 > 50 || n2 < 0 || n3 > 50 || n3 < 0 ||  n4 > 50 || n4 < 0 ||  n5 > 50 || n5 < 0 ||  e1 > 11 || e1 < 0 ||  e2 > 11 || e2 < 0)
	{return true;}
	else
	{return false;}
}
void out_apostas(vector<int> apostas,int jogador)
{
	int i=0;
	int size;
	int n1,n2,n3,n4,n5,e1,e2;
	size = apostas.size();
	ofstream bets;
	bets.open("bets.txt",ios::app);
	if (bets.is_open())
	{

		bets  << endl << right << setw(7) <<setfill('0') << jogador << endl;
		while ( i < size )
		{
			bets <<right << setw(2) << setfill(' ') << apostas[i] << " ";
			bets <<right << setw(2) << apostas[i+1] << " ";
			bets <<right << setw(2) << apostas[i+2] << " ";
			bets <<right << setw(2) << apostas[i+3] << " ";
			bets <<right << setw(2) << apostas[i+4] << " ";
			bets <<setw(1)<< "- ";
			bets <<right << setw(2) << apostas[i+5] << " ";
			bets <<right << setw(2) << apostas[i+6] << " ";
			bets << endl;
			i= i+7;
		}
	}
	bets.close();

}
void aposta_automatica (vector<int>& apostas)
{
	int n1,n2,n3,n4,n5,e1,e2;	
	n1 = rand() % 50 + 1;
	do{n2 = rand() % 50 + 1;}while (n2 == n1);
	do{n3 = rand() % 50 + 1;}while (n3 == n1 || n3 == n2);
	do{n4 = rand() % 50 + 1;}while (n4 == n1 || n4 == n2 || n4 == n3);
	do{n5 = rand() % 50 + 1;}while (n5 == n1 || n5 == n2 || n5 == n3 || n5 == n4);
	e1 = rand() % 11 + 1;
	do{e2 = rand() % 11 + 1;}while(e2==e1);

	int array_apostas_numeros[]={n1, n2, n3, n4, n5};
	bubblesort (array_apostas_numeros, 5);

	apostas.push_back(array_apostas_numeros[0]);
	apostas.push_back(array_apostas_numeros[1]);
	apostas.push_back(array_apostas_numeros[2]);
	apostas.push_back(array_apostas_numeros[3]);
	apostas.push_back(array_apostas_numeros[4]);

	int array_apostas_estrelas[] = {e1, e2};
	bubblesort (array_apostas_estrelas, 2);

	apostas.push_back(array_apostas_estrelas[0]);
	apostas.push_back(array_apostas_estrelas[1]);



}
void reg_bet_jogador (int jogador , double saldo)
{

	int n_apostas;
	int n1,n2,n3,n4,n5,e1,e2;
	vector <int> apostas;
	cout << "Saldo : " << saldo << endl ;
	cout << "Jogador : " << jogador <<endl;
	cout << "Indique o numero de apostas automaticas que pretende registar: " << endl;
	cin >> n_apostas;
	// tratamento das apostas automaticas
	while (cin.fail() || n_apostas < 0){cin.clear();cin.ignore(300,'\n');cout << "Entrada invalida. Introduza novamente o numero de apostas automaticas: "<<endl; cin >> n_apostas;} // entrada de dados incorrecta
	while (n_apostas > saldo) {cout << "O jogador nao possui saldo suficiente."<<endl; return;}
	saldo = saldo - n_apostas;
	while( n_apostas > 0) {aposta_automatica (apostas); n_apostas--;}
	system("cls");
	cout << "Indique o numero de apostas manuais que pretende registar: " << endl;
	cin >> n_apostas;
	while (cin.fail() || n_apostas < 0){cin.clear();cin.ignore(300,'\n');cout << "Entrada invalida. Introduza novamente o numero de apostas manuais: "<<endl; cin >> n_apostas;} // entrada de dados incorrecta
	while (n_apostas > saldo) {cout << "O jogador nao possui saldo suficiente."<<endl<< "Introduza novamente o numero de apostas manuais: " ;}
	// tratamento apostas manuais
	cout << "Ex: 30 31 32 33 34 1 2 , representa a aposta constituida pelos numeros de 30 a 34 e as estrelas 1 e 2" << endl;
	while ( n_apostas > 0 )
	{
		cout <<endl<< "Introduza a aposta : " << endl;
		cin  >> n1 >> n2 >> n3 >> n4 >> n5 >> e1 >> e2 ;
		if (cin.fail() || verifica_apostas(n1,n2,n3,n4,n5,e1,e2) )// o input foi incorreto o programa salta a iteracao sem actualizar o contador
		{
			system("cls");
			cin.clear();
			cin.ignore (300, '\n');
			cout << "Entrada de dados incorrecta"<< endl;
			continue;
		}
		else
		{

			int array_apostas_numeros[]={n1, n2, n3, n4, n5};
			bubblesort (array_apostas_numeros, 5);

			apostas.push_back(array_apostas_numeros[0]);
			apostas.push_back(array_apostas_numeros[1]);
			apostas.push_back(array_apostas_numeros[2]);
			apostas.push_back(array_apostas_numeros[3]);
			apostas.push_back(array_apostas_numeros[4]);

			int array_apostas_estrelas[] = {e1, e2};
			bubblesort (array_apostas_estrelas, 2);

			apostas.push_back(array_apostas_estrelas[0]);
			apostas.push_back(array_apostas_estrelas[1]);

			n_apostas--;
		}

	}
	int escolha;
	int size;
	size = apostas.size();
	size = size / 7; // numero de apostas
	system ("cls");
	cout << "1- Registar apostas " << endl << "2- Sair sem registar" << endl << "Escolha a opcao pretendida: ";
	cin >> escolha;
	while (cin.fail() || (escolha != 1 && escolha != 2)){cin.clear();cin.ignore(300,'\n');cout << "Entrada invalida. Introduza uma opcao valida: "<<endl; cin >> escolha;} // entrada de dados incorrecta
	if (escolha == 2) {return ;}
	actualiza_prize_key( size);
	out_apostas ( apostas,jogador);
	actualiza_saldo(jogador,size);
	return;
}
int in_num_jogador()
	/* 
	recebe um numero de jogador verificando se este existe.
	se existir chama a funcao reg_bet_jogador
	se nao existir da uma mensagem de erro e retorna o valor 1
	*/
{
	int jogador;
	int jogador_file; // numero do jogador importado do ficheiro txt
	double saldo;
	bool fplayer=false;     // found player, indica se o player inserido foi ou nao encontrado
	string ficheiro_players_linha;
	ifstream players; // abertura do ficheiro players.txt

	cout << "Insira o numero do jogador a que pretende adicionar a aposta: ";
	cin  >> jogador;
	while (cin.fail()){cin.clear();cin.ignore(300,'\n');cout << "Entrada invalida. Introduza novamente o numero do jogador: "<<endl; cin >> jogador;} // entrada de dados incorrecta
	players.open ("players.txt");
	if ( players.fail() ){cout<< " O ficheiro players.txt nao existe " << endl ;return 0;} // o ficheiro players nao existe e a funcao termina

	getline(players,ficheiro_players_linha);
	jogador_file = str_int_num_player (ficheiro_players_linha);
	if ( jogador_file < jogador ) // deteta se o jogador e maior que o numero na 1ª linha de codigo(equivalente ao ultimo jogador registado)
	{system("cls");players.close();cout << "O jogador que inseriu nao existe " << endl ;return 0;}
	else
	{		
		getline(players,ficheiro_players_linha); // evita que a linha 1 seja analisada 2 vezes e que haja um erro quando o ficheiro players nao tem nenhum player registado(registo de um jogador seguido da sua eliminacao)
		while ( !players.eof() ) // le o ficheiro em busca do numero do jogador
		{
			jogador_file = str_int_num_player (ficheiro_players_linha); // a funcao retorna zero se a string nao tiver mais do que 6 caracteres
			if ( jogador_file == jogador && jogador_file != 0 )// o jogador foi encontrado
			{fplayer = true;break;}
			getline(players,ficheiro_players_linha);
		}
	}
	players.close();
	if ( fplayer == false ) // o ficheiro foi lido e o jogador nao foi encontrado
	{system("cls");players.close();cout << "O jogador que inseriu nao existe " << endl ;return 0;}
	else // consulta o saldo do jogador
	{ 
		saldo = str_int_saldo_players(ficheiro_players_linha);
		reg_bet_jogador(jogador , saldo);
		return 0;
	}
}
int main()
{
	int escolha;
	srand ((unsigned) time(NULL) );
	cout << "Bem vindo ao ficheiro bets."<< endl<<endl;
	cout << "1-Registar aposta." << endl;
	cout << "2-Sair." << endl<<endl;
	cout <<"Escolha a opcao que pretende executar: " ;
	cin >> escolha;
	while (cin.fail() || (escolha != 1 && escolha != 2) ){cin.clear();cin.ignore(300,'\n');cout << "Entrada invalida. Introduza a opcao pretendida: "<<endl; cin >> escolha;}
	switch (escolha)
	{	
	case 1:
		{
			in_num_jogador();
			while (escolha == 1)
			{
				cout << "1-Registar novas aposta " << endl;
				cout << "2-Sair" << endl;
				cin >> escolha;
				while (cin.fail() || (escolha != 1 && escolha != 2) ){cin.clear();cin.ignore(300,'\n');cout << "Entrada invalida. Introduza a opcao pretendida: "<<endl; cin >> escolha;}
				if (escolha == 2 ) {return 0;}
				in_num_jogador();
			}
		}
	case 2:
		{system("cls"); cout << "Bets Control System agradece a sua preferencia "<<endl;return 0;}


	}}