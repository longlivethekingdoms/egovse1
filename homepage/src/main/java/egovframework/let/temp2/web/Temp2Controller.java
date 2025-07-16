package egovframework.let.temp2.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.string.EgovStringUtil;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.let.temp.service.TempService;
import egovframework.let.temp.service.TempVO;
import egovframework.let.temp2.service.Temp2Service;
import egovframework.let.temp2.service.Temp2VO;

@Controller
public class Temp2Controller {
	
	@Resource(name = "temp2Service")
	private Temp2Service temp2Service;
	
	@RequestMapping(value = "/temp2/selectList.do")
	public String selectList(Temp2VO temp2VO, HttpServletRequest request, ModelMap model) throws Exception{
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(temp2VO.getPageIndex());
		paginationInfo.setRecordCountPerPage(temp2VO.getPageUnit());
		paginationInfo.setPageSize(temp2VO.getPageSize());
		
		temp2VO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		temp2VO.setLastIndex(paginationInfo.getLastRecordIndex());
		temp2VO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<EgovMap> resultList = temp2Service.selectTempList(temp2VO);
		model.addAttribute("resultList", resultList);
		
		int totCnt = temp2Service.selectTempListCnt(temp2VO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("totCnt", totCnt);
		
		return "temp2/Temp2SelectList";
	}
	
	@RequestMapping(value = "/temp2/tempRegist.do")
	public String tempRegist(Temp2VO temp2VO, HttpServletRequest request, ModelMap model) throws Exception{
		Temp2VO result = new Temp2VO();
		if(!EgovStringUtil.isEmpty(temp2VO.getTempId())) {
			result = temp2Service.selectTemp(temp2VO);
		}
		model.addAttribute("result", result);
		
		return "temp2/Temp2Regist";
	}
	
	@RequestMapping(value = "/temp2/insert.do")
	public String insert(Temp2VO temp2VO, HttpServletRequest request, ModelMap model) throws Exception{
		temp2Service.insertTemp(temp2VO);
		return "forward:/temp2/selectList.do";
	}
	
	@RequestMapping(value= "temp2/update.do")
	public String update(Temp2VO temp2VO, HttpServletRequest request, ModelMap model) throws Exception{
		temp2Service.updateTemp(temp2VO);
		return "forward:/temp2/selectList.do";
	}
	
	@RequestMapping(value = "/temp2/select.do")
	public String select(Temp2VO temp2VO, HttpServletRequest request, ModelMap model) throws Exception{
		Temp2VO result = temp2Service.selectTemp(temp2VO);
		model.addAttribute("result", result);
		return "temp2/Temp2Select";
	}
	
	@RequestMapping(value = "temp2/delete.do")
	public String delete(Temp2VO temp2VO, HttpServletRequest request, ModelMap model) throws Exception{
		temp2Service.deleteTemp(temp2VO);
		return "forward:/temp2/selectList.do";
	}
}
