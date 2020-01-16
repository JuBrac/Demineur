public class Grille  {
	public int taille;
	public int nb_bombe;
	public int[][] grille;
	
	public Grille(int t,int n){ //création de l'objet grille
		taille=t;
		nb_bombe=n;
		int g[][]=new int [t][t];
		for(int h=0;h<t;h++){
			for(int j=0;j<t;j++){
				g[h][j]=0;
			}
		}
				
		for(int i=0;i<n;i++){ //mise en place bombes dans des emplacements aléatoires
			int x=(int)(Math.random()*(taille-2)+1);
			int y=(int)(Math.random()*(taille-2)+1);
			g[x][y]=9;
					
		}
		
		grille=g;
	}
	
	

	public int nbVoisin(int c,int b){ //calcul du nombre de bombes voisines pour une case
		
		int voisin=0;
		if(grille[c][b]==9){
			voisin=9;
		} else {
			for(int i=c-1;i<=c+1;i++){
				for(int j=b-1;j<=b+1;j++){
					if(grille[i][j]==9){
						voisin++;
					
					}	
				}
			}
		}
		
		return voisin;
	}
	public int[][] grilleVoisin(Grille g1){ //création d'un tableau contenant le nombre de bombes adjacentes pour chaque case
		Grille g2=g1;
		for(int v=1;v<taille-1;v++){
			for(int p=1;p<taille-1;p++){
				g2.grille[v][p]=nbVoisin(v,p);
			}
		}
		
		for(int k=0;k<taille;k++){
			g2.grille[0][k]=10;
			g2.grille[k][0]=10;
			g2.grille[k][taille-1]=10;
			g2.grille[taille-1][k]=10;
		}	
		return g2.grille;
	}

	public void affichage(char[][] tab){ //affichage du tableau de caractères
		for(int i=0;i<tab.length;i++){
			System.out.println(' ');
			for(int j=0;j<tab[0].length;j++){
				System.out.print(tab[i][j]);
			}
		}
	}
	
	public char[][] conversion(int[][] x){ // conversion d'un tableau d'entiers en tableau de caractères
		char[][] conv=new char[x.length][x.length];
		for(int j=1;j<x[0].length;j++){
			int min=96+j;
			int maj=64+j;
			conv[0][j]=(char)(min);
			conv[j][0]=(char)(maj);
			conv[0][0]=92;
		}
		
		for(int k=1;k<x.length;k++){
			for(int l=1;l<x[0].length;l++){
				if(x[k][l]==9){
					conv[k][l]=42;
				}
				else {
					int chiffres=48+x[k][l];
					conv[k][l]=(char)(chiffres);
				}
			}
		}
		return conv;
	}
	
}

