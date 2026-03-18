package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.DangKy;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.HoaDon;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.DangKyRepo;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.HoaDonRepo;

@Service
public class HoaDonService {
	@Autowired
	private HoaDonRepo dao;

	@Autowired
	private DangKyRepo dkdao;

	public int save(HoaDon hd) {
		HoaDon hdnew = dao.save(hd);
		return hdnew.getMaHD();
	}
	public String xoaHd(String mahd) {
		HoaDon hd = dao.findByMaHD(Integer.parseInt(mahd));
		if(hd != null) {
			dao.delete(hd);
			dkdao.resetTrangThaiChoMaHDNull();
			return "Xóa thánh công!!!";
		}
		else {
			return "Lỗi trong quá trình xóa!!!";
		}
	}
	public String duyetHd(String mahd) {
		HoaDon hd = dao.findByMaHD(Integer.parseInt(mahd));
		if(hd != null) {
			List<DangKy> lstdk = dkdao.findByMaHD(hd.getMaHD());
			for (DangKy dangKy : lstdk) {
				dangKy.setTrangThai(1);
				dkdao.save(dangKy);
			}
			return "Duyệt thành công!!!";
		}
		else {
			return "Lỗi trong quá trình xóa!!!";
		}
	}
}
