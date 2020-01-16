import java.util.Scanner;
public class GrilleAffichee{
	Scanner sc = new Scanner (System.in);
	int longueur=sc.nextInt();
	Scanner bo = new Scanner (System.in);
	int bombe=bo.nextInt();
	Grille t1 = new Grille (longueur,bombe);    // Crée la grille initiale
	

	
	public GrilleAffichee(Grille t2){ 
				// Constructeur
		t2.grille=t1.grille;
			}
	
	public char[][] grilleInitiale(){						// Génère la grille affichée initialement
		char t[][] = new char[longueur][longueur];
		for(int i = 1; i<t.length-1;i++){
			for(int j = 1; j<t[0].length-1;j++){
				t[i][j] = 35;
			}
		}
		for(int i = 1; i<t.length;i++){
				t[i][0] = (char)(64 + i) ;
				t[0][i] = (char)(96 + i);
				t[i][longueur-1]=(char)(124);
				t[longueur-1][i]=(char)(126);
			}
			t[0][0]=(char)(92);
	
		return t;
	}
	
	public char[][] modifieGrille(int ligne, int colonne, char[][]grilleAffichee){	// Modifie la grille pour afficher les voisins
		if( t1.grilleVoisin(t1)[ligne][colonne] != 8 ){  							// La case sélectionnée n'est pas une bombe
			grilleAffichee[ligne][colonne] =(char)(48+(t1.grilleVoisin(t1))[ligne][colonne]);
		}
	
		boolean zerodsgrille=false;
		if (t1.grilleVoisin(t1)[ligne][colonne]==0){ // objectif : afficher tous les zeros nécessaires
			zerodsgrille=true;
			for(int k = -1; k < 2; k++){
				for(int l = -1; l < 2; l++){		//attribuer des valeurs aux voisins
					if((t1.grilleVoisin(t1)[ligne+k][colonne+l] != 9)&& (grilleAffichee[ligne+k][colonne+l] !=' ') &&(t1.grilleVoisin(t1)[ligne+k][colonne+l] != 10)){
						grilleAffichee[ligne+k][colonne+l] = t1.conversion(t1.grilleVoisin(t1))[ligne+k][colonne+l];					
						}
					}
				}
				grilleAffichee [ligne][colonne]=' '; // on remplace le zéro par un espace pour plus qu'il ne soit détecté par modifieGrille
		}

			while (zerodsgrille){
				for(int i = 0; i<grilleAffichee.length;i++){ //parcourir le tableau pour détecter un zero
					for(int j = 0; j<grilleAffichee.length;j++){
						if (grilleAffichee[i][j]=='0'){
							modifieGrille (i,j,grilleAffichee);
						} else {
							zerodsgrille=false; //il n'y a plus de zéro à afficher
						}
					}
				}
			}
		return grilleAffichee;
	}
}
