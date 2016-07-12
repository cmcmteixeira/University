#include "players.h"
using namespace std;
void registarjogador()
{
	int ultimoregistado = 0;
	char nome[250];
	double saldo;
	cin.ignore(100,'\n');
	system("cls");
	cout << "Insira o nome do jogador: ";
	cin.getline (nome,250);
	nome[20] = '\0';
	cout << "Insira o saldo do jogador: ";
	cin >> saldo;
	while (cin.fail())
	{cin.clear(); cin.ignore(500, '\n'); cout << "Saldo inserido invalido. Insira um numero: "; cin >> saldo;}



	ifstream myfile;
	string word;
	string line;
	string codigo;        
	stringstream out;

	ofstream myfile6;
	myfile6.open ("players.txt",ios::app);
	if (myfile6.is_open()) myfile6.close();
	myfile.open ("players.txt");

	if (myfile.is_open()) // obtem o codigo do ultimo registado
	{
		getline (myfile, word);
		int ultimoregistado = atoi (word.c_str());// converter string c++ para inteiro
		ofstream myfile5;
		myfile5.open ("players_tmp.txt", ios::app);
		ultimoregistado = ultimoregistado + 1;

		out << ultimoregistado; // convert int to string
		codigo = out.str();
		if (myfile5.is_open())
		{ myfile5 << right << setfill ('0') << setw(7) << codigo;
		myfile5 << '\n';
		getline (myfile, line);
		while(!myfile.eof()) { 

			myfile5 << line << '\n';
			getline (myfile, line);
		}
		myfile.close();
		myfile5.close();
		}
	}

	
	ofstream myfile3; // o jogador e registado no ficheiro players
	myfile3.setf(ios::fixed,ios::floatfield);
	myfile3.precision(2);
	myfile3.open ("players_tmp.txt", ios::app);
	if (myfile3.is_open())
	{
		myfile3 << right << setfill ('0') << setw(7) << codigo << " ";
		myfile3 << left << setfill (' ') << setw(20) << nome << " ";
		myfile3 << right << setw (13) << saldo << endl;
		myfile3.close();
	}

	char oldname[] = "players_tmp.txt";
	char newname[] = "players.txt";
	remove(newname);
	rename(oldname,newname);

	cout << "Jogador registado com sucesso!" << endl;

	return ;
}
double str_int_saldo_players(string linha)
{
	int i=41; // a funcao "varre" a linha da esquerda para a direita o 42ª caracter e o ultimo caracter
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
int carregarsaldo()
{
	bool jogadorencontrado = false;
	int codigo;
	double carregamento;
	string line;
	string word;
	double saldo;
	string codigojogador;
	int codigojogadorint;
	cout << "Introduza o codigo do jogador: ";
	cin >> codigo;
	while (cin.fail())
	{cin.clear(); cin.ignore(500, '\n'); cout << "Codigo inserido invalido. Insira um inteiro: "; cin >> codigo;}
	cout << "Introduza o montante a carregar: ";
	cin >> carregamento;
	while (cin.fail())
	{cin.clear(); cin.ignore(500, '\n'); cout << "Montante inserido invalido. Insira um numero: "; cin >> carregamento;}
	ifstream myfile;
	ofstream myfile2;
	myfile.open ("players.txt");
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
			if (codigojogadorint == codigo)
			{
				jogadorencontrado = true;
				saldo = str_int_saldo_players(line);  
				saldo = saldo + carregamento;
				line.resize (29);
				myfile2 << line;
				myfile2.setf(ios::fixed,ios::floatfield);
				myfile2.precision(2);
				myfile2 << setw (13) << right << saldo << endl;
			}
			else myfile2 << line << endl;
		}
		if ( jogadorencontrado == false ) 
		{system("cls"); cout << "O jogador nao foi encontrado." << endl;}
		myfile.close();
		myfile2.close();
		char oldname[] ="players_tmp.txt";
		char newname[] ="players.txt";
		remove(newname);
		rename(oldname,newname);
	}
	return 0;
}
int consultarsaldo()
{
	int codigo;
	string line;
	string word;
	double saldo;
	string codigojogador;
	int codigojogadorint;
	bool jogadorencontrado = false;
	cout << "Introduza o codigo do jogador: ";
	cin >> codigo;
	while (cin.fail())
	{cin.clear(); cin.ignore(500, '\n'); cout << "Codigo inserido invalido. Insira um inteiro: "; cin >> codigo;}
	ifstream myfile;
	myfile.open ("players.txt");
	if (myfile.is_open())
	{
		getline (myfile, line);
		while(!myfile.eof())
		{
			getline (myfile,line);
			codigojogador = line;
			codigojogador.resize (7);
			codigojogadorint = atoi (codigojogador.c_str());// converter string c++ para inteiro
			if (codigojogadorint > codigo){break;}
			if (codigojogadorint == codigo)
			{
				saldo = str_int_saldo_players(line); 
				cout << "O saldo do jogador e' de " << fixed <<setprecision(2) << saldo << endl;
				jogadorencontrado = true;
			}
		}

		myfile.close();}



	if (jogadorencontrado == false) {system("cls");cout << "O jogador nao foi encontrado." << endl;}

	return 0;
	return 0;}
