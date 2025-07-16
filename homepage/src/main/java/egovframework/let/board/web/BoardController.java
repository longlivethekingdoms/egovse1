package egovframework.let.board.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.fdl.string.EgovStringUtil;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;
import egovframework.let.temp.service.TempService;
import egovframework.let.temp.service.TempVO;
import egovframework.let.temp2.service.Temp2Service;
import egovframework.let.temp2.service.Temp2VO;

@Controller
public class BoardController {

	@Resource(name = "boardService")
	private BoardService boardService;
	
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertyService;
	
	@RequestMapping(value = "/board/selectList.do")
	public String selectList(@ModelAttribute("searchVO") BoardVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		
		//공지 글
		searchVO.setNoticeAt("Y");
		List<EgovMap> noticeResultList = boardService.selectBoardList(searchVO);
		model.addAttribute("noticeResultList", noticeResultList);
		
		if("IMAGE".equals(searchVO.getBoardType())) {
			searchVO.setPageUnit(propertyService.getInt("imagePageUnit"));
			searchVO.setPageSize(propertyService.getInt("imagePageSize"));
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		//일반 글
		searchVO.setNoticeAt("N");
		List<EgovMap> resultList = boardService.selectBoardList(searchVO);
		model.addAttribute("resultList", resultList);
		
		int totCnt = boardService.selectBoardListCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "board/BoardSelectList";
		
	}
	
	@RequestMapping(value = "/board/select.do")
	public String select(@ModelAttribute("searchVO") BoardVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	model.addAttribute("USER_INFO", user);
	
	BoardVO result = boardService.selectBoard(searchVO);
	
	if("Y".equals(result.getOthbcAt())) {
		if(user == null || user.getId()==null||(!user.getId().equals(result.getFrstRegisterId())&&!"admin".equals(user.getId()))) {
			model.addAttribute("message", "작성자 본인만 확인 가능합니다.");
			return "forward:/board/selectList.do";
		}
	}
	model.addAttribute("result", result);
	return "board/BoardSelect";
	}
	
	//게시물 등록 수정
	@RequestMapping(value = "/board/boardRegist.do")
	public String boardRegist(@ModelAttribute("searchVO") BoardVO boardVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/board/selectList.do";
		}else {
			model.addAttribute("USER_INFO", user);
		}
		
		BoardVO result = new BoardVO();
		if(!EgovStringUtil.isEmpty(boardVO.getBoardId())) {
			result = boardService.selectBoard(boardVO);
			//프런트단에서만 접근제한하는 게 아닌 벡단에서도 한 번 더 처리해야함.
			if(!user.getId().equals(result.getFrstRegisterId()) && !"admin".equals(user.getId())) {
				model.addAttribute("message", "작성자 본인만 확인 가능합니다.");
				return "forward:/board/selectList.do";
			}
		}
		model.addAttribute("result", result);
		
		request.getSession().removeAttribute("sessionBoard");
		
		return "board/BoardRegist";
	}
	
	//게시물 등록
	@RequestMapping(value = "/board/insert.do")
	public String insert(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		if(request.getSession().getAttribute("sessionBoard") != null) {
			return "forward:/board/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null) {
		model.addAttribute("message", "로그인 후 사용가능합니다.");
		return "forward:/board/selectList.do";	
	}
	
	//첨부 파일 가져오기
	List<FileVO> result = null;
	String atchFileId = "";
	
	final Map<String, MultipartFile> files = multiRequest.getFileMap();
	if(!files.isEmpty()) {
		result = fileUtil.parseFileInf(files, "BOARD_", 0, "", "board.fileStorePath");
		atchFileId = fileMngService.insertFileInfs(result);
	}
	searchVO.setAtchFileId(atchFileId);
	
	//사용자IP가져오기
	searchVO.setCreatIp(request.getRemoteAddr());
	searchVO.setUserId(user.getId());
	
	boardService.insertBoard(searchVO);
	
	request.getSession().setAttribute("sessionBoard", searchVO);
	return "forward:/board/selectList.do";
	}
	
	//게시물 수정
	@RequestMapping(value = "/board/update.do")
	public String update(final MultipartHttpServletRequest multiRequest, @ModelAttribute("searchVO") BoardVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		//이중 서브밋 방지
		if(request.getSession().getAttribute("sessionBoard") != null) {
			return "forward:/board/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/board/selectList.do";
		}else if("admin".equals(user.getId())) {
			searchVO.setMngAt("Y");
		}
		
		String atchFileId = searchVO.getAtchFileId();
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		if(!files.isEmpty()) {
			if(EgovStringUtil.isEmpty(atchFileId)) {
				List<FileVO> result = fileUtil.parseFileInf(files, "BOARD_", 0, "", "board.fileStorePath");
				atchFileId =fileMngService.insertFileInfs(result);
				searchVO.setAtchFileId(atchFileId);
			}else
			{
				FileVO fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int cnt = fileMngService.getMaxFileSN(fvo);
				List<FileVO> _result = fileUtil.parseFileInf(files, "BOARD_", cnt, atchFileId, "board.fileStorePath");
				fileMngService.updateFileInfs(_result);
			}
		}
		
		searchVO.setUserId(user.getId());
		
		boardService.updateBoard(searchVO);
		
		//이중 서브밋 방지
		request.getSession().setAttribute("sessionBoard", searchVO);
		return "forward:/board/selectList.do";
	}
	
	//게시물 삭제
	@RequestMapping(value = "/board/delete.do")
	public String delete(@ModelAttribute("searchVO") BoardVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || user.getId() == null) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/board/selectList.do";
		}else if("admin".equals(user.getId())) {
			searchVO.setMngAt("Y");
		}
		
		searchVO.setUserId(user.getId());
		boardService.deleteBoard(searchVO);
		
		return "forward:/board/selectList.do";
	}
}