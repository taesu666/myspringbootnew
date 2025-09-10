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
	
	/*@RequestMapping("/addrbook_list.do")
	public String list(HttpServletRequest req) throws Exception {
		// DAO 불러와서 리스트 넘겨줘야함
		
		List<AddrBookVO> list = dao.getDBList();
		// list를 request에 넣는다.
		req.setAttribute("data", list);		
		return "addrbook_list";
	}*/
	
	@RequestMapping("/addrbook_list.do")
	public String list(HttpSession session, HttpServletRequest req) throws Exception {
	//	if(session.getAttribute("userId") == null) {
	//		return "login";
	//	}
		List<AddrBookVO> list = dao.getDBList();
		req.setAttribute("data", list);
		return "addrbook_list";
	}
	
	
	@RequestMapping("/edit.do")
	public ModelAndView editform(@RequestParam("abId") int abId) throws Exception {
		ModelAndView result = new ModelAndView();		
		AddrBookVO vo = dao.selectDBList(abId);
		result.addObject("ab", vo);
		result.setViewName("addrbook_edit_form");
		return result;//jsp file name
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
