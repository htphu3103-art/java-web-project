package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.HoaDonView;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.HoaDonViewRepo;

@Service
public class HoaDonViewService {
	@Autowired
	private HoaDonViewRepo dao;

	public List<HoaDonView> getByMaHV(int mahv) {
		return dao.findByMaHV(mahv);
	}
	public List<HoaDonView> getAll() {
		return dao.findAll();
	}
	public List<HoaDonView> getByHoTen(String hoTen){
		return dao.findByHoTenContaining(hoTen);
	}
	
}
