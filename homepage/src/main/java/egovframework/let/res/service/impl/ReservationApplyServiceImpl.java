package egovframework.let.res.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovStringUtil;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

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
import egovframework.let.utl.sim.service.EgovFileScrty;

@Service("reservationApplyService")
public class ReservationApplyServiceImpl extends EgovAbstractServiceImpl implements ReservationApplyService{

	@Resource(name="reservationApplyDAO")
	private ReservationApplyDAO reservationApplyDAO;
	
	@Resource(name="reservationService")
	private ReservationService reservationService;
	
	@Resource(name = "egovReqIdGnrService")
	private EgovIdGnrService idgenService;
	
	//예약가능여부 확인
	public ReservationApplyVO rsvCheck(ReservationApplyVO vo) throws Exception {
		//신청 인원 체크
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setResveId(vo.getResveId());
		ReservationVO result = reservationService.selectReservation(reservationVO);
		if(result.getMaxAplyCnt() <= result.getApplyFCnt()) {
			vo.setErrorCode("ERROR-R1");
			vo.setMessage("마감 됐습니다.");
		}else if(reservationApplyDAO.duplicateApplyCheck(vo) > 0) {
			vo.setErrorCode("ERROR-R2");
			vo.setMessage("이미 해당 프로그램 예약이 되어 있습니다.");
		}
		return vo;
	}

	//예약자 등록하기
	public ReservationApplyVO insertReservationApply(ReservationApplyVO vo) throws Exception {
		//신청 인원 체크
		ReservationVO reservationVO = new ReservationVO();
		reservationVO.setResveId(vo.getResveId());
		ReservationVO result = reservationService.selectReservation(reservationVO);
		if(result.getMaxAplyCnt() <= result.getApplyFCnt()) {
			vo.setErrorCode("ERROR-R1");
			vo.setMessage("마감 됐습니다");
		}else {
			//기존 신청여부
			if(reservationApplyDAO.duplicateApplyCheck(vo) > 0) {
				vo.setErrorCode("ERROR-R2");
				vo.setMessage("이미 해당 프로그램 예약이 되어 있습니다.");
			}else {
				String id = idgenService.getNextStringId();
				vo.setReqstId(id);
				
				//최대신청가능인원 체크
				vo.setMaxAplyCnt(result.getMaxAplyCnt());
				int cnt = reservationApplyDAO.insertReservationApply(vo);
				if(cnt == 0) {
					vo.setErrorCode("ERROR-R1");
					vo.setMessage("마감 됐습니다.");
				}
			}
		}
		
		return vo;
	}

	//예약자 목록 가져오기
	public List<EgovMap> selectReservationApplyList(ReservationApplyVO vo) throws Exception {
		return reservationApplyDAO.selectReservationApplyList(vo);
	}

	//예약자 목록 수
	public int selectReservationApplyListCnt(ReservationApplyVO vo) throws Exception {
		return reservationApplyDAO.selectReservationApplyListCnt(vo);
	}

	//예약자 상세정보
	@Override
	public ReservationApplyVO selectReservationApply(ReservationApplyVO vo) throws Exception {
		return reservationApplyDAO.selectReservationApply(vo);
	}

	//예약자 승인처리
	@Override
	public void updateReservationConfirm(ReservationApplyVO vo) throws Exception {
		reservationApplyDAO.updateReservationConfirm(vo);
	}

	//예약자 삭제하기
	@Override
	public void deleteReservationApply(ReservationApplyVO vo) throws Exception {
		reservationApplyDAO.deleteReservationApply(vo);
	}

	//예약자 수정하기
	@Override
	public void updateReservationApply(ReservationApplyVO vo) throws Exception {
		reservationApplyDAO.updateReservationApply(vo);
	}
	
	

}
