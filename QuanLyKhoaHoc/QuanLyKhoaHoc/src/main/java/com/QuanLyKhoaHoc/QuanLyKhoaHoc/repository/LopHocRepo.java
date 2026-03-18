package com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.LopHoc;

public interface LopHocRepo extends JpaRepository<LopHoc, Integer>{
	List<LopHoc> findByMaKHAndTrangThai(String makh, boolean trangthai);
	List<LopHoc> findByMaGV(int maGV);
	LopHoc findByMaLopAndMaGV(int maLop,int maGV);
	LopHoc findByMaLop(int maLop);
	List<LopHoc> findByMaGVAndTrangThaiTrueAndNgayBatDauLessThanEqualOrderByNgayBatDau(
	        int maGV,
	        Date endOfWeek
	);

	@Query("SELECT lh FROM LopHoc lh " +
		       "JOIN DangKy dk ON lh.maLop = dk.maLop " +
		       "WHERE dk.maHV = :maHV " +
		       "AND lh.ngayBatDau <= :endOfWeek " +
		       "And dk.trangThai = 1 "+
		       "ORDER BY lh.ngayBatDau")
		List<LopHoc> findLopHocByHocVienAndEndOfWeek(
		    @Param("maHV") int maHV,
		    @Param("endOfWeek") Date endOfWeek
		);
	}