package com.zmsk.smartbox.mapper;

import com.zmsk.smartbox.pojo.Cell;
import com.zmsk.smartbox.pojo.CellCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CellMapper {

    public List<CellCount> queryAvailableCount(String boxId);

    public Cell queryRandomAvailableCell(@Param("boxId")String boxId, @Param("typeId")String typeId);

    public String queryCellId(@Param("boxId")String boxId, @Param("cellNum")String cellNum);

    public int holdCell(@Param("cellId")String cellId);

    public int releaseCell(@Param("cellId")String cellId);
}
