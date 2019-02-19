package com.myspring.board;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.model.BoardDTO;
import com.myspring.model.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Resource(name="bService")//Autowired와 같은 개념.. 이름으로 찾는다
	private BoardService bService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	//글쓰기로 이동
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	//글쓰기
	@RequestMapping(value="/writeForm", method=RequestMethod.POST)
	public String writeForm(BoardDTO dto) {
		bService.insert(dto);
		return "redirect:list";
	}
	
	//전체보기
	@RequestMapping("/list")
	public String list(Model model) {
		List<BoardDTO> dto = bService.list();
		model.addAttribute("dto",dto);
		return "list"; //자동으로 list.jsp로 간다 -> 여기서 return을 생략해도 자동으로 위에서 RequestMapping에 있는 이름을 찾아서 list.jsp로 감, 가독성을 위해 적어주었음
	}
	
	//상세보기
	@GetMapping("detail")
	public String detail(int seq, Model model) {
		BoardDTO dto = bService.detail(seq);
		model.addAttribute("dto",dto);
		return "detail";
	}
	
	//수정하기
	@PostMapping("update")
	public String update(BoardDTO dto, String password1) {
		if(password1.equals(dto.getPassword()));{
			bService.update(dto);
			return "redirect:list";
		}	
	}
	
	//삭제하기
	@RequestMapping("delete")
	public String delete(int seq) {
		bService.delete(seq);
		return "redirect:list";
	}
	
}
