package com.QuanLyKhoaHoc.QuanLyKhoaHoc.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CaiDatService {

	public void getTB(HttpServletRequest request, String tenloaitb) {
		if (request.getSession().getAttribute(tenloaitb) != null) {
			request.setAttribute(tenloaitb, request.getSession().getAttribute(tenloaitb));
			request.getSession().removeAttribute(tenloaitb);
		}
	}
}
