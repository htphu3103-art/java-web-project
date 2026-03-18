package com.QuanLyKhoaHoc.QuanLyKhoaHoc.model;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Immutable
@Table(name = "ThongKeView")
public class ThongKeView {
	@Id
	@Column(name = "MaKH")
	private String maKH;

	@Column(name = "TenKH")
	private String tenKH;

	@Column(name = "TongHocVien")
	private long tongHocVien;

	@Column(name = "DoanhThu")
	private long doanhThu;

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public long getTongHocVien() {
		return tongHocVien;
	}

	public void setTongHocVien(long tongHocVien) {
		this.tongHocVien = tongHocVien;
	}

	public long getDoanhThu() {
		return doanhThu;
	}

	public void setDoanhThu(long doanhThu) {
		this.doanhThu = doanhThu;
	}


}
