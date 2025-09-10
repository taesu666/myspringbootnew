package lx.edu.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lx.edu.springboot.dao.AddrBookDAO;
import lx.edu.springboot.vo.AddrBookVO;

@Controller
public class AddrBookController {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	AddrBookDAO dao;

	@RequestMapping("/addrbook_form.do")
	public String form() {
		return "addrbook_form";//jsp file name
	}

	
	@RequestMapping("/addrbook_list.do")
	public String list(HttpSession session, HttpServletRequest req) throws Exception {
		List<AddrBookVO> list = dao.getDBList();
		req.setAttribute("data", list);
		return "addrbook_list";
	}
	
	
	@RequestMapping("/edit.do")
	public String edit(@RequestParam("abId") int abId ,HttpServletRequest req) throws Exception {
		
		AddrBookVO vo = dao.getDB(abId);
		req.setAttribute("ab", vo);
		return "addrbook_edit_form";//jsp file name
	}
	
	@RequestMapping("/update.do")
	public String update(AddrBookVO vo) {
		System.out.println("vo=" + vo);
		//dao.updateDB(vo);
		return "redirect:addrbook_list.do";
	}
	
	@RequestMapping("/addrbook_editlist.do")
	public String editlist(AddrBookVO ab) throws Exception {
		System.out.println(ab);
		dao.updateDB(ab);
		
		return "redirect:addrbook_list.do";
		
	}
	
	@RequestMapping("/addrbook_insert.do")
	public String insert(AddrBookVO vo) throws Exception {
		//1. 클라이언트가 보낸 정보 꺼내오기
		System.out.println(vo);
		//2. dao 이용해 전달받은 거 보내줌
		dao.insertDB(vo);
		return "redirect:addrbook_list.do";
	}
	
}
