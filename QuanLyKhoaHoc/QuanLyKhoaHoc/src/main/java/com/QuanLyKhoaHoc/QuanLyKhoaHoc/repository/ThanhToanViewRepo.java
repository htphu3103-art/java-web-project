package com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.ThanhToanView;
import java.util.List;


public interface ThanhToanViewRepo extends JpaRepository<ThanhToanView, Integer>{
	List<ThanhToanView> findByTrangThai(int trangThai);
	List<ThanhToanView> findByTrangThaiAndHoTenContaining(int trangThai, String HoTen);
	ThanhToanView findByMaDK(int maDK);
	List<ThanhToanView> findByMaHDAndMaHV(int maHD,int maHV);
	List<ThanhToanView> findByMaHD(int maHD);
}
