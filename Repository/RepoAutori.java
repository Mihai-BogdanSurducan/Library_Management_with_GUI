package Repository;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import Model.Autor;
import java.util.Scanner;

public class RepoAutori {
    private List<Autor> autoriList;
    private String fileName;

    //constructor implicit

    public RepoAutori(String fileName) {
        this.fileName = fileName;
        autoriList = new ArrayList<>();
    }

    public RepoAutori(List<Autor> autoriList, String fileName) {
        this.autoriList = autoriList;
        this.fileName = fileName;
    }

    public int getSize() {
        return autoriList.size();
    }

    public void readFromFile_Autori() {
        try {
            FileInputStream fileInput = new FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(fileInput);
            BufferedReader buffread = new BufferedReader(isr);
            String line = null;
            autoriList.clear();
            while ((line = buffread.readLine()) != null) {
                String components[] = line.split(",");
                String string_id_autor = components[0].trim();
                String nume_autor = components[1];
                int id = Integer.parseInt(string_id_autor);
                Autor autor = new Autor(id,nume_autor);
                autoriList.adaugare(autor);
            }
            buffread.close();
        } catch (IOException e) {
            System.err.println("Eroare in citirea din fisier!");
        }
    }
    
    public void writeToFile() throws IOException {
        File fileOutput = new File(fileName);
        FileOutputStream fos = new FileOutputStream(fileOutput);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);

        for (int i = 0; i < autoriList.size(); i++) {
            bw.write(autoriList.get(i).toStringForWriteToFile());
            bw.newLine();
        }
        bw.close();
    }

    public int searchByIdAutor(int id) {
        for (int i = 0; i < autoriList.size(); i++) {
            if (autoriList.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void adaugareAutor(Autor a) throws Exception {
        int pozitie = searchByIdAutor(a.getId());
        if (pozitie == -1)
        {
            autoriList.adaugare(a);
            writeToFile();
        } else
            throw new Exception("Autorul exista deja !");
    }
    
    public void updateAutor(Autor a) throws Exception {
        int pozitie = searchByIdAutor(a.getId());
        if (pozitie == -1) {
            throw new Exception("Autorul nu exista!");
        } else {
            autoriList.set(pozitie, a);
            writeToFile();
        }
    }
    
    public void schimbareAutori(Autor a, Autor b) throws Exception {
        int pozitieA = searchByIdAutor(a.getId());
        int pozitieB = searchByIdAutor(b.getId());
        
        autoriList.set(pozitieA, b);
        autoriList.set(pozitieB, a);        
    }

    public List<Autor> getAll() {
        return autoriList;
    }

    public Autor getAutor(int pozitie) {
        return autoriList.get(pozitie);
    }
}