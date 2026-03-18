package com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.LopHocView;

public interface LopHocViewRepo extends JpaRepository<LopHocView, Integer>{
	List<LopHocView> findByMaHV(int maHV);
	LopHocView findByMaLopAndMaHV(int maLop, int maHV);
	LopHocView findByMaLop(int maLop);
	List<LopHocView> findByMaHVAndTrangThaiHocPhi(int maHV, int isHP);
}
