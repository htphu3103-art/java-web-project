package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.ThoiKhoaBieu;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.ThoiKhoaBieuRepo;

@Service
public class ThoiKhoaBieuService {
	@Autowired
	private ThoiKhoaBieuRepo dao;

	public void save(ThoiKhoaBieu tkb) {
		dao.save(tkb);
	}

	public List<ThoiKhoaBieu> getAllTKBMaLop(int malop) {
		return dao.findByMaLopOrderByNgayHocAsc(malop);

	}
	public String themBuoiHoc(Date ngayHoc, int maLop, Time gioBatDau, Time gioKetThuc, Date ngayBatDau) {
	    List<ThoiKhoaBieu> ds = getAllTKBMaLop(maLop);

	    if (ds == null || ds.isEmpty()) {
	        return "Lớp chưa có buổi học nào!";
	    }


	    if (ngayHoc.before(ngayBatDau)) {
	        return "Ngày nhập phải sau ngày bắt đầu học!";
	    }


	    long miliGiay = ngayHoc.getTime() - ngayBatDau.getTime();
	    int soTuan = (int) (miliGiay / (1000 * 60 * 60 * 24 * 7)) + 1;


	    ThoiKhoaBieu tkb = new ThoiKhoaBieu();
	    tkb.setMaLop(maLop);
	    tkb.setNgayHoc(ngayHoc);
	    tkb.setGioBatDau(gioBatDau);
	    tkb.setGioKetThuc(gioKetThuc);
	    tkb.setTuan(soTuan);

	    save(tkb);

	    return "Đã bổ sung thêm buổi học tuần " + soTuan;
	}
	public List<ThoiKhoaBieu> getLichHocTheoTuan(int tuan, int maLop) {
			return dao.findByTuanAndMaLopOrderByNgayHocAscGioBatDauAsc(tuan, maLop);

		// TODO Auto-generated method stub

	}

	public String toStringLichHoc(List<ThoiKhoaBieu> ds) {

	        String lichtrongtuan = "";
	        for (ThoiKhoaBieu tkb : ds) {
	        	if(tkb.getTuan() == 1) {

		            Calendar cal = Calendar.getInstance();
		            cal.setTime(tkb.getNgayHoc());
		            String thu = switch(cal.get(Calendar.DAY_OF_WEEK)) {
		                case 1 -> "CN";
		                case 2 -> "Thứ 2";
		                case 3 -> "Thứ 3";
		                case 4 -> "Thứ 4";
		                case 5 -> "Thứ 5";
		                case 6 -> "Thứ 6";
		                case 7 -> "Thứ 7";
		                default -> "";
		            };


		            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		            lichtrongtuan += thu + ": "
		                            + sdf.format(tkb.getGioBatDau())
		                            + "-"
		                            + sdf.format(tkb.getGioKetThuc())
		                            + "<br>";

	        	}
	        }
	        return lichtrongtuan;
	}
	public void xoaMaTKB(int tkb) {
		dao.deleteById(tkb);
	}
	public List<ThoiKhoaBieu> getThoiKhoaBieuTrongTuan(int maLop, int tuanHienTai) {
		return dao.getThoiKhoaBieuTrongTuan(maLop, tuanHienTai);
	}
}
