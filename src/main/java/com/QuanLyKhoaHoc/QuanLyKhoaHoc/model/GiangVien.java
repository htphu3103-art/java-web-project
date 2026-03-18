package com.QuanLyKhoaHoc.QuanLyKhoaHoc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GiangVien")
public class GiangVien {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "MaGV", length = 10)
	private int maGV;
	@Column(name = "TenGV", length = 50)
	private String tenGV;
	@Column(name = "Email", length = 50)
	private String email;
	@Column(name = "SDT", length = 50)
	private String sdt;
	@Column(name = "Pass", length = 50)
	private String pass;
	@Column(name = "ChuyenNganh", length = 50)
	private String chuyenNganh;


	public GiangVien(int maGV, String tenGV, String email, String sdt, String pass, String chuyenNganh) {
		super();
		this.maGV = maGV;
		this.tenGV = tenGV;
		this.email = email;
		this.sdt = sdt;
		this.pass = pass;
		this.chuyenNganh = chuyenNganh;
	}

	public GiangVien() {
		super();
	}

	public int getMaGV() {
		return maGV;
	}

	public void setMaGV(int maGV) {
		this.maGV = maGV;
	}

	public String getTenGV() {
		return tenGV;
	}

	public void setTenGV(String tenGV) {
		this.tenGV = tenGV;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getChuyenNganh() {
		return chuyenNganh;
	}

	public void setChuyenNganh(String chuyenNganh) {
		this.chuyenNganh = chuyenNganh;
	}

}
