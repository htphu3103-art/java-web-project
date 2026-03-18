package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.KhoaHoc;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.KhoaHocRepo;

@Service
public class KhoaHocService {
	@Autowired
	private KhoaHocRepo idao;
	public List<KhoaHoc> getAllKH() {
		return idao.findAll();
	}
}
