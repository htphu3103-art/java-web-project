package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.HocVien;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.HocVienRepo;

@Service
public class HocVienService {
	@Autowired
	private HocVienRepo dao;

	public List<HocVien> getAllHocVien(){
		return dao.findAll();
	}
	public HocVien ktDangNhap(String email, String pass) {
		return dao.findByEmailAndPass(email, pass);
	}

	public void save(HocVien hv) {
		dao.save(hv);
	}
	public HocVien findByVerificationCode(String code) {
		return dao.findByVerificationCode(code);
	}
	public boolean ktEmail(String email) {
		return dao.findByEmail(email) != null;
	}



}
