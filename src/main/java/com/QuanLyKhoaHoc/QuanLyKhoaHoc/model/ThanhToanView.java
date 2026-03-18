package com.QuanLyKhoaHoc.QuanLyKhoaHoc.model;

import java.util.Date;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "ThanhToanView")
public class ThanhToanView {

	@Id
	@Column(name = "MaDK")
	private int maDK;

	@Column(name = "MaLop")
	private int maLop;

	@Column(name = "MaHV")
	private int maHV;

	@Column(name = "TenLop")
	private String tenLop;

	@Column(name = "HocPhi")
	private long hocPhi;

	@Column(name = "NgayDongHocPhi")
	private Date ngayDongHocPhi;

	@Column(name = "HoTen")
	private String hoTen;

	@Column(name = "TrangThai")
	private int trangThai;

	@Column(name = "MaHD")
	private int maHD;


	public int getMaHD() {
		return maHD;
	}

	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}

	public int getMaLop() {
		return maLop;
	}

	public void setMaLop(int maLop) {
		this.maLop = maLop;
	}

	public int getMaHV() {
		return maHV;
	}

	public void setMaHV(int maHV) {
		this.maHV = maHV;
	}

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public long getHocPhi() {
		return hocPhi;
	}

	public void setHocPhi(long hocPhi) {
		this.hocPhi = hocPhi;
	}

	public Date getNgayDongHocPhi() {
		return ngayDongHocPhi;
	}

	public void setNgayDongHocPhi(Date ngayDongHocPhi) {
		this.ngayDongHocPhi = ngayDongHocPhi;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public int getMaDK() {
		return maDK;
	}

	public void setMaDK(int maDK) {
		this.maDK = maDK;
	}



}
