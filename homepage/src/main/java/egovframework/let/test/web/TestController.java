package egovframework.let.test.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.egovframe.rte.fdl.string.EgovStringUtil;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.let.test.service.TestService;
import egovframework.let.test.service.TestVO;

@Controller
public class TestController {
	@Resource(name = "testService")
	private TestService testService;
	
	@RequestMapping(value = "/test/selectList.do")
	public String selectList(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception{
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(testVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(testVO.getPageUnit());
		paginationInfo.setPageSize(testVO.getPageSize());
		
		testVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		testVO.setLastIndex(paginationInfo.getLastRecordIndex());
		testVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<EgovMap> resultList = testService.selectTestList(testVO);
		model.addAttribute("resultList", resultList);
		
		int totCnt = testService.selectTestListCnt(testVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		model.addAttribute("totCnt", totCnt);
		
		return "test/TestSelectList";
	}
	
	@RequestMapping(value = "/test/testRegist.do")
	public String testRegist(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception{
		TestVO result = new TestVO();
		if(!EgovStringUtil.isEmpty(testVO.getTestId())) {
			result = testService.selectTest(testVO);
		}
		model.addAttribute("result", result);
		
		return "test/TestRegist";
	}
	
	@RequestMapping(value = "/test/insert.do")
	public String insert(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception{
		testService.insertTest(testVO);
		return "forward:/test/selectList.do";
	}
	
	@RequestMapping(value= "test/update.do")
	public String update(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception{
		testService.updateTest(testVO);
		return "forward:/test/selectList.do";
	}
	
	@RequestMapping(value = "/test/select.do")
	public String select(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception{
		TestVO result = testService.selectTest(testVO);
		model.addAttribute("result", result);
		return "test/TestSelect";
	}
	
	@RequestMapping(value = "test/delete.do")
	public String delete(TestVO testVO, HttpServletRequest request, ModelMap model) throws Exception{
		testService.deleteTest(testVO);
		return "forward:/test/selectList.do";
	}
}
