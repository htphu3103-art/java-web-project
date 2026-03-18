package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.GiangVien;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.GiangVienRepo;

@Service
public class GiangVienService {

	@Autowired
	private GiangVienRepo dao;

	public boolean kiemTraMaGV(int magv) {
		return dao.existsByMaGV(magv);

	}
	public boolean kiemTraEmail(String email) {
		return dao.existsByEmail(email);
	}
	public GiangVien getGiangVien(String email, String Pass) {
		return dao.findByEmailAndPass(email, Pass);
	}
	public void save(GiangVien gv) {
		dao.save(gv);
	}
	public List<GiangVien> getAllGV(){
		return dao.findAll();
	}
	public void delete(int magv) {
		dao.deleteById(magv);
	}
}
