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
	
	@Resource(name="bService")//Autowired와 같은 개념.. 이름으로 찾는다
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
	public String list(Model model, String pageNum) {	 //검색하기는 분리 시켰는데 가독성을 위해.. 다음에 여기에 검색하기도 같이 넣으면 검색했을때도 페이징이 자동으로 이루어진다 꼭 연습 해볼것
		if(pageNum==null)pageNum="1"; //페이지 번호 받아 오는게 없으면 그냥 1번으로
		int pageSize = 5;
		int currentPage=Integer.parseInt(pageNum); //현재 클릭해서 넘어오는 페이지 (혹은 없다면 초기화값 1)
		int count = bService.count(); //게시글 수 (배열 크기로 구할 수도 있지만 코드 순서상 이건 독립적으로 db에서 퍼오는게 추후 유지보수에 더 유리해 보여서 db에서 퍼오는 걸로 
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = startRow+pageSize-1;
		if(endRow > count ) endRow = count; //게시글 총 개수보다 해당 페이지내 endRow 산술 값이 클 때는 마지막 글이 endRow값이 되게 해놓는 장치
		int number = count-(currentPage-1)*pageSize; //페이징 한 후 게시글 번호 (페이징 게시글 갯수와 관련)
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm.put("startRow", startRow); // <String, Integer>
		hm.put("endRow", endRow);
		
		List<BoardDTO> dto = bService.list(hm); //전체보기 dao로 가는 여정 + 페이징을 위한 HasmMap 가지고 넘어가서 페이지당으로 정렬해서 뽑히게함
		String pageHtml = page.paging(count, pageSize, currentPage);
		model.addAttribute("dto",dto);
		model.addAttribute("count",count);
		model.addAttribute("number",number);
		model. addAttribute("pageHtml", pageHtml);
		return "list"; //자동으로 list.jsp로 간다 -> 여기서 return을 생략해도 자동으로 위에서 RequestMapping에 있는 이름을 찾아서 list.jsp로 감, 가독성을 위해 적어주었음
	}						//그냥 list로 가면 연결을 유지하는거고 redirect: 로해서 가면 연결을 끊고 새로 간다
	
	//상세보기
	@GetMapping("/detail")
	public String detail(int seq, Model model) {
		BoardDTO dto = bService.detail(seq);
		model.addAttribute("dto",dto); 
		return "detail";
	}
	
	//수정하기
	@PostMapping("/update")
	public String update(BoardDTO dto, String password1) {
		if(password1.equals(dto.getPassword()));{
			bService.update(dto);
			return "redirect:list";
		}	
	}
	
	//삭제하기
	@RequestMapping("/delete")
	public String delete(int seq) {
		bService.delete(seq);
		return "redirect:list";
	}
	
	//검색하기
	@RequestMapping("/search")
	public String search(Model model, String field, String word) {  //검색하기는 분리 시켰는데 가독성을 위해.. 다음에 여기에 검색하기도 같이 넣으면 검색했을때도 페이징이 자동으로 이루어진다 꼭 연습 해볼것
		List<BoardDTO> dto = bService.search(field, word);
		model.addAttribute("dto",dto);
		return "list";
	}
	
	//답글검색폼
	@RequestMapping("/replyForm")
	public String reply(Model model, int groups, int levels, int steps) {
		model.addAttribute("groups", groups);
		model.addAttribute("levels", levels);
		model.addAttribute("steps", steps);
		return "reply";
	}
	//답글쓰기
	@RequestMapping(value="/replyForm", method=RequestMethod.POST)
	public String reply(BoardDTO dto) {
		bService.reply(dto);
		return "redirect:list";
	}
	
	//댓글쓰기
	@RequestMapping("/comment")
	public String comment(String comment, String cName) {
		System.out.println(comment);
		System.out.println(cName);
		bService.comment(comment, cName);
		return "redirect:commentList";
	}
	//댓글바로 뿌리기(쓰자마자 일로 내려옴)
	@RequestMapping("commentList")
	public List<CommentDTO> commentList(HttpServletResponse resp) {
		List<CommentDTO> dto = bService.commentList(); //이거 그냥 여기서 JSON안하고 들고나가서 for each 돌려도 됨
		JSONArray jarr = new JSONArray(); //json maven repository에서 추가 하고 사용 dependency
		for(CommentDTO dto1 : dto) { //dto를 dto1에 담는다. 
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
		}	 //jsp 파일에서는 바로 out 내보낼 수 있지만 java에서는 이거 해서 사용가능
		return null;
	} 
	
}
