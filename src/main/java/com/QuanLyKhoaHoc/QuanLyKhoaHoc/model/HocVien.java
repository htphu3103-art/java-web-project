package com.QuanLyKhoaHoc.QuanLyKhoaHoc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "HocVien")
public class HocVien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaHV")
	private int maHV;

	@Column(name = "HoTen", length = 50)
	private String hoTen;

	@Column(name = "SDT", length = 10)
	private String sdt;

	@Column(name = "Email", length = 50)
	private String email;

	@Column (name = "Pass", length = 250)
	private String pass;

	@Column(name = "VerificationCode")
	private String verificationCode;

	@Column(name = "Verified")
	private boolean verified = false;


	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
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

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


}
