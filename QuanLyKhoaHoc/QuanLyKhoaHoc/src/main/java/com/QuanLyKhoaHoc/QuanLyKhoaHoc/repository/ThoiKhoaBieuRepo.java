package com.QuanLyKhoaHoc.QuanLyKhoaHoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.QuanLyKhoaHoc.QuanLyKhoaHoc.model.ThoiKhoaBieu;

public interface ThoiKhoaBieuRepo extends JpaRepository<ThoiKhoaBieu, Integer> {
	List<ThoiKhoaBieu> findByMaLopOrderByNgayHocAsc(int maLop);

	List<ThoiKhoaBieu> findByTuanAndMaLopOrderByNgayHocAscGioBatDauAsc(int tuan,int maLop);

	@Query("SELECT t FROM ThoiKhoaBieu t " +
		       "WHERE t.maLop = :maLop " +
		       "AND (t.tuan = 1 OR t.tuan = :tuanHienTai)")
		List<ThoiKhoaBieu> getThoiKhoaBieuTrongTuan(
		    @Param("maLop") int maLop,
		    @Param("tuanHienTai") int tuanHienTai
		);

}
