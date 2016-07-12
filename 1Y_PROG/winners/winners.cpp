#include "winners.h";
using namespace std;
double str_int_saldo_players(string linha)// funcao de conversao do saldo de string para inteiro
{
	int i=41; // a funcao "varre" a linha da esquerda para a direita o 42ª caracter e o ultimo caracter
	char a;// caracter analisado
	double b; // o caracter a e convertido num inteiro e guardado em b, esta variavel e depois convertida numa de tipo double
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
void actualiza_saldo(int jogador,double incremento)
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
				saldo = saldo + 1.0*incremento;
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
bool esta_na_chave_estrela(int estrela, int const chave[])
{
	int i=5; // verifica se a estrela esta na chave
	while (i < 7)
	{
		if (chave[i] == estrela) { return true;}
		i++;
	}
	return false;
}
bool esta_na_chave_numero(int numero, int const chave[])
{
	int i=0; // verifica se o numero esta na chavee
	while (i < 5)
	{
		if (chave[i] == numero) {return true;}
		i++;
	}
	return false;
}
int devolve_lugar_premio(int const chave_analisar[],int const chave[])
{
	int estrelas=0;
	int numeros=0;
	int i = 4;
	int contadori = 4;
	while (i >= 0 ) // devolve o numero de numeros comuns entre a chave premiada e a aposta
	{
		while (contadori >= 0)
		{
			if (chave_analisar[contadori] == chave[i]) {numeros++;break;} 
			contadori--;
		}
		i--;
		contadori=4;
	}
	i=6;
	contadori = 6;

	while (i >= 5 ) // devolve o numero de estrelas comuns entre a chave premiada e a aposta
	{
		while (contadori >=5)
		{
			if (chave_analisar[contadori] == chave[i]) {estrelas++;break;}
			contadori--;
		}
		i--;
		contadori=6;
	}
	// devolve a posicao do premio no vector jogador_premios da funcao out_winners
	if ( numeros == 5 && estrelas == 2) {return 0;}
	if ( numeros == 5 && estrelas == 1) {return 1;}
	if ( numeros == 5 && estrelas == 0) {return 2;}
	if ( numeros == 4 && estrelas == 2) {return 3;}
	if ( numeros == 4 && estrelas == 1) {return 4;}
	if ( numeros == 4 && estrelas == 0) {return 5;}
	if ( numeros == 3 && estrelas == 2) {return 6;}
	if ( numeros == 2 && estrelas == 2) {return 7;}
	if ( numeros == 3 && estrelas == 1) {return 8;}
	if ( numeros == 3 && estrelas == 0) {return 9;}
	if ( numeros == 1 && estrelas == 2) {return 10;}
	if ( numeros == 2 && estrelas == 1) {return 11;}
	if ( numeros == 2 && estrelas == 0) {return 12;}
	return 13;
}
int out_winners(int jogador,vector <int> vector_apostas,int chave[],int premios[])
{
	string nome; // contem o nome do jogador
	string linha;// string auxiliar 
	string codigo; // string auxiliar
	ifstream players;
	int jogador_premios[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0}; // contem ordenadamente as apostas premiadas de um jogador
	int numero_apostas_jogador = vector_apostas.size()/7;
	int chave_analisar [7]; 
	double montante_que_o_jogador_recebe_se_ganhar_premio[13];
	///////////////////////////////////
	int i_auxiliar = 12;
	int contador_premios_nao_atribuidos= 0;
	int contador_premios_atribuidos;
	int i_prize_in=0;
	string word_prize_in;

	ifstream prize_in;
	prize_in.open("prize_key.txt");
	if (prize_in.fail()) {cout << "O ficheiro prize_key nao existe." << endl; return 1;}
	getline (prize_in, linha);
	getline (prize_in, linha);
	getline (prize_in, linha);
	getline (prize_in, linha);
	while (i_prize_in <=12) // obtem o montante que o jogador recebe se ganhar um premio e guarda-o no lugar respectivo do array 
	{prize_in >> word_prize_in;
	prize_in >> word_prize_in;
	prize_in >> word_prize_in;
	prize_in >> word_prize_in;
	montante_que_o_jogador_recebe_se_ganhar_premio[i_prize_in]= atof(word_prize_in.c_str());
	i_prize_in++;
	}




	players.open("players.txt");
	if (players.fail()) {cout << "O ficheiro players nao existe." << endl; return 1;}
	getline (players, linha);
	while (!players.eof()) // obtem o nome do jogador que esta a ser analisado
	{
		players >> codigo;
		if ((atoi(codigo.c_str()))==jogador)
		{getline (players, linha);
		nome = linha.substr (1,21);}
		else {getline (players, linha);}
	}
	players.close();
	int i=vector_apostas.size()-1;
	while (i >= 0) // incrementa a posicao do array jogador_premios se o jogador vencer o premio respectivo
	{
		chave_analisar[6] = vector_apostas[i];
		chave_analisar[5] = vector_apostas[i-1];
		chave_analisar[4] = vector_apostas[i-2];
		chave_analisar[3] = vector_apostas[i-3];
		chave_analisar[2] = vector_apostas[i-4];
		chave_analisar[1] = vector_apostas[i-5];
		chave_analisar[0] = vector_apostas[i-6];
		i = i-7;
		jogador_premios[devolve_lugar_premio(chave_analisar,chave)]++;
	}
	i = 12;
	int num_premios_ganhos=0;
	while (i >= 0) // conta quantos premios o jogador ganhou
	{
		num_premios_ganhos = jogador_premios[i]+num_premios_ganhos;
		i--;
	}
	ofstream winners;
	winners.open("winners.txt",ios::app);
	winners << setw(7) <<right << setfill('0') << jogador;
	winners << " - ";
	winners << nome ;
	winners << "     ";
	winners << numero_apostas_jogador << "/";
	winners << num_premios_ganhos;

	double valor_total_premio_jogador=0;
	i = 12;
	while ( i >= 0 )
	{
		valor_total_premio_jogador = valor_total_premio_jogador + jogador_premios[i] *  montante_que_o_jogador_recebe_se_ganhar_premio[i];
		i--;
	}
	// actualizacao da primeira linha do winners.txt
	winners.setf(ios::fixed,ios::floatfield);
	winners.precision(2);
	winners <<"   " << valor_total_premio_jogador;
	actualiza_saldo(jogador,valor_total_premio_jogador);
	winners << endl;
	// exporta as chaves premiadas com os respectivos asteriscos* 
	int lugar_premio = devolve_lugar_premio(chave_analisar,chave);
	i=vector_apostas.size()- 1;
	while (i >= 0)
	{
		chave_analisar[6] = vector_apostas[i];
		chave_analisar[5] = vector_apostas[i-1];
		chave_analisar[4] = vector_apostas[i-2];
		chave_analisar[3] = vector_apostas[i-3];
		chave_analisar[2] = vector_apostas[i-4];
		chave_analisar[1] = vector_apostas[i-5];
		chave_analisar[0] = vector_apostas[i-6];
		i = i-7;
		if (13 == devolve_lugar_premio(chave_analisar,chave)){continue;}
		winners << setw(2) << setfill(' ') << right<<chave_analisar[0];
		if (esta_na_chave_numero (chave_analisar[0],chave)) { winners << "* " ;}
		else { winners << "  ";}
		winners << setw(2) << setfill(' ') << right<<chave_analisar[1];
		if (esta_na_chave_numero (chave_analisar[1],chave)) { winners << "* " ;}
		else { winners << "  ";}
		winners << setw(2) << setfill(' ') << right<<chave_analisar[2];
		if (esta_na_chave_numero (chave_analisar[2],chave)) { winners << "* " ;}
		else { winners << "  ";}
		winners << setw(2) << setfill(' ') << right<<chave_analisar[3];
		if (esta_na_chave_numero (chave_analisar[3],chave)) { winners << "* " ;}
		else { winners << "  ";}
		winners << setw(2) << setfill(' ') << right<<chave_analisar[4];
		if (esta_na_chave_numero (chave_analisar[4],chave)) { winners << "* " ;}
		else { winners << "  ";}

		winners << "- ";


		winners << setw(2) << setfill(' ') << right<<chave_analisar[5];
		if (esta_na_chave_estrela (chave_analisar[5],chave)) { winners << "* " ;}
		else { winners << "  ";}
		winners << setw(2) << setfill(' ') << right<<chave_analisar[6];
		if (esta_na_chave_estrela (chave_analisar[6],chave)) { winners << "* " ;}
		else { winners << "  ";}


		lugar_premio = devolve_lugar_premio(chave_analisar,chave);
		if (lugar_premio == 0) {winners << " (5+2)=";}
		if (lugar_premio == 1) {winners << " (5+1)=";}
		if (lugar_premio == 2) {winners << " (5+0)=";}
		if (lugar_premio == 3) {winners << " (4+2)=";}
		if (lugar_premio == 4) {winners << " (4+1)=";}
		if (lugar_premio == 5) {winners << " (4+0)=";}
		if (lugar_premio == 6) {winners << " (3+2)=";}
		if (lugar_premio == 7) {winners << " (2+2)=";}
		if (lugar_premio == 8) {winners << " (3+1)=";}
		if (lugar_premio == 9) {winners << " (3+0)=";}
		if (lugar_premio == 10) {winners << " (1+2)=";}
		if (lugar_premio == 11) {winners << " (2+1)=";}
		if (lugar_premio == 12) {winners << " (2+0)=";}
		winners.setf(ios::fixed,ios::floatfield);
		winners.precision(2);
		winners << montante_que_o_jogador_recebe_se_ganhar_premio[lugar_premio] << endl;

	}

	winners << endl;
	winners.close();
	return 0;
}
//auxiliar (acrescenta 1 aposta a um vector com todas as apostas de um jogador)
void acrescenta_vector( string linha,vector <int> &apostas)
{
	// acrescenta ao vector apostas a aposta contida na linha
	string interm;
	interm = linha.substr(0,2);
	apostas.push_back(atoi(interm.c_str()));
	interm = linha.substr(3,2);
	apostas.push_back(atoi(interm.c_str()));
	interm = linha.substr(6,2);
	apostas.push_back(atoi(interm.c_str()));
	interm = linha.substr(9,2);
	apostas.push_back(atoi(interm.c_str()));
	interm = linha.substr(12,2);
	apostas.push_back(atoi(interm.c_str()));
	interm = linha.substr(17,2);
	apostas.push_back(atoi(interm.c_str()));
	interm = linha.substr(20,2);
	apostas.push_back(atoi(interm.c_str()));
	return;

} // secundario
// auxiliar de output para o prize_key.txt
int prize_key_out (int a[], int valor_premio)
{
	int i = 0;
	int contador = 0;
	while (i<13)
	{if(a[i]==0){contador++;i++;} // encontra no array a[] o numero de apostas nao premiadas
	else i++;}
	contador = 13 - contador; // contador guarda o numero de apostas premiadas
	ofstream prize_out;
	prize_out.open("prize_key.txt", ios::app); 
	prize_out.setf(ios::fixed,ios::floatfield);
	prize_out.precision(2);
	if (prize_out.fail()) {return 1;} // se existir um erro na abertura do prize_key.txt 
	prize_out << "Premio Acertos Num.Venc.      Valor" << endl; 
	prize_out << "------ ------- --------- ---------------" << endl;
	prize_out << "   1     5+2   " << setw(9) << setfill (' ') << right << a[0] << " ";
	// se nao existirem quaisquer apostas premiadas insere 0, se existirem insere o valor do premio individual
	if (a[0]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[0]);}
	prize_out << endl << "   2     5+1   " << setw(9) << setfill (' ') << right << a[1] << " ";
	if (a[1]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[1]);}
	prize_out << endl << "   3     5+0   " << setw(9) << setfill (' ') << right << a[2] << " ";
	if (a[2]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[2]);}
	prize_out << endl << "   4     4+2   " << setw(9) << setfill (' ') << right << a[3] << " ";
	if (a[3]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[3]);}
	prize_out << endl << "   5     4+1   " << setw(9) << setfill (' ') << right << a[4] << " ";
	if (a[4]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[4]);}
	prize_out << endl << "   6     4+0   " << setw(9) << setfill (' ') << right << a[5] << " ";
	if (a[5]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[5]);}
	prize_out << endl << "   7     3+2   " << setw(9) << setfill (' ') << right << a[6] << " ";
	if (a[6]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[6]);}
	prize_out << endl << "   8     2+2   " << setw(9) << setfill (' ') << right << a[7] << " ";
	if (a[7]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[7]);}
	prize_out << endl << "   9     3+1   " << setw(9) << setfill (' ') << right << a[8] << " ";
	if (a[8]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[8]);}
	prize_out << endl << "  10     3+0   " << setw(9) << setfill (' ') << right << a[9] << " ";
	if (a[9]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[9]);}
	prize_out << endl << "  11     1+2   " << setw(9) << setfill (' ') << right << a[10] << " ";
	if (a[10]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[10]);}
	prize_out << endl << "  12     2+1   " << setw(9) << setfill (' ') << right << a[11] << " ";
	if (a[11]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[11]);}
	prize_out << endl << "  13     2+0   " << setw(9) << setfill (' ') << right << a[12] << " ";
	if (a[12]==0) {prize_out << "           0.00";} else {prize_out << setw(15) << setfill(' ') << right << (double) valor_premio/(contador*a[12]);}
	return 0;
} 
//auxiliar winners (devolve lugar premio)
int trata_string (string linha, int const chave[])
{

	int estrelas=0;
	int numeros=0;
	int i = 4;
	int contadori = 4;
	int aposta[7];
	string interm = "";
	interm = linha.substr(0,2); //obtencao da aposta a analisar
	aposta[0] = atoi(interm.c_str());
	interm = linha.substr(3,2);
	aposta[1] = atoi(interm.c_str());
	interm = linha.substr(6,2);
	aposta[2] = atoi(interm.c_str());
	interm = linha.substr(9,2);
	aposta[3] = atoi(interm.c_str());
	interm = linha.substr(12,2);
	aposta[4] = atoi(interm.c_str());
	interm = linha.substr(17,2);
	aposta[5] = atoi(interm.c_str());
	interm = linha.substr(20,2);
	aposta[6] = atoi(interm.c_str());

	while (i >= 0 ) // obtem o numero de numeros comuns entre a aposta e a chave
	{
		while (contadori >= 0)
		{
			if (aposta[contadori] == chave[i]) {numeros++;break;}
			contadori--;
		}
		i--;
		contadori=4;
	}
	i=6;
	contadori = 6;

	while (i >= 5 ) // obtem o numero de estrelas comuns entre a aposta e a chave
	{
		while (contadori >=5)
		{
			if (aposta[contadori] == chave[i]) {estrelas++;break;}
			contadori--;
		}
		i--;
		contadori=6;
	}
	if ( numeros == 5 && estrelas == 2) {return 0;} //devolve o tipo de premio
	if ( numeros == 5 && estrelas == 1) {return 1;}
	if ( numeros == 5 && estrelas == 0) {return 2;}
	if ( numeros == 4 && estrelas == 2) {return 3;}
	if ( numeros == 4 && estrelas == 1) {return 4;}
	if ( numeros == 4 && estrelas == 0) {return 5;}
	if ( numeros == 3 && estrelas == 2) {return 6;}
	if ( numeros == 2 && estrelas == 2) {return 7;}
	if ( numeros == 3 && estrelas == 1) {return 8;}
	if ( numeros == 3 && estrelas == 0) {return 9;}
	if ( numeros == 1 && estrelas == 2) {return 10;}
	if ( numeros == 2 && estrelas == 1) {return 11;}
	if ( numeros == 2 && estrelas == 0) {return 12;}
	return 13; // devolve 13 se nao houver premio
}
//2º
int winners()
{
	int n1,n2,n3,n4,n5,e1,e2; // numeros e estrelas premiadas
	int valor_premio;
	int premios[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	string word;
	ifstream prize_in;
	prize_in.open("prize_key.txt");
	if ( prize_in.fail()) {cout <<"O ficheiro prize_key nao existe. ";return 0;}
	prize_in >> word; // obter valor do total das apostas do ficheiro prize_key.txt
	prize_in >> word;
	prize_in >> word;
	prize_in >> word;
	prize_in >> word;
	valor_premio = atoi( word.c_str() ); // converte a string num inteiro
	prize_in >> word; // obter chave premiada
	prize_in >> word;
	prize_in >> word;
	prize_in >> word;
	n1 = atoi( word.c_str() );
	prize_in >> word;
	n2 = atoi( word.c_str() );
	prize_in >> word;
	n3 = atoi( word.c_str() );
	prize_in >> word;
	n4 = atoi( word.c_str() );
	prize_in >> word;
	n5 = atoi( word.c_str() );
	prize_in >> word;
	prize_in >> word;
	e1 = atoi( word.c_str() );
	prize_in >> word;
	e2 = atoi( word.c_str() );
	prize_in.close();
	int chave [] = {n1,n2,n3,n4,n5,e1,e2}; // inserir chave num array
	ifstream bets_in;
	bets_in.open("bets.txt");
	if (bets_in.fail()){cout << "O ficheiro bets.txt nao existe." ; return 0;}
	getline(bets_in,word);
	if (bets_in.eof()) {cout <<"Nao foram registadas quaisquer apostas."; return 0;}
	while ( !bets_in.eof() ) //Enquanto o ficheiro nao acabar
	{
		if ( word.size() > 8) // se for uma aposta
		{ int a; a=trata_string(word,chave);premios[a]=premios[a]+1;} // ver se é premiada; se for, coloca-a na posicao respectiva do array premios
		getline(bets_in,word);

	}
	bets_in.close();
	prize_key_out (premios, valor_premio);
	//////////////////////////////////////////////////////////////// -> o ficheiro prize_key foi actualizado
	vector <int> vector_apostas; // guarda todas as apostas de um jogador em sequencia
	string n_jogadores; // string auxiliar
	int i=1;
	int njogadores; // nº jogadores total
	string linha;
	string linha_apostas; // string com um aposta do jogador
	int jogador; // numero do jogador
	ifstream players;
	players.open ("players.txt");
	if (players.fail()) {cout << "O ficheiro players nao existe." << endl; return 1;}
	players >> n_jogadores;
	njogadores = atoi (n_jogadores.c_str());
	//////////////////////////////////-> obtencao num ultimo jogador
	players.close();


	ifstream bets;
	bets.open ("bets.txt");
	if (bets.fail()) {cout << "O ficheiro bets nao existe." << endl; return 1;}
	getline (bets, linha);

	while ( njogadores >= i ) //controla o numero de vezes que o ficheiro bets e lido (bets e lido tantas vezes quantos jogadores existirem)
	{
		getline(bets,linha);
		while (!bets.eof()) 
		{
			if (linha.size() == 7) // Foi encontrado um codigo de jogador
			{
				jogador = atoi (linha.c_str());
				if (jogador != i) {getline(bets,linha);continue;} // o jogador encontrado nao e o que queremos analisar 
				getline(bets,linha);
				while ((linha.size() > 7) && (!bets.eof())) // enquanto nao for encontrado um codigo de jogador sao obtidas as sucessivas apostas
				{
					acrescenta_vector(linha,vector_apostas);
					getline(bets,linha);
				}
			}
			getline(bets,linha);	
		}
		if (vector_apostas.size()==0 ) {bets.close(); bets.open("bets.txt");i++;continue;} 
		out_winners(i,vector_apostas,chave,premios); // actualiza o ficheiro winners.txt
		bets.close();
		bets.open("bets.txt");
		vector_apostas.clear();
		i++;
	}
	bets.close();
	system("cls");
	cout << "Winners actualizado com sucesso."<< endl;

	return 0;
}
//1º
int main()
{
	int escolha;
	cout << "Bem vindo!" << endl<<endl;
	cout << "1- Correr winners " << endl;
	cout << "2- Sair" <<endl<< endl;
	cout << "Escolha a opcao pretendida: " ;
	cin >> escolha;
	while (cin.fail() || (escolha != 1 && escolha != 2) )
	{cin.clear();cin.ignore ( 500, '\n');cout << "Opcao invalida. Introduza novamente a opcao pretendida: ";cin >> escolha;}
	switch (escolha)
	{
	case 2:
		break;
	case 1:
		winners();
		break;
	}
	return 0 ;
}