package ngoc.spql.practice._13_spql.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import ngoc.spql.practice._13_spql.entity.SinhVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class SinhVienDAO implements SinhVienDAOInterface {

    public EntityManager EntityManager;

    @Autowired
    public SinhVienDAO(EntityManager entityManager) {
        EntityManager = entityManager;
    }

    @Override
    public void save(SinhVien sinhvien) {
        EntityManager.persist(sinhvien);
    }

    @Override
    public SinhVien findById(int id) {
        return EntityManager.find(SinhVien.class, id);
    }

    @Override
    public SinhVien findByName(String name) {
        String Jpql = "SELECT s FROM SinhVien s WHERE s.name = :name ";
        TypedQuery<SinhVien> query = EntityManager.createQuery(Jpql, SinhVien.class);
        query.setParameter("name", name);
        return query.getSingleResult();

    }

    public List<SinhVien> searchSinhVien(String name) {
        String jpql = "SELECT s FROM SinhVien s WHERE s.name LIKE :name";
        TypedQuery<SinhVien> query = EntityManager.createQuery(jpql, SinhVien.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

}
