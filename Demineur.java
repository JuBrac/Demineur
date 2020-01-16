import java.util.Scanner;

public class Demineur {
	public static void main (String [] arg) {
		System.out.println(); //règles du jeu
		System.out.println();
		System.out.println("Bienvenue dans le Démineur !");
		System.out.println("L'objectif est de retrouver les mines cachées sous les cases.");
		System.out.println("Chaque nombre que vous découvrirez correspondra à un nombre de mines adjacentes à la case découverte.");
		System.out.println("Bonne chance !");
		System.out.println();
		System.out.println("Choisissez la taille du tableau(4 <taile< 25):");
		int longueur=0;
		while (!((longueur>=4) && (longueur<=25))){
			Scanner lon = new Scanner (System.in);
			longueur=lon.nextInt();
			if (!((longueur>=4) && (longueur<=25))){
				System.out.println("Rentrez une valeur valide svp");
			}
		}
		System.out.println("Choisissez le nombre de bombes(<taille*taille):");
		int bombe=-1;
		while (!((bombe>0) && (bombe<=longueur*longueur))){
		Scanner bo = new Scanner (System.in);
		bombe=bo.nextInt();
			if (!((bombe>0) && (bombe<=longueur*longueur))){
				System.out.println("Rentrez une valeur valide svp");
			}
		}
		System.out.println("Confirmez");
		
		Grille jeu = new Grille (longueur,bombe);
		GrilleAffichee ecran=new GrilleAffichee (jeu);
		jeu.affichage(ecran.grilleInitiale());
		int perdu=0;
		char [][] nouv=ecran.grilleInitiale();
	
	
		while(perdu==0){ //tant que le joueur n'a pas perdu (perdu=1) ou gagné (perdu=2)
			int nb_cases_restantes=0;
			System.out.println();
			System.out.println();
			System.out.println("Saisir les coordonees(colonne,LIGNE)");
			Scanner sc = new Scanner (System.in); //on demande au joueur les coordonnées de la case qu'il veut découvrir
			String coord=sc.nextLine();
			char colonne=coord.charAt(0);
			char ligne=coord.charAt(1);	
			int y= (int)(colonne-96);
			int x=(int)(ligne-64);
			nouv=ecran.modifieGrille(x,y,nouv); //on affiche la grille modifiéé
			
			if(jeu.grilleVoisin(jeu)[x][y]==9){ //si le joueur tombe sur une bombe : perdu
				perdu=1;
				System.out.println("Perdu");
			}
			else {			
				for(int i=0;i<nouv.length;i++){ //compteur de cases restantes à découvrir
					System.out.println();
					for(int j=0;j<nouv.length;j++){
						System.out.print(nouv[i][j]);
						if(nouv[i][j]=='#'){
							nb_cases_restantes++;
						}	
					}
				}
				if(nb_cases_restantes==jeu.nb_bombe){ //si le nombre de cases restantes à découvrir est égales au nombre de bombes,
					perdu=2;
					System.out.println();				//le joueur a découvert toutes les bombes, il a gagné
					System.out.println("Victoire");
				}
			}	
		}		
	}	
}
