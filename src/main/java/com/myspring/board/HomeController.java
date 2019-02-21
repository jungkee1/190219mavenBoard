package com.myspring.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import com.myspring.model.CommentDTO;
import com.myspring.model.PagingAction;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Resource(name="bService")//Autowired�� ���� ����.. �̸����� ã�´�
	private BoardService bService;
	
	@Resource(name="page")
	private PagingAction page;
	
	
	
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
	public String list(Model model, String pageNum) {	 //�˻��ϱ�� �и� ���״µ� �������� ����.. ������ ���⿡ �˻��ϱ⵵ ���� ������ �˻��������� ����¡�� �ڵ����� �̷������ �� ���� �غ���
		if(pageNum==null)pageNum="1"; //������ ��ȣ �޾� ���°� ������ �׳� 1������
		int pageSize = 5;
		int currentPage=Integer.parseInt(pageNum); //���� Ŭ���ؼ� �Ѿ���� ������ (Ȥ�� ���ٸ� �ʱ�ȭ�� 1)
		int count = bService.count(); //�Խñ� �� (�迭 ũ��� ���� ���� ������ �ڵ� ������ �̰� ���������� db���� �ۿ��°� ���� ���������� �� ������ ������ db���� �ۿ��� �ɷ� 
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = startRow+pageSize-1;
		if(endRow > count ) endRow = count; //�Խñ� �� �������� �ش� �������� endRow ��� ���� Ŭ ���� ������ ���� endRow���� �ǰ� �س��� ��ġ
		int number = count-(currentPage-1)*pageSize; //����¡ �� �� �Խñ� ��ȣ (����¡ �Խñ� ������ ����)
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm.put("startRow", startRow); // <String, Integer>
		hm.put("endRow", endRow);
		
		List<BoardDTO> dto = bService.list(hm); //��ü���� dao�� ���� ���� + ����¡�� ���� HasmMap ������ �Ѿ�� ������������ �����ؼ� ��������
		String pageHtml = page.paging(count, pageSize, currentPage);
		model.addAttribute("dto",dto);
		model.addAttribute("count",count);
		model.addAttribute("number",number);
		model. addAttribute("pageHtml", pageHtml);
		return "list"; //�ڵ����� list.jsp�� ���� -> ���⼭ return�� �����ص� �ڵ����� ������ RequestMapping�� �ִ� �̸��� ã�Ƽ� list.jsp�� ��, �������� ���� �����־���
	}						//�׳� list�� ���� ������ �����ϴ°Ű� redirect: ���ؼ� ���� ������ ���� ���� ����
	
	//�󼼺���
	@GetMapping("/detail")
	public String detail(int seq, Model model) {
		BoardDTO dto = bService.detail(seq);
		model.addAttribute("dto",dto); 
		return "detail";
	}
	
	//�����ϱ�
	@PostMapping("/update")
	public String update(BoardDTO dto, String password1) {
		if(password1.equals(dto.getPassword()));{
			bService.update(dto);
			return "redirect:list";
		}	
	}
	
	//�����ϱ�
	@RequestMapping("/delete")
	public String delete(int seq) {
		bService.delete(seq);
		return "redirect:list";
	}
	
	//�˻��ϱ�
	@RequestMapping("/search")
	public String search(Model model, String field, String word) {  //�˻��ϱ�� �и� ���״µ� �������� ����.. ������ ���⿡ �˻��ϱ⵵ ���� ������ �˻��������� ����¡�� �ڵ����� �̷������ �� ���� �غ���
		List<BoardDTO> dto = bService.search(field, word);
		model.addAttribute("dto",dto);
		return "list";
	}
	
	//��۰˻���
	@RequestMapping("/replyForm")
	public String reply(Model model, int groups, int levels, int steps) {
		model.addAttribute("groups", groups);
		model.addAttribute("levels", levels);
		model.addAttribute("steps", steps);
		return "reply";
	}
	//��۾���
	@RequestMapping(value="/replyForm", method=RequestMethod.POST)
	public String reply(BoardDTO dto) {
		bService.reply(dto);
		return "redirect:list";
	}
	
	//��۾���
	@RequestMapping("/comment")
	public String comment(String comment, String cName) {
		System.out.println(comment);
		System.out.println(cName);
		bService.comment(comment, cName);
		return "redirect:commentList";
	}
	//��۹ٷ� �Ѹ���(���ڸ��� �Ϸ� ������)
	@RequestMapping("commentList")
	public List<CommentDTO> commentList(HttpServletResponse resp) {
		List<CommentDTO> dto = bService.commentList(); //�̰� �׳� ���⼭ JSON���ϰ� ������� for each ������ ��
		JSONArray jarr = new JSONArray(); //json maven repository���� �߰� �ϰ� ��� dependency
		for(CommentDTO dto1 : dto) { //dto�� dto1�� ��´�. 
			JSONObject obj = new JSONObject();
			obj.put("cName", dto1.getcName());
			obj.put("msg", dto1.getMsg());
			obj.put("regdate", dto1.getRegdate());
			obj.put("seq", dto1.getSeq());
			jarr.add(obj);
		}
		resp.setContentType("text/html; charset=utf-8");
		try {
			PrintWriter out = resp.getWriter();
			out.println(jarr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 //jsp ���Ͽ����� �ٷ� out ������ �� ������ java������ �̰� �ؼ� ��밡��
		return null;
	} 
	
}
