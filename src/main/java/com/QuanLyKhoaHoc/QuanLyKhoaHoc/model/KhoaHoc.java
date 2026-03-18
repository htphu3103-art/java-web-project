package com.QuanLyKhoaHoc.QuanLyKhoaHoc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "KhoaHoc")
public class KhoaHoc {
@Id
@Column(name = "MaKH", length = 50)
private String maKH;

@Column(name = "TenKH")
private String tenKH;

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





}
