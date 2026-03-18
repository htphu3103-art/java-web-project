package com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.HoaDonView;

public interface HoaDonViewRepo extends JpaRepository<HoaDonView, Integer>{
	List<HoaDonView> findByMaHV(int maHV);
	List<HoaDonView> findByHoTenContaining(String hoTen);
}