int eliminarjogador()
{
bool jogadorencontrado=false;
int codigo;
string line;
string word;
string codigojogador;
int codigojogadorint;
cout << "Introduza o codigo do jogador: ";
cin >> codigo;
while (cin.fail())
{cin.clear(); cin.ignore(500, '\n'); cout << "Codigo inserido invalido. Insira um inteiro: "; cin >> codigo;}
ifstream myfile;
ofstream myfile2;
myfile.open ("players.txt");
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
		if (codigojogadorint == codigo) {jogadorencontrado = true;continue;}
		else myfile2 << line << endl;
	}
	myfile.close();
myfile2.close();

}
char oldname[] ="players_tmp.txt";
char newname[] ="players.txt";
remove(newname);
rename(oldname,newname);
if (jogadorencontrado == false){cout << "O jogador nao foi encontrado" << endl;} 
return 0;
}
int main()
{
	int opcao;
	int opcao_reg=1;
	cout << "1 - Registar um jogador" << endl << "2 - Consultar saldo" << endl << "3 - Carregar o cartao do jogador" << endl << "4 - Eliminar um jogador" << endl << "5 - Sair" << endl;
	cin >> opcao;
	while (cin.fail() || opcao > 5 || opcao < 1)
	{cin.clear(); cin.ignore(500, '\n'); cout << "Opcao inserida invalida. Insira um numero valido: "; cin >> opcao;}
	system("cls");
	switch (opcao)
	{
	case 1: 
		registarjogador();
		while ( opcao_reg==1 ) {
			cout << "1 - Registar novo jogador" << endl << "2 - Sair" << endl;
			cin >> opcao_reg;
			while (cin.fail())
			{cin.clear(); cin.ignore(500, '\n'); cout << "Opcao inserida invalida. Insira um numero valido: "; cin >> opcao_reg;}
			if (opcao_reg != 1) break;
			else registarjogador();}
		break;

	case 2:
		consultarsaldo();
		while ( opcao_reg==1 ) {
			cout << "1 - Consultar saldo a novo jogador" << endl << "2 - Sair" << endl;
			cin >> opcao_reg;
			while (cin.fail())
			{cin.clear(); cin.ignore(500, '\n'); cout << "Opcao inserida invalida. Insira um numero valido: "; cin >> opcao_reg;}
			if (opcao_reg != 1) break;
			else consultarsaldo();}
		break;


	case 3:
		carregarsaldo();
		while ( opcao_reg==1 ) {
			cout << "1 - Carregar saldo" << endl << "2 - Sair" << endl;
			cin >> opcao_reg;
			while (cin.fail())
			{cin.clear(); cin.ignore(500, '\n'); cout << "Opcao inserida invalida. Insira um numero valido: "; cin >> opcao_reg;}
			if (opcao_reg != 1) break;
			else carregarsaldo();}
		break;

	case 4:
		eliminarjogador();
		while ( opcao_reg==1 ) {
			cout << "1 - Eliminar jogador" << endl << "2 - Sair" << endl;
			cin >> opcao_reg;
			while (cin.fail())
			{cin.clear(); cin.ignore(500, '\n'); cout << "Opcao inserida invalida. Insira um numero valido: "; cin >> opcao_reg;}
			if (opcao_reg != 1) break;
			else eliminarjogador();}
		break;

	case 5:
		return 0;



	}
	return 0;
}

