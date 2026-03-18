package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.ThanhToanView;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.ThanhToanViewRepo;

@Service
public class ThanhToanViewService {

	@Autowired
	private ThanhToanViewRepo dao;

	public List<ThanhToanView> getAllThanhToanViewTrangThai(int trangThai){
		return dao.findByTrangThai(trangThai);
	}
	public List<ThanhToanView> getAllTrangThaiAndHoTen(int trangThai, String hoTen){
		return dao.findByTrangThaiAndHoTenContaining(trangThai, hoTen);
	}
	public List<ThanhToanView> getAllMaHDAndMaHV(int maHD, int maHV){
		return dao.findByMaHDAndMaHV(maHD, maHV);
	}
	public List<ThanhToanView> getAllMaHD(int maHD){
		return dao.findByMaHD(maHD);
	}

}
