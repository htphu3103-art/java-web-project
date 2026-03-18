package com.QuanLyKhoaHoc.QuanLyKhoaHoc.model;

import java.sql.Time;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ThoiKhoaBieu")
public class ThoiKhoaBieu {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "MaTKB")
	private int maTKB;

	@Column(name = "MaLop")
	private int maLop;

	@Column(name = "NgayHoc")
	private Date ngayHoc;

	@Column(name = "GioBatDau")
	private Time gioBatDau;

	@Column(name = "GioKetThuc")
	private Time gioKetThuc;

	@Column(name = "Tuan")
	private int tuan;


	public int getMaLop() {
		return maLop;
	}

	public int getTuan() {
		return tuan;
	}

	public void setTuan(int tuan) {
		this.tuan = tuan;
	}

	public void setMaLop(int maLop) {
		this.maLop = maLop;
	}

	public Date getNgayHoc() {
		return ngayHoc;
	}

	public void setNgayHoc(Date ngayHoc) {
		this.ngayHoc = ngayHoc;
	}

	public Time getGioBatDau() {
		return gioBatDau;
	}

	public void setGioBatDau(Time gioBatDau) {
		this.gioBatDau = gioBatDau;
	}

	public Time getGioKetThuc() {
		return gioKetThuc;
	}

	public void setGioKetThuc(Time gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}

	public int getMaTKB() {
		return maTKB;
	}

	public void setMaTKB(int maTKB) {
		this.maTKB = maTKB;
	}

}
