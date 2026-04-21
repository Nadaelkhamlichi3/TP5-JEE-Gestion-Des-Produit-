package dao;

import java.util.List;
import model.Produit;

public interface ProduitDao {
    void save(Produit produit);
    void update(Produit produit);
    void delete(Long id);
    Produit findById(Long id);
    List<Produit> findAll();
}