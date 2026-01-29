package com.fresh.xy.sample2.mapper.scan;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fresh.xy.sample2.api.bo.Sample2ScanBo;
import com.fresh.xy.sample2.api.bo.Sample2ScanPageBo;
import com.fresh.xy.sample2.entity.scan.Sample2Scan;
import org.apache.ibatis.annotations.Param;

public interface Sample2ScanMapper extends BaseMapper<Sample2Scan> {

    IPage<Sample2ScanBo> selectByPojo(IPage<Sample2ScanBo> page, @Param("scanPageBo") Sample2ScanPageBo scanPageBo);
}
