package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.ThongKeView;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.ThongKeViewRepo;

@Service
public class ThongKeViewService {

	@Autowired
	private ThongKeViewRepo dao;

	public List<ThongKeView> getAll() {
		return dao.findAll();

	}
}
