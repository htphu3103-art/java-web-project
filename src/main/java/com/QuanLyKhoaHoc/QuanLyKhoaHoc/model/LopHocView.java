package com.QuanLyKhoaHoc.QuanLyKhoaHoc.model;

import java.util.Date;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Immutable
@Table(name = "LopHocView")
public class LopHocView {

	@Id
	@Column(name = "MaLop")
	private int maLop;

	@Column(name = "TenLop")
	private String tenLop;

	@Column(name = "TenGV")
	private String tenGV;

	@Column(name = "TenKH")
	private String tenKH;

	@Column(name = "NgayBatDau")
	private Date ngayBatDau;

	@Column(name = "SoTuanHoc")
	private int soTuanHoc;

	@Column(name = "TrangThai")
	private boolean trangThai;

	@Column(name = "HocPhi")
	private double hocPhi;

	@Column(name = "NgayDangKy")
	private Date ngayDangKy;

	@Column(name = "TrangThaiHocPhi")
	private int trangThaiHocPhi;

	@Column(name = "NgayDongHocPhi")
	private Date ngayDongHocPhi;

	@Column(name = "MaHV")
	private int maHV;


	@Column(name = "HoTen")
	private String hoTen;

	@Column(name = "GhiChu")
	private String ghiChu;

	@Transient
	private String lichHoc;

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}



	public String getLichHoc() {
		return lichHoc;
	}

	public void setLichHoc(String lichHoc) {
		this.lichHoc = lichHoc;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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

	public String getTenLop() {
		return tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public String getTenGV() {
		return tenGV;
	}

	public void setTenGV(String tenGV) {
		this.tenGV = tenGV;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public int getSoTuanHoc() {
		return soTuanHoc;
	}

	public void setSoTuanHoc(int soTuanHoc) {
		this.soTuanHoc = soTuanHoc;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public double getHocPhi() {
		return hocPhi;
	}

	public void setHocPhi(double hocPhi) {
		this.hocPhi = hocPhi;
	}

	public Date getNgayDangKy() {
		return ngayDangKy;
	}

	public void setNgayDangKy(Date ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}


	public Date getNgayDongHocPhi() {
		return ngayDongHocPhi;
	}

	public void setNgayDongHocPhi(Date ngayDongHocPhi) {
		this.ngayDongHocPhi = ngayDongHocPhi;
	}

	public int getTrangThaiHocPhi() {
		return trangThaiHocPhi;
	}

	public void setTrangThaiHocPhi(int trangThaiHocPhi) {
		this.trangThaiHocPhi = trangThaiHocPhi;
	}


}
