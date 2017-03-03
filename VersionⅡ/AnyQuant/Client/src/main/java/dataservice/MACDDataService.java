package dataservice;

import po.macdPO;

import java.util.List;

/**
 * Created by HP on 2016/3/29.
 */
public interface MACDDataService {
    public List<macdPO> getData(String startTime, String endTime, String codeName, String type);
    public void update();
}
