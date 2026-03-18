package com.QuanLyKhoaHoc.QuanLyKhoaHoc.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.GiangVien;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.LopHoc;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.ThoiKhoaBieu;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.GiangVienService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.KhoaHocService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.LopHocService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.LopHocViewService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.ThoiKhoaBieuService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class GiangVienController {

	@Autowired
	private GiangVienService gvbo;
	@Autowired
	private LopHocService lhbo;
	@Autowired
	private KhoaHocService khbo;
	@Autowired
	private ThoiKhoaBieuService tkbbo;

	@Autowired
	private LopHocViewService lhvbo;

	@GetMapping("/TrangChuGiangVien")
	public String giaodienTrangChuGV(HttpServletRequest request) {
		GiangVien gv = (GiangVien) request.getSession().getAttribute("gv");
		if (gv == null) {
			return "redirect:/DangNhapGiangVien";
		}
		request.setAttribute("ds", lhbo.getLopDayTrongTuan(gv.getMaGV()));
		return "tcgiangvien";
	}

	@GetMapping("/DangNhapGiangVien")
	public String giaodienDangNhap() {
		return "dangnhapgv";
	}

	@PostMapping("/DangNhapGiangVien")
	public String xuLyDangNhap(HttpServletRequest request) {
		String email = (String) request.getParameter("txtEmail");
		String pass = (String) request.getParameter("txtPass");
		GiangVien gv = gvbo.getGiangVien(email, pass);
		if (gv == null) {
			request.setAttribute("tb", "Thông tin đăng nhập không chính xác!!!");
			return "dangnhapgv";
		} else {
			request.getSession().setAttribute("gv", gv);
			return "redirect:/TrangChuGiangVien";
		}

	}

	@GetMapping("/QuanLyLichDay")
	public String giaodienDangKyLopHoc(HttpServletRequest request) throws Exception {

		GiangVien gv = (GiangVien) request.getSession().getAttribute("gv");
		if (gv == null)
			return "redirect:/DangNhapGiangVien";

		request.setAttribute("dsKH", khbo.getAllKH());
		request.setAttribute("dsLop", lhbo.getAllLopHocGiangVien(gv.getMaGV()));

		return "quanlylichday";
	}

	@PostMapping("/QuanLyLichDay")
	public String xulyDangKyLopHoc(HttpServletRequest request) throws Exception {

		GiangVien gv = (GiangVien) request.getSession().getAttribute("gv");

		if (gv == null)
			return "redirect:/DangNhapGiangVien";

		request.setAttribute("dsKH", khbo.getAllKH());

		request.setAttribute("dsLop", lhbo.getAllLopHocGiangVien(gv.getMaGV()));
		if (request.getParameter("action") != null) {
			String str = request.getParameter("action");
			String[] lst = str.trim().split("_");
			int maLop = Integer.parseInt(lst[1]);
			lhbo.delete(maLop);
			request.setAttribute("tb", "Xóa thành công!!!");
			request.setAttribute("dsLop", lhbo.getAllLopHocGiangVien(gv.getMaGV()));
			return "quanlylichday";
		}

		LopHoc lh = new LopHoc();

		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		String ngayBatDau = request.getParameter("ngayBatDau");
		if (ngayBatDau != null) {
			lh.setNgayBatDau(dd.parse(ngayBatDau));
		}
		Date ngayNi = new Date();

		if (lh.getNgayBatDau().before(ngayNi)) {
			request.setAttribute("tb", "Ngày bắt đầu phải nằm trong khoảng thời gian sắp tới!!!");
			return "quanlylichday";
		}
		String strGioBD = request.getParameter("gioBatDau");
		String strGioKT = request.getParameter("gioKetThuc");
		if (strGioBD.isEmpty() && strGioKT.isEmpty()) {
			return "Không được bỏ trống thời gian dạy";
		}
		String hocPhi = request.getParameter("hocPhi");
		Time gioBD = Time.valueOf(strGioBD + ":00");
		Time gioKT = Time.valueOf(strGioKT + ":00");
		lh.setTenLop(request.getParameter("tenLop"));
		lh.setMaGV(gv.getMaGV());
		lh.setMaKH(request.getParameter("maKH"));
		lh.setGhiChu(request.getParameter("ghiChu"));
		lh.setSoTuanHoc(Integer.parseInt(request.getParameter("soTuanHoc")));
		lh.setTrangThai(true);
		lh.setHocPhi(Integer.parseInt(hocPhi));
		Calendar cal = Calendar.getInstance();
		cal.setTime(lh.getNgayBatDau());
		if (!gioKT.after(gioBD)) {
			request.setAttribute("tb", "Giờ kết thúc phải lớn hơn giờ bắt đầu");
			return "quanlylichday";
		} else {
			lhbo.save(lh);
			ThoiKhoaBieu tkb = new ThoiKhoaBieu();
			tkb.setGioBatDau(gioBD);
			tkb.setGioKetThuc(gioKT);
			tkb.setMaLop(lh.getMaLop());
			tkb.setNgayHoc(cal.getTime());
			tkb.setTuan(1);
			tkbbo.save(tkb);
		}
		request.setAttribute("dsLop", lhbo.getAllLopHocGiangVien(gv.getMaGV()));

		return "quanlylichday";
	}

	@GetMapping("/ChiTietLichDay")
	public String truyCapSaiCTLD() {
		return "redirect:/QuanLyLichDay";
	}

	@GetMapping("/ChiTietLichDay/{maLop}")
	public String giaodienChiTietLichDay(@PathVariable int maLop, HttpServletRequest request) throws Exception {
		GiangVien gv = (GiangVien) request.getSession().getAttribute("gv");
		if (gv == null) {
			return "redirect:/DangNhapGiangVien";
		}

		if (request.getSession().getAttribute("tb") != null) {
			request.setAttribute("tb", (String) (request.getSession().getAttribute("tb")));
			request.getSession().removeAttribute("tb");
		}
		LopHoc lh = lhbo.getLopMaLHAndMaGV(maLop, gv.getMaGV());
		if (lh == null) {
			return "chitietlichday";
		}
		List<ThoiKhoaBieu> dsTKB = tkbbo.getAllTKBMaLop(maLop);

		Map<Integer, List<ThoiKhoaBieu>> mapTuan = new LinkedHashMap<Integer, List<ThoiKhoaBieu>>();
		for (ThoiKhoaBieu tkb : dsTKB) {
			mapTuan.computeIfAbsent(tkb.getTuan(), k -> new ArrayList<>()).add(tkb);
		}

		request.setAttribute("mapTuan", mapTuan);
		return "chitietlichday";
	}

	@PostMapping("/ChiTietLichDay/{maLop}")
	public String xulyLichDay(@PathVariable int maLop, HttpServletRequest request) throws Exception {
		GiangVien gv = (GiangVien) request.getSession().getAttribute("gv");
		if (gv == null) {
			return "redirect:/DangNhapGiangVien";
		}
		String action = request.getParameter("action");
		System.out.println(action);
		String[] lst = action.trim().split("_");
		if (lst.length < 2) {
			LopHoc lh = lhbo.getLopMaLHAndMaGV(maLop, gv.getMaGV());
			if (lh == null) {
				return "redirect:/ChiTietLichDay/" + maLop;
			}

			String d = request.getParameter("ngayHoc");
			Date ngayHoc = null;
			if (d != null && !d.isEmpty()) {
				SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
				ngayHoc = dd.parse(d);
			} else {
				request.getSession().setAttribute("tb", "Ngay học không được để trống!!!");
				return "redirect:/ChiTietLichDay/" + maLop;
			}
			String strGioBD = request.getParameter("gioBatDau");
			String strGioKT = request.getParameter("gioKetThuc");

			if (strGioBD == null || strGioBD.isEmpty() || strGioKT == null || strGioKT.isEmpty()) {
				request.setAttribute("tb", "Bạn phải nhập giờ bắt đầu và giờ kết thúc");
				return "redirect:/ChiTietLichDay/" + maLop;
			}

			Time gioBD = Time.valueOf(strGioBD + ":00");
			Time gioKT = Time.valueOf(strGioKT + ":00");

			// Date ngayBatDau = lh.getNgayBatDau();
			if (!gioKT.after(gioBD)) {
				request.getSession().setAttribute("tb", "Giờ kết thúc phải lớn hơn giờ bắt đầu");
				return "redirect:/ChiTietLichDay/" + maLop;
			} else {
				request.getSession().setAttribute("tb",
						tkbbo.themBuoiHoc(ngayHoc, maLop, gioBD, gioKT, lh.getNgayBatDau()));
			}
		} else if (lst[0].equals("xoa")) {
			tkbbo.xoaMaTKB(Integer.parseInt(lst[1]));
			request.getSession().setAttribute("tb", "Xóa Thành công!!!");
		}

		return "redirect:/ChiTietLichDay/" + maLop;
	}

	@GetMapping("QuanLyLopDay")
	public String giaodienQuanLyLopDay(HttpServletRequest request) {
		if (request.getSession().getAttribute("gv") == null) {
			return "redirect:/DangNhapGiangVien";
		}
		GiangVien gv = (GiangVien) request.getSession().getAttribute("gv");
		if (gv == null) {
			return "redirect:/DangNhapGiangVien";
		}

		request.setAttribute("ds", lhbo.getAllLopHocGiangVien(gv.getMaGV()));
		return "quanlylopday";
	}

	@GetMapping("/ChiTietLopHoc/{maLop}")
	public String giaodienChiTietLopHoc(@PathVariable int maLop, HttpServletRequest request) {
		GiangVien gv = (GiangVien) request.getSession().getAttribute("gv");
		if (gv == null) {
			return "redirect:/DangNhapGiangVien";
		}
		if (request.getSession().getAttribute("tb") != null) {
			request.setAttribute("tb", request.getSession().getAttribute("tb"));
			request.getSession().removeAttribute("tb");
		}
		request.setAttribute("maLop", maLop);
		LopHoc lh = lhbo.getLopMaLHAndMaGV(maLop, gv.getMaGV());
		request.setAttribute("lop", lh);
		return "chitietlopday";
	}

	@PostMapping("/ChiTietLopHoc/{maLop}")
	public String xulyChiTietLopHoc(@PathVariable int maLop, HttpServletRequest request) {
		GiangVien gv = (GiangVien) request.getSession().getAttribute("gv");
		if (gv == null) {
			return "redirect:/DangNhapGiangVien";
		}
		String ghiChu = request.getParameter("ghiChu");
		if (ghiChu != null || ghiChu != "") {
			LopHoc lh = lhbo.getLopMaLHAndMaGV(maLop, gv.getMaGV());
			lh.setGhiChu(ghiChu);
			lhbo.save(lh);
			request.getSession().setAttribute("tb", "Đã Cập nhật ghi chú!!!");
		}
		return "redirect:/ChiTietLopHoc/" + maLop;

	}

	@GetMapping("/DanhSachHocVien/{maLop}")
	public String giaodienDanhSachHocVien(@PathVariable int maLop, HttpServletRequest request) {
		GiangVien gv = (GiangVien) request.getSession().getAttribute("gv");
		if (gv == null) {
			return "redirect:/DangNhapGiangVien";
		}
		if (request.getSession().getAttribute("tb") != null) {
			request.setAttribute("tb", request.getSession().getAttribute("tb"));
			request.getSession().removeAttribute("tb");
		}
		request.setAttribute("maLop", maLop);
		LopHoc lh = lhbo.getLopMaLHAndMaGV(maLop, gv.getMaGV());
		request.setAttribute("ds", lhvbo.getAllByMaLH(lh.getMaLop()));
		return "danhsachhocvien";
	}

	@GetMapping("/ThongTinGiangVien")
	public String thongTinGV(HttpSession session, HttpServletRequest request) {
		GiangVien gv = (GiangVien) session.getAttribute("gv");
		if (gv == null)
			return "redirect:/DangNhapGiangVien";

		request.setAttribute("gv", gv);
		return "thongtingiangvien";
	}

	@PostMapping("/ThongTinGiangVien")
	public String capNhatGV(@RequestParam String tenGV, @RequestParam String email, @RequestParam String sdt,
			@RequestParam String pass, @RequestParam String chuyenNganh, HttpSession session,
			HttpServletRequest request) {

		GiangVien gv = (GiangVien) session.getAttribute("gv");
		if (gv == null)
			return "redirect:/DangNhapGiangVien";

		gv.setTenGV(tenGV);
		gv.setEmail(email);
		gv.setSdt(sdt);
		gv.setPass(pass);
		gv.setChuyenNganh(chuyenNganh);

		gvbo.save(gv);

		request.setAttribute("gv", gv);
		request.setAttribute("tb", "Cập nhật thông tin thành công!");

		return "thongtingiangvien";
	}
	@GetMapping("/DangXuatGV")
	public String dxGV(HttpSession session) {
		session.removeAttribute("gv");
		return "redirect:/DangNhapGiangVien";
	}

}
