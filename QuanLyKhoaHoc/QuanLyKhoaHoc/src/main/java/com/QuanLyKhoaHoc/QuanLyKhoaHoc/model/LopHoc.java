package com.QuanLyKhoaHoc.QuanLyKhoaHoc.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "LopHoc")
public class LopHoc {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "MaLop", length = 50)
	private int maLop;

	@Column(name = "TenLop", length = 50)
	private String tenLop;

	@Column(name = "MaGV")
	private int maGV;

	@Column(name = "MaKH", length = 10)
	private String maKH;

	@Column(name = "GhiChu", length = 50)
	private String ghiChu;

	@Column(name = "TrangThai")
	private boolean trangThai;

	@Column(name = "NgayBatDau")
	private Date ngayBatDau;

	@Column(name = "SoTuanHoc")
	private int soTuanHoc;
	
	@Column(name = "HocPhi")
	private long hocPhi;
	
	

	@ManyToOne
	@JoinColumn(name = "MaGV", insertable = false, updatable = false)
	private GiangVien giangVien;

	@Transient
	private String lichHocStr;

	public String getLichHocStr() {
	    return lichHocStr;
	}

	public void setLichHocStr(String lichHocStr) {
	    this.lichHocStr = lichHocStr;
	}



	public long getHocPhi() {
		return hocPhi;
	}

	public void setHocPhi(long hocPhi) {
		this.hocPhi = hocPhi;
	}

	public GiangVien getGiangVien() {
		return giangVien;
	}

	public void setGiangVien(GiangVien giangVien) {
		this.giangVien = giangVien;
	}

	public int getMaGV() {
		return maGV;
	}

	public void setMaGV(int maGV) {
		this.maGV = maGV;
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

	public void setSoTuanHoc(int i) {
		this.soTuanHoc = i;
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

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

}
