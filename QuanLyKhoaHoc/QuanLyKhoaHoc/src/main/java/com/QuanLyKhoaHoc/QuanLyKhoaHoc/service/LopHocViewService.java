package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.LopHocView;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.ThoiKhoaBieu;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.LopHocViewRepo;

@Service
public class LopHocViewService {

	@Autowired
	private LopHocViewRepo dao;

	@Autowired
	private ThoiKhoaBieuService tkbbo;



	public List<LopHocView> getAllByMaHV(int mahv){
		List<LopHocView> ds = dao.findByMaHV(mahv);
		for (LopHocView lhv : ds) {
			List<ThoiKhoaBieu> dstkb = tkbbo.getLichHocTheoTuan(1, lhv.getMaLop());
			lhv.setLichHoc(tkbbo.toStringLichHoc(dstkb));
		}
		return ds;

	}

	public LopHocView getAllByMaLH(int malop) {
		return dao.findByMaLop(malop);

	}
	public LopHocView getAllByMaLHAndMaHV(int maLop,int maHV) {
		return dao.findByMaLopAndMaHV(maLop, maHV);
	}
	public List<LopHocView> getAllByMaHVAndTrangThaiHocPhi(int maHV,int isHP) {
		return dao.findByMaHVAndTrangThaiHocPhi(maHV, isHP);
	}
}
