package dao;

import java.util.List;
import model.Produit;

public interface ProduitDao {
    void save(Produit produit);
    List<Produit> findAll();
    Produit findById(Long id);
    void update(Produit produit);
    void delete(Long id);
}