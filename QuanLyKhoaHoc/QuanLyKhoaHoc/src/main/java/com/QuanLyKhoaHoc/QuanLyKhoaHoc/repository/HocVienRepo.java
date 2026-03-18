package com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.HocVien;


public interface HocVienRepo extends JpaRepository<HocVien, Integer>{
	HocVien findByEmailAndPass(String email, String pass);
	HocVien findByEmail(String email);
	HocVien findByVerificationCode(String verificationCode);
}
