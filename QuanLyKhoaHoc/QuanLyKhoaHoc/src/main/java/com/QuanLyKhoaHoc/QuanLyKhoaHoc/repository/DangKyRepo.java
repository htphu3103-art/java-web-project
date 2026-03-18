package com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.DangKy;

import jakarta.transaction.Transactional;


public interface DangKyRepo extends JpaRepository<DangKy, Integer>{
	boolean existsByMaLopAndMaHV(int maLop,int maHV);
	DangKy findByMaHVAndMaLop(int maHV, int maLop);
	DangKy findByMaDK(int maDK);
	List<DangKy> findByMaHD(Integer maHD);

	@Modifying
	@Transactional
	@Query("UPDATE DangKy dk SET dk.trangThai = 0 WHERE dk.maHD IS NULL")
	int resetTrangThaiChoMaHDNull();

}
