#include "key.h"
using namespace std;
void bubblesort(int a[], int n) // ordena array de n elementos
{
	for(int j=0; j<n; j++){
		for(int i=0; i<n-1; i++){
			if(a[i+1] < a[i])
				swap(a[i+1], a[i]);
		}
	}
}
int sorteio()
{system("cls");
int n1, n2, n3, n4, n5, e1, e2;
srand ( time(NULL) );

n1 = rand() % 50 + 1;							//gera aleatoriamente os 5 numeros
do {n2 = rand() % 50 + 1;}
while (n2==n1);
do {n3 = rand() % 50 + 1;}
while (n3==n2 || n3==n1);
do {n4 = rand() % 50 + 1;}
while (n4==n3 || n4==n2 || n4==n1);
do {n5 = rand() % 50 + 1;}
while (n5==n4 || n5==n3 || n5==n2 || n5==n1);

int numeros[] = {n1, n2, n3, n4, n5}; // cria array com os 5 numeros

bubblesort(numeros, 5);  // ordena os 5 numeros

e1 = rand() % 11 + 1;		// gera aleatoriamente as 2 estrelas
do {e2 = rand() % 11 + 1;}
while (e2==e1);

int estrelas[] = {e1, e2};   // cria array com as 2 estrelas

bubblesort(estrelas, 2);  // ordena as 2 estrelas

cout << "Chave premiada: \n \n";
cout << right << setfill (' ') << setw(2) << numeros[0] << " ";
cout << right << setfill (' ') << setw(2) << numeros[1] << " ";
cout << right << setfill (' ') << setw(2) << numeros[2] << " ";
cout << right << setfill (' ') << setw(2) << numeros[3] << " ";
cout << right << setfill (' ') << setw(2) << numeros[4] << " - ";
cout << right << setfill (' ') << setw(2) << estrelas[0] << " ";
cout << right << setfill (' ') << setw(2) << estrelas[1] << endl << endl;

	ofstream pout;
	pout.open ("prize_key.txt",ios::app);
	if (pout.is_open())
	{
		pout << "Chave do concurso: ";
		pout << right << setfill (' ') << setw(2) << numeros[0] << " ";
		pout << right << setfill (' ') << setw(2) << numeros[1] << " ";
		pout << right << setfill (' ') << setw(2) << numeros[2] << " ";
		pout << right << setfill (' ') << setw(2) << numeros[3] << " ";
		pout << right << setfill (' ') << setw(2) << numeros[4] << " - ";
		pout << right << setfill (' ') << setw(2) << estrelas[0] << " ";
		pout << right << setfill (' ') << setw(2) << estrelas[1] << endl;

		
	pout.close();
}


return 0;}
int main()
{ 
	cout << "SORTEIO DA CHAVE PREMIADA \n \n 1 - Realizar o sorteio \n 2 - Sair \n \nInsira a opcao pretendida: ";
	int opcao;
	int opcao_sort = 1;
	cin >> opcao;
	while (cin.fail() || opcao < 1 || opcao > 2)
	{cin.clear(); cin.ignore(500, '\n'); cout << "Opcao inserida invalida. Insira um numero valido: "; cin >> opcao;}
	system("cls");
	switch (opcao)
	{
	case 1: 
		sorteio();
		break;
	
	case 2:
		break;
	}

	return 0;
}