package service;

import java.util.List;

import dao.ProduitDao;
import dao.ProduitDaoImpl;
import model.Produit;

public class ProduitServiceImpl implements ProduitService {

    private ProduitDao produitDao = new ProduitDaoImpl();

    @Override
    public void saveProduit(Produit produit) {
        produitDao.save(produit);
    }

    @Override
    public void updateProduit(Produit produit) {
        produitDao.update(produit);
    }

    @Override
    public void deleteProduit(Long id) {
        produitDao.delete(id);
    }

    @Override
    public Produit getProduitById(Long id) {
        return produitDao.findById(id);
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitDao.findAll();
    }
}