package egovframework.let.res.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.let.board.service.BoardVO;
import egovframework.let.join.service.JoinVO;
import egovframework.let.member.service.MemberVO;
import egovframework.let.res.service.ReservationApplyVO;
import egovframework.let.res.service.ReservationVO;
import egovframework.let.temp2.service.Temp2VO;

@Repository("reservationApplyDAO")
public class ReservationApplyDAO extends EgovAbstractMapper {
	
	//기존 신청여부
	int duplicateApplyCheck(ReservationApplyVO vo) throws Exception{
		return selectOne("reservationApplyDAO.duplicateApplyCheck", vo);
	}

	//예약자 등록하기
	public int insertReservationApply(ReservationApplyVO vo) throws Exception {
		return insert("reservationApplyDAO.insertReservationApply", vo);
	}

	//예약자 목록 가져오기
	public List<EgovMap> selectReservationApplyList(ReservationApplyVO vo)throws Exception {
		return selectList("reservationApplyDAO.selectReservationApplyList", vo);
	}

	//예약자 목록 수
	public int selectReservationApplyListCnt(ReservationApplyVO vo)throws Exception {
		return selectOne("reservationApplyDAO.selectReservationApplyListCnt", vo);
	}
	
	//예약자 상세정보
	public ReservationApplyVO selectReservationApply(ReservationApplyVO vo) throws Exception {
		return selectOne("reservationApplyDAO.selectReservationApply", vo);
	}

	//예약자 승인처리
	public void updateReservationConfirm(ReservationApplyVO vo) throws Exception {
		update("reservationApplyDAO.updateReservationConfirm", vo);
	}

	public void deleteReservationApply(ReservationApplyVO vo) throws Exception {
		delete("reservationApplyDAO.deleteReservationApply", vo);
	}

	public void updateReservationApply(ReservationApplyVO vo) throws Exception {
		update("reservationApplyDAO.updateReservationApply", vo);
	}
}
