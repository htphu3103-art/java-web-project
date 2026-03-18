package com.QuanLyKhoaHoc.QuanLyKhoaHoc.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DangKy")
public class DangKy {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "MaDK")
	private int maDK;

	@Column(name = "MaHV")
	private int maHV;

	@Column(name = "MaLop")
	private int maLop;

	@Column(name = "NgayDangKy")
	private Date ngayDangKy;

	@Column(name = "NgayDongHocPhi")
	private Date ngayDongHocPhi;

	@Column(name = "TrangThai")
	private int trangThai;

	@Column(name = "MaHD")
	private Integer maHD;


	public Integer getMaHD() {
		return maHD;
	}

	public void setMaHD(Integer maHD) {
		this.maHD = maHD;
	}

	public int getMaDK() {
		return maDK;
	}

	public void setMaDK(int maDK) {
		this.maDK = maDK;
	}

	public Date getNgayDangKy() {
		return ngayDangKy;
	}

	public void setNgayDangKy(Date ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}

	public int getMaHV() {
		return maHV;
	}

	public void setMaHV(int maHV) {
		this.maHV = maHV;
	}

	public int getMaLop() {
		return maLop;
	}

	public void setMaLop(int maLop) {
		this.maLop = maLop;
	}

	public Date getNgayDongHocPhi() {
		return ngayDongHocPhi;
	}

	public void setNgayDongHocPhi(Date ngayDongHocPhi) {
		this.ngayDongHocPhi = ngayDongHocPhi;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}




}
