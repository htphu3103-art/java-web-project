package com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.HoaDon;

public interface HoaDonRepo extends JpaRepository<HoaDon, Integer>{
	HoaDon findByMaHD(int maHD);
}
