package ngoc.spql.practice._13_spql.dao;

import ngoc.spql.practice._13_spql.entity.SinhVien;

import java.util.List;

public interface SinhVienDAOInterface {

    public void save(SinhVien sinhvien);

    public SinhVien findById(int id);

    public SinhVien findByName(String name);

    public List<SinhVien> searchSinhVien(String name);
}
