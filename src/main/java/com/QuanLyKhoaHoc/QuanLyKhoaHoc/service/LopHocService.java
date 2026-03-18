package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.LopHoc;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.ThoiKhoaBieu;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.LopHocRepo;

@Service
public class LopHocService {
	@Autowired
	private LopHocRepo dao;
	@Autowired
	private ThoiKhoaBieuService tkbbo;

	public List<LopHoc> getAllLopHoc() {
		return dao.findAll();
	}

	public List<LopHoc> getAllLopHocMaKH(String makh) {
		return dao.findByMaKHAndTrangThai(makh, true);
	}

	public List<LopHoc> getAllLopHocGiangVien(int maGV) {
		return dao.findByMaGV(maGV);
	}

	public void save(LopHoc lh) {
		dao.save(lh);
	}

	public void delete(int malop) {
		dao.deleteById(malop);
	}

	public LopHoc getLopMaLHAndMaGV(int malop, int magv) {
		return dao.findByMaLopAndMaGV(malop, magv);
	}

	public List<LopHoc> getAllLopHocWithLich(String maKH) {
	    List<LopHoc> dsLop = getAllLopHocMaKH(maKH);

	    for (LopHoc lh : dsLop) {
	        List<ThoiKhoaBieu> tkbList = tkbbo.getAllTKBMaLop(lh.getMaLop());

	        String lichtrongtuan = "";
	        for (ThoiKhoaBieu tkb : tkbList) {
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

	        lh.setLichHocStr(lichtrongtuan);
	    }

	    return dsLop;
	}
	public LopHoc getByMaLop(int maLop) {
		return dao.findByMaLop(maLop);
	}

	public List<LopHoc> getAllLopHocThanhToan(String [] lst){
		List<LopHoc> ds = new ArrayList<LopHoc>();
		for (String malop : lst) {
			LopHoc lh = getByMaLop(Integer.parseInt(malop));
			if(lh != null)
				ds.add(lh);
		}
		return ds;
	}
	public long tongHocPhi(List<LopHoc> ds) {
		long tong = 0;
		for (LopHoc lopHoc : ds) {
			tong += lopHoc.getHocPhi();
		}
		return tong;
	}
	public List<LopHoc> getLopHocTrongTuan(int maHV){

	    Date today = new Date();


	    Calendar cal = Calendar.getInstance();
	    cal.setTime(today);


	    cal.add(Calendar.WEEK_OF_YEAR, 1);
	    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);


	    cal.add(Calendar.DAY_OF_MONTH, - 1);
	    Date endOfWeek = cal.getTime();
	    List<LopHoc> ds = dao.findLopHocByHocVienAndEndOfWeek(maHV, endOfWeek);
	    Date ngayHienTai = new Date();
	    for (LopHoc lopHoc : ds) {
	    	long miliGiay = ngayHienTai.getTime() - lopHoc.getNgayBatDau().getTime();
		    int soTuan = (int) (miliGiay / (1000 * 60 * 60 * 24 * 7)) + 1;
		    List<ThoiKhoaBieu> dstkb = tkbbo.getThoiKhoaBieuTrongTuan(lopHoc.getMaLop(),soTuan);
		    lopHoc.setLichHocStr(tkbbo.toStringLichHoc(dstkb));
		}
	    return ds;
	}
	public List<LopHoc> getLopDayTrongTuan(int maGV){

	    Date today = new Date();


	    Calendar cal = Calendar.getInstance();
	    cal.setTime(today);


	    cal.add(Calendar.WEEK_OF_YEAR, 1);
	    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);


	    cal.add(Calendar.DAY_OF_MONTH, - 1);
	    Date endOfWeek = cal.getTime();
	    List<LopHoc> ds = dao.findByMaGVAndTrangThaiTrueAndNgayBatDauLessThanEqualOrderByNgayBatDau(maGV, endOfWeek);
	    Date ngayHienTai = new Date();
	    for (LopHoc lopHoc : ds) {
	    	long miliGiay = ngayHienTai.getTime() - lopHoc.getNgayBatDau().getTime();
		    int soTuan = (int) (miliGiay / (1000 * 60 * 60 * 24 * 7)) + 1;
		    List<ThoiKhoaBieu> dstkb = tkbbo.getThoiKhoaBieuTrongTuan(lopHoc.getMaLop(),soTuan);
		    lopHoc.setLichHocStr(tkbbo.toStringLichHoc(dstkb));
		}
	    return ds;
	}

}
