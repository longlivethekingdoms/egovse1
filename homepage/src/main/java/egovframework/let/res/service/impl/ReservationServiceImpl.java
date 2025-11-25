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
import egovframework.let.res.service.ReservationService;
import egovframework.let.res.service.ReservationVO;
import egovframework.let.utl.sim.service.EgovFileScrty;

@Service("reservationService")
public class ReservationServiceImpl extends EgovAbstractServiceImpl implements ReservationService{

	@Resource(name="reservationDAO")
	private ReservationDAO reservationDAO;
	
	@Resource(name="egovRsvIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Override
	public List<EgovMap> selectReservationList(ReservationVO vo) throws Exception {

		return reservationDAO.selectReservationList(vo);
	}

	//예약 목록 가져오기
	@Override
	public int selectReservationListCnt(ReservationVO vo) throws Exception {
		
		return reservationDAO.selectReservationListCnt(vo);
	}

	//예약 상세
	@Override
	public ReservationVO selectReservation(ReservationVO vo) throws Exception {
		return reservationDAO.selectReservation(vo);
	}

	//예약 등록
	@Override
	public String insertReservation(ReservationVO vo) throws Exception {
		String id = idgenService.getNextStringId();
		vo.setResveId(id);
		reservationDAO.insertReservation(vo);
		
		return id;
	}

	//예약 수정
	@Override
	public void updateReservation(ReservationVO vo) throws Exception {
		reservationDAO.updateReservation(vo);
	}

	//예약 삭제
	@Override
	public void deleteReservation(ReservationVO vo) throws Exception {
		reservationDAO.deleteReservation(vo);
	}

}
