package com.QuanLyKhoaHoc.QuanLyKhoaHoc.model;

import java.util.Date;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "HoaDonView")
public class HoaDonView {
	@Id
	@Column(name = "MaHD")
	private int maHD;

	@Column(name = "MaHV")
	private int maHV;

	@Column(name = "HoTen")
	private String hoTen;

	@Column(name = "NgayThanhToan")
	private Date ngayThanhToan;

	public int getMaHD() {
		return maHD;
	}

	public void setMaHD(int maHD) {
		this.maHD = maHD;
	}

	public int getMaHV() {
		return maHV;
	}

	public void setMaHV(int maHV) {
		this.maHV = maHV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public Date getNgayThanhToan() {
		return ngayThanhToan;
	}

	public void setNgayThanhToan(Date ngayDongHocPhi) {
		this.ngayThanhToan = ngayDongHocPhi;
	}


}
