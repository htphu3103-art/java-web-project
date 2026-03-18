package com.QuanLyKhoaHoc.QuanLyKhoaHoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.DangKy;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.GiangVien;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.HoaDon;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.LopHoc;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.CaiDatService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.DangKyService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.GiangVienService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.HoaDonService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.HoaDonViewService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.HocVienService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.KhoaHocService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.LopHocService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.LopHocViewService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.ThanhToanViewService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.ThongKeViewService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
	@Autowired
	private HocVienService hvbo;

	@Autowired
	private KhoaHocService khbo;

	@Autowired
	private LopHocService lhbo;

	@Autowired
	private GiangVienService gvbo;

	@Autowired
	private ThanhToanViewService ttvbo;

	@Autowired
	private DangKyService dkbo;

	@Autowired
	private ThongKeViewService tkvbo;

	@Autowired
	private HoaDonViewService hdvbo;

	@Autowired
	private CaiDatService cdbo;

	@Autowired
	private HoaDonService hdbo;

	@Autowired
	private LopHocViewService lhvbo;

	@GetMapping("/TrangChuAdmin")
	public String giaodienGDTrangChuAdmin(HttpServletRequest request) {
		request.setAttribute("ds", tkvbo.getAll());
		return "tcadmin";
	}

	@GetMapping("/QuanLyGiangVien")
	public String giaodienThemMoiGiangVien(HttpServletRequest request) {

		request.setAttribute("ds", gvbo.getAllGV());
		return "quanlygiangvien";
	}

	@PostMapping("/QuanLyGiangVien")
	public String xulyDuLieuGiangVien(HttpServletRequest request) {

		String txtEmail = (String) request.getParameter("txtEmail");
		String action = (String) request.getParameter("action");

		if (action != null) {
			if (action.equals("them")) {
				if (gvbo.kiemTraEmail(txtEmail)) {
					request.setAttribute("tb", "Mỗi giảng viên chỉ được sử dụng 1 email để đăng nhập!!!");
					request.setAttribute("ds", gvbo.getAllGV());
					return "quanlygiangvien";
				}
				String txtTenGV = (String) request.getParameter("txtTenGV");
				String txtSDT = (String) request.getParameter("txtSDT");
				String txtPass = (String) request.getParameter("txtPass");
				String txtChuyenNganh = (String) request.getParameter("txtChuyenNganh");
				GiangVien gv = new GiangVien();
				// txtTenGV, txtEmail, txtSDT, txtPass, txtChuyenNganh
				gv.setTenGV(txtTenGV);
				gv.setEmail(txtEmail);
				gv.setSdt(txtSDT);
				gv.setSdt(txtSDT);
				gv.setPass(txtPass);
				gv.setChuyenNganh(txtChuyenNganh);
				gvbo.save(gv);
				request.setAttribute("tb", "Thêm mới giảng viên thành công!!!");
				request.setAttribute("ds", gvbo.getAllGV());
				return "quanlygiangvien";
			}
			String[] lst = action.split("_");
			if (lst[0].equals("xoa")) {
				gvbo.delete(Integer.parseInt(lst[1]));
				request.setAttribute("tb", "Xóa thành công!!!");
			}
		}
		request.setAttribute("ds", gvbo.getAllGV());
		return "quanlygiangvien";
	}

	@GetMapping("/ThanhToanChoXuLy")
	public String giaodienThanhToanChoXuLy(HttpServletRequest request) throws Exception {
		// ?trangThai=2&keyword=Hoàng+Trọng+Phú
		String key = request.getParameter("key");
			if (key != null && !key.equals("")) {
				request.setAttribute("ds", hdvbo.getByHoTen(key.trim()));
			} else {
				request.setAttribute("ds", hdvbo.getAll());
			}

		if (request.getSession().getAttribute("tb") != null) {
			request.setAttribute("tb", request.getSession().getAttribute("tb"));
			request.getSession().removeAttribute("tb");
		}
		return "thanhtoanchoxuly";
	}

	@GetMapping("/ChiTietThanhToan/{maHD}")
	public String chitietThanhToan(@PathVariable int maHD, HttpServletRequest request) {
		cdbo.getTB(request, "tb");
		request.setAttribute("ds", ttvbo.getAllMaHD(maHD));
		return "cthdad";
	}

	@PostMapping("/ChiTietThanhToan/{maDK}")
	public String xulyChiTietThanhToan(@PathVariable int maDK, HttpServletRequest request) {
		DangKy dk = dkbo.getByMaDK(maDK);
		int maHD = dk.getMaHD();
		if(request.getParameter("action") != null) {
			if(dk!=null)
			request.getSession().setAttribute("tb", dkbo.XuLyThanhToan(dk, request.getParameter("action")));
		}
		return "redirect:/ChiTietThanhToan/" + maHD;
	}

	@PostMapping("/XuLyThanhToanCho")
	public String xuLyThanhToan(HttpServletRequest request) {
		String action = request.getParameter("action");
		if(action != null) {
			String [] lst = action.trim().split("_");
			if(lst[0].equals("xoa")) {
				request.getSession().setAttribute("tb", hdbo.xoaHd(lst[1]));
				return "redirect:/ThanhToanChoXuLy";
			}
			if(lst[0].equals("duyet")) {
				request.getSession().setAttribute("tb", hdbo.duyetHd(lst[1]));
				return "redirect:/ThanhToanChoXuLy";
			}
		}
		//TODO: process POST request
		return "redirect:/ThanhToanChoXuLy";

	}
	@GetMapping("/DanhSachKhoaHocAD")
	public String dsKhoaHoc(HttpServletRequest request) {
		request.setAttribute("ds", khbo.getAllKH());
		return "dskhoahocad";
	}

	@GetMapping("/AdminDanhSachKhoaHoc/{maKH}")
	public String gdDsKhoaHoc(@PathVariable String maKH, HttpServletRequest request) {
		if (maKH == null) {
			return "redirect:/DanhSachKhoaHoc";
		}
		cdbo.getTB(request, "tb");
		List<LopHoc> dsLop = lhbo.getAllLopHocWithLich(maKH);

		request.setAttribute("maKH", maKH);
		request.setAttribute("ds", dsLop);
		return "dslophocmakhad";
	}
	@GetMapping("/DanhSachHocVienAD/{maKH}/{maLop}")
	public String gdLopHoc(@PathVariable String maKH,
							@PathVariable int maLop,
							HttpServletRequest request)
	{
		if (request.getSession().getAttribute("tb") != null) {
			request.setAttribute("tb", request.getSession().getAttribute("tb"));
			request.getSession().removeAttribute("tb");
		}

		request.setAttribute("ds", lhvbo.getAllByMaLH(maLop));
		return "danhsachhocvienad";
	}

}
