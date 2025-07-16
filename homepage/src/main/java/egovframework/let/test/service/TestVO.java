package egovframework.let.test.service;

import java.io.Serializable;
import java.util.Date;

import egovframework.com.cmm.ComDefaultVO;

public class TestVO extends ComDefaultVO implements Serializable {
	
		private String testCtgry;
	
		//임시데이터id
		private String testId;
		
		//임시데이터값
		private String testSj;
		
		private String testCn;
		
		private String testNm;
		
		private Date testFrstRegistPnttm;
		
		public String getTestCtgry() {
			return testCtgry;
		}

		public void setTestCtgry(String testCtgry) {
			this.testCtgry = testCtgry;
		}

		public String getTestCn() {
			return testCn;
		}

		public void setTestCn(String testCn) {
			this.testCn = testCn;
		}

		public String getTestNm() {
			return testNm;
		}

		public void setTestNm(String testNm) {
			this.testNm = testNm;
		}

		

		public Date getTestFrstRegistPnttm() {
			return testFrstRegistPnttm;
		}

		public void setTestFrstRegistPnttm(Date testFrstRegistPnttm) {
			this.testFrstRegistPnttm = testFrstRegistPnttm;
		}

		public String getTestId() {
			return testId;
		}

		public void setTestId(String testId) {
			this.testId = testId;
		}

		public String getTestSj() {
			return testSj;
		}

		public void setTestSj(String testSj) {
			this.testSj = testSj;
		}

}
