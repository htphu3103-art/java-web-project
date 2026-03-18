package com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.GiangVien;

public interface GiangVienRepo extends JpaRepository<GiangVien, Integer>{
	boolean existsByMaGV(int maGV);
	boolean existsByEmail(String email);
	GiangVien findByEmailAndPass(String email,String pass);

}
