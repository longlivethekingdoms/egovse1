package egovframework.let.temp.web;

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

@Controller
public class TempController {
	
	@Resource(name = "tempService")
	private TempService tempService;
	
	@RequestMapping(value = "/temp/select.do")
	public String select(TempVO tempVO, HttpServletRequest request, ModelMap model) throws Exception{
		TempVO result = tempService.selectTemp(tempVO);
		model.addAttribute("temp", result);
		return "temp/TempSelect";
	}
	
	@RequestMapping(value = "/temp/selectList.do")
	public String selectList(TempVO tempVO, HttpServletRequest request, ModelMap model) throws Exception{
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(tempVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(tempVO.getPageUnit());
		paginationInfo.setPageSize(tempVO.getPageSize());
		
		tempVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		tempVO.setLastIndex(paginationInfo.getLastRecordIndex());
		tempVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<EgovMap> resultList = tempService.selectTempList(tempVO);
		model.addAttribute("resultList", resultList);
		
		int totCnt = tempService.selectTempListCnt(tempVO);
		model.addAttribute("totCnt", totCnt);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "temp/TempSelectList";
	}
	
	@RequestMapping(value = "/temp/tempRegist.do")
	public String tempRegist(TempVO tempVO, HttpServletRequest request, ModelMap model) throws Exception{
		TempVO result = new TempVO();
		//egovframework.let.util.fcc.service.egovstringutil
		if(!EgovStringUtil.isEmpty(tempVO.getTempId())) {
			result = tempService.selectTemp(tempVO);
		}
		model.addAttribute("result", result);
		
		return "temp/TempRegist";
	}
		
	@RequestMapping(value = "temp/insert.do")
	public String insert(TempVO tempVO, HttpServletRequest request) throws Exception{
			tempService.insertTemp(tempVO);
			
			return "forward:/temp/selectList.do";
	}
	
	@RequestMapping(value = "/temp/update.do")
	public String update(TempVO tempVO, HttpServletRequest request) throws Exception{
		tempService.updateTemp(tempVO);
		return "forward:/temp/selectList.do";
	}
	
	@RequestMapping(value = "temp/delete.do")
	public String delete(TempVO tempVO, HttpServletRequest request) throws Exception{
		tempService.deleteTemp(tempVO);
		return "forward:/temp/selectList.do";
	}
	
	//JSTL
	@RequestMapping(value = "/temp/jstl.do")
	public String jstl(TempVO tempVO, HttpServletRequest request, ModelMap model) throws Exception{
		return "/temp/Jstl";
	}
	
	//JSTL Import@
	@RequestMapping(value = "temp/jstlImport.do")
	public String jstlImport(TempVO tempVO, HttpServletRequest request, ModelMap model) throws Exception{
		return "/temp/jstlImport";
	}
}
