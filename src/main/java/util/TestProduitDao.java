package util;

import java.util.List;

import dao.ProduitDao;
import dao.ProduitDaoImpl;
import model.Produit;

public class TestProduitDao {

    public static void main(String[] args) {
        ProduitDao produitDao = new ProduitDaoImpl();

        Produit produit = new Produit("PC Portable", "HP i5 16Go RAM", 7500.0);
        produitDao.save(produit);

        System.out.println("Produit ajoute avec succes !");

        List<Produit> produits = produitDao.findAll();
        for (Produit p : produits) {
            System.out.println(
                p.getIdProduit() + " | " +
                p.getNom() + " | " +
                p.getDescription() + " | " +
                p.getPrix()
            );
        }
    }
}