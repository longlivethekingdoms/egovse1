package egovframework.let.res.web;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;
import egovframework.let.join.service.JoinService;
import egovframework.let.join.service.JoinVO;
import egovframework.let.member.service.MemberService;
import egovframework.let.member.service.MemberVO;
import egovframework.let.res.service.ReservationApplyService;
import egovframework.let.res.service.ReservationApplyVO;
import egovframework.let.res.service.ReservationService;
import egovframework.let.res.service.ReservationVO;
import egovframework.let.temp.service.TempService;
import egovframework.let.temp.service.TempVO;
import egovframework.let.temp2.service.Temp2Service;
import egovframework.let.temp2.service.Temp2VO;
import net.sf.json.JSONObject;

@Controller
public class ReservationApplyController {
	
	@Resource(name = "reservationService")
	private ReservationService reservationService;
	
	@Resource(name = "reservationApplyService")
	private ReservationApplyService reservationApplyService;
	
	//예약여부 체크
	@RequestMapping(value = "/rsv/rsvCheck.json")
	public void rsvCheck(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) 
			throws Exception {
		String successYn = "Y";
		String message = "성공";
		
		//net.sf.json.JSONObject
		JSONObject jo = new JSONObject();
		response.setContentType("text/javascript; charset=utf-8");
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			successYn = "N";
			message = "로그인 후 사용 가능합니다.";
		}
		searchVO.setUserId(user.getId());
		
		ReservationApplyVO result = reservationApplyService.rsvCheck(searchVO);
		if(!EgovStringUtil.isEmpty(result.getErrorCode())) {
			successYn = "N";
			message = result.getMessage();
		}
		
		jo.put("successYn", successYn);
		jo.put("message", message);
		
		PrintWriter printwriter = response.getWriter();
		printwriter.println(jo.toString());
		printwriter.flush();
		printwriter.close();
	}
	
	//예약자정보 등록/수정
	@RequestMapping(value="rsv/rsvApplyRegist.do")
	public String rsvApplyRegist(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}else {
			model.addAttribute("USER_INFO", user);
		}
	
		//프로그램 정보
		ReservationVO reservation = new ReservationVO();
		//org.egovframe.rte.fdl.string.EgovStringUtil
		if(!EgovStringUtil.isEmpty(searchVO.getResveId())) {
			reservation = reservationService.selectReservation(searchVO);
		}
		model.addAttribute("reservation", reservation);
		
		//예약정보
		ReservationApplyVO result = new ReservationApplyVO();
		if(!EgovStringUtil.isEmpty(searchVO.getReqstId())) {
			//result = reservationApplyService.selectReservationApply(searchVO);
		}
		
		model.addAttribute("result", result);
		
		request.getSession().removeAttribute("sessionReservationApply");
		
		return "rsv/RsvApplyRegist";
	}
	
	//예약자정보 등록하기
	@RequestMapping(value = "/rsv/rsvApplyInsert.do")
	public String rsvApplyInsert(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		//이중 서브밋 방지 체크
		if(request.getSession().getAttribute("sessionReservationApply")!=null) {
			return "forward:/rsv/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}
		
		searchVO.setUserId(user.getId());
		searchVO.setCreatIp(request.getRemoteAddr());
		
		ReservationApplyVO result = reservationApplyService.insertReservationApply(searchVO);
		if(!EgovStringUtil.isEmpty(result.getErrorCode())) {
			model.addAttribute("message", result.getMessage());
		}else {
			model.addAttribute("message", "신청완료됐습니다.");
		}
		
		//이중 서브밋 방지
		request.getSession().setAttribute("sessionReservationApply", searchVO);
		return "forward:/rsv/selectList.do";
	}
	
	//예약자 정보 목록 가져오기
	@RequestMapping(value="/rsv/selectApplyList.do")
	public String selectApplyList(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}else {
			searchVO.setUserId(user.getId());
			model.addAttribute("USER_INFO", user);
		}
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<EgovMap> resultList = reservationApplyService.selectReservationApplyList(searchVO);
		model.addAttribute("resultList", resultList);
		
		int totCnt = reservationApplyService.selectReservationApplyListCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "rsv/RsvApplySelectList";
	}
	
	//예약정보 삭제하기
	@RequestMapping(value="/rsv/rsvApplyDelete.do")
	public String rsvApplyDelete(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}
		
		searchVO.setUserId(user.getId());
		
		reservationApplyService.deleteReservationApply(searchVO);
		
		return "forward:/rsv/selectApplyList.do";
	}
	
	//예약자정보 상세
	@RequestMapping(value = "/rsv/rsvApplySelect.do")
	public String rsvApplySelect(@ModelAttribute("searchVO") ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}else {
			model.addAttribute("USER_INFO", user);
		}
		
		ReservationApplyVO result = reservationApplyService.selectReservationApply(searchVO);
		
		model.addAttribute("result", result);
		
		//이중 서브밋 방지
		request.getSession().removeAttribute("sessionReservationApply");
		
		return "rsv/RsvApplySelect";
	}
	
	//예약자정보 수정하기
	@RequestMapping(value = "/rsv/rsvApplyUpdate.do")
	public String rsvApplyUpdate(@ModelAttribute("searchVO")ReservationApplyVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		//이중 서브밋 방지
		if(request.getSession().getAttribute("sessionReservationApply")!=null) {
			return "forward:/rsv/selectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/rsv/selectList.do";
		}
		
		searchVO.setUserId(user.getId());
		
		reservationApplyService.updateReservationApply(searchVO);
		
		//이중 서브밋 방지
		request.getSession().setAttribute("sessionReservationApply", searchVO);
		
		return "forward:/rsv/selectApplyList.do";
	}
}