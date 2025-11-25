package egovframework.let.admin.rsv.web;

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
import egovframework.let.res.service.ReservationService;
import egovframework.let.res.service.ReservationVO;
import egovframework.let.temp.service.TempService;
import egovframework.let.temp.service.TempVO;
import egovframework.let.temp2.service.Temp2Service;
import egovframework.let.temp2.service.Temp2VO;
import net.sf.json.JSONObject;

@Controller
public class ReservationAdminController {
	
	@Resource(name = "reservationService")
	private ReservationService reservationService;
	
	//예약정보 목록 가져오기
	@RequestMapping(value = "/admin/rsv/rsvSelectList.do")
	public String rsvSelectList(@ModelAttribute("searchVO") ReservationVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/login/login.do";
		}else {
			model.addAttribute("USER_INFO", user);
		}
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<EgovMap>resultList = reservationService.selectReservationList(searchVO);
		model.addAttribute("resultList", resultList);
		
		int totCnt = reservationService.selectReservationListCnt(searchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "admin/rsv/RsvSelectList";
	}
	
	//예약정보 등록/수정
	@RequestMapping(value = "/admin/rsv/rsvRegist.do")
	public String rsvRegist(@ModelAttribute("searchVO") ReservationVO ReservationVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/login/login.do";
		}else {
			model.addAttribute("USER_INFO", user);
		}
		
		ReservationVO result = new ReservationVO();
		if(!EgovStringUtil.isEmpty(ReservationVO.getResveId())) {
			result = reservationService.selectReservation(ReservationVO);
		}
		model.addAttribute("result", result);
		
		request.getSession().removeAttribute("sessionReservation");
		
		return "admin/rsv/RsvRegist";	
	}
	
	//예약정보 등록하기
	@RequestMapping(value = "/admin/rsv/rsvInsert.do")
	public String insert(@ModelAttribute("searchVO") ReservationVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		//이중 서브밋 방지 체크
		if(request.getSession().getAttribute("sessionReservation")!=null) {
			return "forward:/admin/rsv/rsvSelectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용 가능합니다.");
			return "forward:/login/login.do";
		}
		
		searchVO.setUserId(user.getId());
		
		reservationService.insertReservation(searchVO);
		
		request.getSession().setAttribute("sessionReservation", searchVO);
		return "forward:/admin/rsv/rsvSelectList.do";
	}
	
	//예약정보 수정하기
	@RequestMapping(value="/admin/rsv/rsvUpdate.do")
	public String rsvUpdate(@ModelAttribute("searchVO") ReservationVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		//이중 서브밋 방지
		if(request.getSession().getAttribute("sessionReservation")!=null) {
			return "forward:/admin/rsv/rsvSelectList.do";
		}
		
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용 가능합니다.");
			return "forward:/login/login.do";
		}
		
		searchVO.setUserId(user.getId());
		
		reservationService.updateReservation(searchVO);
		
		//이중 서브밋 방지
		request.getSession().setAttribute("sessionReservation", searchVO);
		return "forward:/admin/rsv/rsvSelectList.do";
	}
	
	//예약정보 삭제하기
	@RequestMapping(value = "/admin/rsv/rsvDelete.do")
	public String rsvDelete(@ModelAttribute("searchVO") ReservationVO searchVO, HttpServletRequest request, ModelMap model) throws Exception{
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		if(user == null || EgovStringUtil.isEmpty(user.getId())) {
			model.addAttribute("message", "로그인 후 사용가능합니다.");
			return "forward:/login/login.do";
		}
		
		searchVO.setUserId(user.getId());
		
		reservationService.deleteReservation(searchVO);
		
		return "forward:/admin/rsv/rsvSelectList.do";
	}
}