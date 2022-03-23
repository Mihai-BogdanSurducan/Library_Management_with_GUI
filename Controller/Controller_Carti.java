package Controller;

import Model.Carte;
import Repository.RepoCarti;
import java.util.*;

public class Controller_Carti {

	    private static RepoCarti repo;
	    
	    public Controller_Carti(RepoCarti repo) {
	        this.repo = repo;
	    }

	    public static void readFromFile_Carti() {
	        repo.readFromFile_Carti();
	    }

	    public void adaugareCarte(int id, String titlu, String nume_autor, int an, double pret, String editura, String cota) {
	        Carte carte = new Carte(id,titlu,nume_autor,an,pret,editura,cota);
	        try {
	            repo.adaugareCarte(carte);
	            System.out.println("Cartea a fost adaugata!");
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    public boolean searchByIdCarte(int id) {
	        if (repo.searchByIdCarte(id) != -1) {
	            return true;
	        }
	        return false;
	    }

	    public Carte searchByIdCarte_Obiect(int id) {
	        int pozitie = repo.searchByIdCarte(id);
	        if (repo.searchByIdCarte(id) != -1) {
	            return repo.getCarte(pozitie);
	        }
	        return repo.getCarte(-1);
	    }

	    public void updateCarte(int id, String titlu, String nume_autor, int an, double pret, String editura, String cota) {
	        Carte carte = new Carte(id,titlu,nume_autor,an,pret,editura,cota);
	        try {
	            repo.updateCarte(carte);
	            System.out.println("Cartea a fost actualizata!");
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	    }

	    public void deleteCarte(int id) throws Exception {
	        int pozitie = repo.searchByIdCarte(id);
	        if (pozitie != -1) {
	            Carte carte = new Carte(id, repo.getCarte(pozitie).getTitlu(), repo.getCarte(pozitie).getNumeAutor(), repo.getCarte(pozitie).getAn(), repo.getCarte(pozitie).getPret(), repo.getCarte(pozitie).getEditura(), repo.getCarte(pozitie).getCota());
	            try {
	                repo.deleteCarte(carte);
	                System.out.println("Cartea a fost stearsa!");
	            } catch (Exception e) {
	                System.out.println(e.getMessage());
	            }
	        } else {
	            throw new Exception("Cartea cu acest id nu exista!");
	        }
	    }

	    public List<Carte> getAll() {
	        return repo.getAll();
	    }
}