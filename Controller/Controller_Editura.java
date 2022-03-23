package Controller;

import Model.Editura;
import Repository.RepoEdituri;


import java.util.*;

public class Controller_Editura {
    private RepoEdituri repo;

    public Controller_Editura(RepoEdituri repo) {
        this.repo = repo;
    }

    public void readFromFile_Edituri() {
        repo.readFromFile_Edituri();
    }

    public void adaugareEditura(int id, String nume) {
    	Editura editura = new Editura(id,nume);
    	try {
            repo.adaugareEditura(editura);
            System.out.println("Editura a fost adaugata!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateEditura(int id, String nume) {
    	Editura editura = new Editura(id,nume);
    	try {
            repo.updateEditura(editura);
            System.out.println("Editura a fost actualizata!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Editura> getAll() {
        return repo.getAll();
    }


    public void deleteEditura(int id) throws Exception {
        int pozitie = repo.searchByIdEditura(id);
        if (pozitie != -1) {
            Editura Editura = new Editura(id, repo.getEditura(pozitie).getNume());
            try {
                repo.deleteEditura(Editura);
                System.out.println("Editura a fost stearsa!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new Exception("Editura nu exista!");
        }
    }
}
