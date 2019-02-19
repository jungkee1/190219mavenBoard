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
	
	@Resource(name="bService")//Autowired�� ���� ����.. �̸����� ã�´�
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
	
	//�۾���� �̵�
	@RequestMapping("/writeForm")
	public String writeForm() {
		return "writeForm";
	}
	
	//�۾���
	@RequestMapping(value="/writeForm", method=RequestMethod.POST)
	public String writeForm(BoardDTO dto) {
		bService.insert(dto);
		return "redirect:list";
	}
	
	//��ü����
	@RequestMapping("/list")
	public String list(Model model) {
		List<BoardDTO> dto = bService.list();
		model.addAttribute("dto",dto);
		return "list"; //�ڵ����� list.jsp�� ���� -> ���⼭ return�� �����ص� �ڵ����� ������ RequestMapping�� �ִ� �̸��� ã�Ƽ� list.jsp�� ��, �������� ���� �����־���
	}
	
	//�󼼺���
	@GetMapping("detail")
	public String detail(int seq, Model model) {
		BoardDTO dto = bService.detail(seq);
		model.addAttribute("dto",dto);
		return "detail";
	}
	
	//�����ϱ�
	@PostMapping("update")
	public String update(BoardDTO dto, String password1) {
		if(password1.equals(dto.getPassword()));{
			bService.update(dto);
			return "redirect:list";
		}	
	}
	
	//�����ϱ�
	@RequestMapping("delete")
	public String delete(int seq) {
		bService.delete(seq);
		return "redirect:list";
	}
	
}
