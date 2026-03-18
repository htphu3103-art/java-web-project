package com.QuanLyKhoaHoc.QuanLyKhoaHoc.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.DangKy;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.HoaDon;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.HocVien;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.LopHoc;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository.HoaDonViewRepo;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.CaiDatService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.DangKyService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.EmailService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.HoaDonService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.HoaDonViewService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.HocVienService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.KhoaHocService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.LopHocService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.LopHocViewService;
import com.QuanLyKhoaHoc.QuanLyKhoaHoc.service.ThanhToanViewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HocVienController {
	@Autowired
	private HocVienService hvbo;

	@Autowired
	private KhoaHocService khbo;

	@Autowired
	private LopHocService lhbo;

	@Autowired
	private CaiDatService cdbo;

	@Autowired
	private DangKyService dkbo;

	@Autowired
	private EmailService ebo;

	@Autowired
	private LopHocViewService lhvbo;

	@Autowired
	private HoaDonViewService hdvbo;

	@Autowired ThanhToanViewService ttvbo;


	@GetMapping("/TrangChu")
	public String listSinhVien(HttpServletRequest request) {
		HocVien hv = (HocVien) request.getSession().getAttribute("hv");
		if (hv == null) {
			return "redirect:/DangNhap";
		}
		request.setAttribute("ds", lhbo.getLopHocTrongTuan(hv.getMaHV()));
		return "trangchu";
	}

	@GetMapping("/DangNhap")
	public String formDangNhap() {
		return "dangnhaphv";
	}

	@PostMapping("/DangNhap")
	public String dangNhapHV(HttpServletRequest request, HttpSession session) {
		String email = request.getParameter("txtEmail");
		String pass = request.getParameter("txtPass");
		HocVien hv = hvbo.ktDangNhap(email, pass);
		if (hv == null) {
			request.setAttribute("tb", "Email hoặc mật khẩu không đúng!");
			return "dangnhaphv";
		}
		if (!hv.isVerified()) {
			request.setAttribute("tb", "Bạn chưa xác thực tài khoản, vui lòng kiểm tra email (hiệu lực 24h)!");
			return "dangnhaphv";
		}
		session.setAttribute("hv", hv);
		return "redirect:/TrangChu";
	}

	@GetMapping("DangKyHocVien")
	public String giaodienDangKy(HttpServletRequest request) {
		return "dangkyhocvien";
	}

	@PostMapping("DangKyHocVien")
	public String xuLyDangKy(HttpServletRequest request) {
		String hoTen = request.getParameter("HoTen");
		String sdt = request.getParameter("SDT");
		String email = request.getParameter("Email");
		String pass = request.getParameter("Pass");

		if (hvbo.ktEmail(email)) {
			request.setAttribute("tb", "Email đã tồn tại trong csdl!!!");
			return "dangkyhocvien";
		} else {
			HocVien hv = new HocVien();
			hv.setEmail(email);
			hv.setHoTen(hoTen);
			hv.setPass(pass);
			hv.setSdt(sdt);

			String code = UUID.randomUUID().toString();
			hv.setVerificationCode(code);
			hv.setVerified(false);

			hvbo.save(hv);

			String link = "http://localhost:8080/XacThucEmail?code=" + code;

			ebo.sendVerificationEmail(email, link);

			return "redirect:/DangKyHocVien?success=true";

		}
	}

	@GetMapping("/XacThucEmail")
	public String xacThucEmail(@RequestParam("code") String code, HttpServletRequest request) {
		HocVien hv = hvbo.findByVerificationCode(code);

		if (hv == null) {
			request.setAttribute("tb", "❌ Mã xác thực không hợp lệ!");
			return "xacthucemail";
		}

		hv.setVerified(true);
		hv.setVerificationCode(null);
		hvbo.save(hv);

		request.setAttribute("tb", "✅ Xác thực email thành công! Bạn có thể đăng nhập.");
		return "xacthucemail";
	}

	@GetMapping("/DanhSachKhoaHoc")
	public String dsKhoaHoc(HttpServletRequest request) {
		request.setAttribute("ds", khbo.getAllKH());
		return "dskhoahoc";
	}

	@GetMapping("/DanhSachLopHoc/{maKH}")
	public String dsLopHocOfKhoaHoc(@PathVariable String maKH, HttpServletRequest request) {
		if (maKH == null) {
			return "redirect:/DanhSachKhoaHoc";
		}
		cdbo.getTB(request, "tb");
		List<LopHoc> dsLop = lhbo.getAllLopHocWithLich(maKH);

		request.setAttribute("maKH", maKH);
		request.setAttribute("ds", dsLop);

		return "dslophocmakh";
	}

	@GetMapping("/DangKyHocPhan/{maKH}/{maLop}")
	public String xulyDangKyHocPhan(@PathVariable String maKH, @PathVariable int maLop, HttpServletRequest request) {
		HocVien hv = (HocVien) request.getSession().getAttribute("hv");
		if (hv == null) {
			return "redirect:/DangNhap";
		}
		DangKy dk = new DangKy();
		if (dkbo.existsByMaLop(maLop,hv.getMaHV())) {
			request.getSession().setAttribute("tb", "Bạn đã đăng ký khóa học này rồi");
			return "redirect:/DanhSachLopHoc/" + maKH;
		}
		dk.setTrangThai(0);
		dk.setMaHV(hv.getMaHV());
		dk.setMaLop(maLop);
		dk.setNgayDangKy(new Date());
		dkbo.save(dk);
		request.getSession().setAttribute("tb", "Bạn đã đăng ký thành công khóa học");
		return "redirect:/DanhSachLopHoc/" + maKH;
	}

	@GetMapping("/LopHocDaDangKy")
	public String giaoDienLopHocDaDangKy(HttpServletRequest request) {

		HocVien hv = (HocVien) request.getSession().getAttribute("hv");
		if (hv == null) {
			return "redirect:/DangNhap";
		}
		cdbo.getTB(request, "tb");

		request.setAttribute("ds", lhvbo.getAllByMaHV(hv.getMaHV()));
		return "lophocdadangky";
	}

	@GetMapping("/ThongTinLopHoc/{maLop}")
	public String giaodienLopHoc(@PathVariable int maLop, HttpServletRequest request) {
		HocVien hv = (HocVien) request.getSession().getAttribute("hv");
		if (hv == null) {
			return "redirect:/DangNhap";
		}
		if (request.getParameter("action") != null) {
			if (request.getParameter("action").equals("huydangky")) {
				DangKy dk = dkbo.getDangKyMaHVAndMaLop(hv.getMaHV(), maLop);
				if (dk != null) {
					dkbo.delete(dk);
					request.getSession().setAttribute("tb", "Hủy lớp học thành công!!!");
					return "redirect:/LopHocDaDangKy";
				}
			}
		}
		request.setAttribute("lop", lhvbo.getAllByMaLHAndMaHV(maLop, hv.getMaHV()));
		return "thongtinlophoc";
	}

	@GetMapping("/ThanhToanHocPhi")
	public String giaodienThanhToanHocPhi(HttpServletRequest request) {
		HocVien hv = (HocVien) request.getSession().getAttribute("hv");
		if (hv == null) {
			return "redirect:/DangNhap";
		}
		cdbo.getTB(request, "tb");

		request.setAttribute("ds", lhvbo.getAllByMaHVAndTrangThaiHocPhi(hv.getMaHV(), 0));
		return "thanhtoanhocphi";
	}

	@PostMapping("/XuLyThanhToan")
	public String xulyThanhToan(HttpServletRequest request) {

		HocVien hv = (HocVien) request.getSession().getAttribute("hv");
		if (hv == null) {
			return "redirect:/DangNhap";
		}

		String[] lst = request.getParameterValues("dsMaLop");
		if (lst == null || lst.length < 1) {
			return "redirect:/ThanhToanHocPhi";
		}

		List<LopHoc> dsLop = lhbo.getAllLopHocThanhToan(lst);
		request.setAttribute("dsLop", dsLop);
		request.setAttribute("tongTien", lhbo.tongHocPhi(dsLop));
		request.setAttribute("dsMaLop", lst);

		String noiDungCK = hv.getMaHV() + " " + hv.getHoTen() + " " + String.join(" ", lst);
		System.out.println(noiDungCK);
		request.setAttribute("qrAddInfo", noiDungCK);

		return "xacnhanthanhtoan";
	}

	@PostMapping("/XacNhanThanhToan")
	public String xulyXacNhanThanhToan(HttpServletRequest request) {
		HocVien hv = (HocVien) request.getSession().getAttribute("hv");
		if (hv == null) {
			return "redirect:/DangNhap";
		}
		String dsMaLop = request.getParameter("dsMaLop");
		if (dsMaLop == null) {
			return "redirect:/LopHocDaDangKy";
		}

		request.getSession().setAttribute("tb", dkbo.getXuLyDangKy(dsMaLop, hv.getMaHV()));
		return "redirect:/ThanhToanHocPhi";
	}


	@GetMapping("/LichSuGiaoDich")
	public String lichsuGiaoDich(HttpServletRequest request) {
		HocVien hv = (HocVien) request.getSession().getAttribute("hv");
		if (hv == null)
			return "redirect:/DangNhap";
		request.setAttribute("ds", hdvbo.getByMaHV(hv.getMaHV()));
		return "lichsugiaodichhv";

	}
	@GetMapping("/ChiTietHoaDon/{maHD}")
	public String chitietHoaDon(@PathVariable int maHD, HttpServletRequest request) {
		HocVien hv = (HocVien) request.getSession().getAttribute("hv");
		if (hv == null)
			return "redirect:/DangNhap";
		request.setAttribute("ds", ttvbo.getAllMaHDAndMaHV(maHD, hv.getMaHV()));
		return "cthdhv";
	}


	@GetMapping("/ThongTinHocVien")
	public String thongTinHV(HttpSession session, HttpServletRequest request) {
		HocVien hv = (HocVien) session.getAttribute("hv");
		if (hv == null)
			return "redirect:/DangNhap";
		cdbo.getTB(request, "tb");
		request.setAttribute("hv", hv);
		return "thongtinhocvien";
	}

	@PostMapping("/ThongTinHocVien")
	public String capNhatHV(@RequestParam String hoTen, @RequestParam String sdt, @RequestParam String email,
			@RequestParam String pass, HttpSession session, HttpServletRequest request) {

		HocVien hv = (HocVien) session.getAttribute("hv");
		if (hv == null)
			return "redirect:/DangNhapHocVien";

		hv.setHoTen(hoTen);
		hv.setSdt(sdt);
		hv.setEmail(email);
		hv.setPass(pass);

		hvbo.save(hv);

		session.setAttribute("hv", hv);
		request.getSession().setAttribute("tb", "Cập nhật thông tin học viên thành công!");

		return "redirect:/ThongTinHocVien";
	}
	@GetMapping("/DangXuat")
	public String dxHV(HttpSession session) {
		session.removeAttribute("hv");
		return "redirect:/TrangChu";
	}
	@GetMapping("/DangXuatHV")
	public String dxHV2(HttpSession session) {
		session.removeAttribute("hv");
		return "redirect:/TrangChu";
	}

}
