package opd.dao;

import hisglobal.vo.RoomChangeVO;
import hisglobal.vo.UserVO;

public interface RoomChangeDetailDAOi {

public void changePatientRoomDetails(RoomChangeVO _roomChangeVO,UserVO _userVO);
public void saveRoomChangeDetail(RoomChangeVO _roomChangeVO,UserVO _userVO);

}
