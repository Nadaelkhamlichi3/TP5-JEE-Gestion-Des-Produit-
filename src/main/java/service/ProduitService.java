package service;

import java.util.List;
import model.Produit;

public interface ProduitService {
    void saveProduit(Produit produit);
    void updateProduit(Produit produit);
    void deleteProduit(Long id);
    Produit getProduitById(Long id);
    List<Produit> getAllProduits();
}