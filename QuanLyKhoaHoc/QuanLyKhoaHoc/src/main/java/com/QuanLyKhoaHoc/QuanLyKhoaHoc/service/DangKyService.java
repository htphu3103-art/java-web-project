package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.DangKy;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.HoaDon;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.DangKyRepo;

@Service
public class DangKyService {
	@Autowired
	private DangKyRepo dao;

	@Autowired
	private HoaDonService hdbo;

	public void save(DangKy dk) {
		dao.save(dk);
	}
	public boolean existsByMaLop(int maLop,int maHV) {
		return dao.existsByMaLopAndMaHV(maLop,maHV);
	}
	public DangKy getDangKyMaHVAndMaLop(int maHV, int maLop) {
		return dao.findByMaHVAndMaLop(maHV, maLop);
	}
	public String getXuLyDangKy(String dsMaLop, int maHV) {
		String[] arrMaLop = dsMaLop.split(",");
		HoaDon hd = new HoaDon();
		hd.setNgayThanhToan(new Date());
		hd.setMaHV(maHV);
		int maHd = hdbo.save(hd);
	    for (String maLop : arrMaLop) {
	        DangKy dk = getDangKyMaHVAndMaLop(maHV, Integer.parseInt(maLop));
	        if(dk == null) {
	        	return "Lỗi thanh toán " + maLop;
	        } else {
	        	dk.setTrangThai(2);
	        	dk.setNgayDongHocPhi(hd.getNgayThanhToan());
	        	dk.setMaHD(maHd);
	        	save(dk);
	        }

	    }
	    return "Thanh toán thành công";

	}
	public DangKy getByMaDK(int maDK) {
		return dao.findByMaDK(maDK);
	}
	public void delete(DangKy dk) {
		dao.delete(dk);
	}
	public String XuLyThanhToan(DangKy dk, String action) {
		if(action.equals("duyet")) {
			dk.setTrangThai(1);
			save(dk);
			return "Duyệt thành công!!";
		}
		if(action.equals("xoa")) {
			dk.setMaHD(null);
			dk.setTrangThai(0);
			save(dk);
			return "Xóa Thành Công";
		}
		return "Đã có lỗi khi xử lý!!!";

	}
	public void capNhatHoaDonNull() {
		dao.resetTrangThaiChoMaHDNull();
	}
}
