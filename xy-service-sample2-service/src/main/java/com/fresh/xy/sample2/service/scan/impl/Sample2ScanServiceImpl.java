package com.fresh.xy.sample2.service.scan.impl;

import com.fresh.xy.mbp.utils.MybatisPlusPageUtils;
import com.fresh.xy.sample2.api.bo.Sample2ScanBo;
import com.fresh.xy.sample2.api.bo.Sample2ScanPageBo;
import com.fresh.xy.sample2.entity.scan.Sample2Scan;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fresh.xy.sample2.mapper.scan.Sample2ScanMapper;
import com.fresh.xy.sample2.service.scan.Sample2ScanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Sample2ScanServiceImpl extends ServiceImpl<Sample2ScanMapper, Sample2Scan> implements Sample2ScanService {

    @Autowired
    private Sample2ScanMapper sample2ScanMapper;

    @Override
    public IPage<Sample2ScanBo> listByPojo(Sample2ScanPageBo scanPageBo) {
        IPage<Sample2ScanBo> page = MybatisPlusPageUtils.mybatisPlusPage(scanPageBo);
        sample2ScanMapper.selectByPojo(page, scanPageBo);
        return page;
    }
}
