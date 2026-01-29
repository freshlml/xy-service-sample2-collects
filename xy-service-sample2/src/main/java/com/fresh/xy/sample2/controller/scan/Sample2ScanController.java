package com.fresh.xy.sample2.controller.scan;

import com.fresh.common.enums.JsonResultEnum;
import com.fresh.common.result.JsonResult;
import com.fresh.common.result.PageJsonResultVo;
import com.fresh.common.utils.AssertUtils;
import com.fresh.xy.common.dto.PageDto;
import com.fresh.xy.mbp.utils.MybatisPlusPageUtils;
import com.fresh.xy.sample.api.SampleServiceApi;
import com.fresh.xy.sample.api.bo.SampleScanBo;
import com.fresh.xy.sample2.dto.Sample2ScanAddDto;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fresh.xy.sample2.entity.scan.Sample2Scan;
import com.fresh.xy.sample2.service.scan.Sample2ScanService;
import com.fresh.xy.sample2.vo.Sample2ScanVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/sample2Scan/")
public class Sample2ScanController {

    @Autowired
    private Sample2ScanService sample2ScanService;
    @Autowired
    private SampleServiceApi sampleServiceApi;

    @PostMapping("save")
    public JsonResult<?> save(@RequestBody @Valid Sample2ScanAddDto scanDto) {
        Sample2Scan scan = Sample2Scan.builder().build().setName(scanDto.getName()).setScanType(scanDto.getScanType()).setScanTime(scanDto.getScanTime());
        sample2ScanService.save(scan);
        return JsonResult.buildSuccessResult("保存成功");
    }

    @GetMapping("get")
    public JsonResult<Sample2ScanVo> getById(Long id) {
        //AssertUtils.ifNull(id, () -> "id不能为空", () -> JsonResultEnum.FAIL.getCode());
        Sample2Scan scan = sample2ScanService.getById(id);
        Sample2ScanVo result = null;
        if(scan != null) {
            result = Sample2ScanVo.builder().id(scan.getId()).name(scan.getName()).scanType(scan.getScanType()).scanTime(scan.getScanTime()).createTime(scan.getCreateTime()).modifyTime(scan.getModifyTime()).build();
        }
        return JsonResult.buildSuccessResult(result);
    }

    @GetMapping("list")
    public JsonResult<PageJsonResultVo<Sample2Scan>> list(PageDto pageDto) {
        AssertUtils.notNull(pageDto, () -> "分页参数不能为空", () -> JsonResultEnum.FAIL.getCode());
        AssertUtils.ifTrue(pageDto.getPageSize()<0, () -> "pageSize不能为负数", () -> JsonResultEnum.FAIL.getCode());

        Page<Sample2Scan> pageParam = new Page<>();
        pageParam.setCurrent(pageDto.getPage());
        pageParam.setSize(pageDto.getPageSize());
        IPage<Sample2Scan> result = sample2ScanService.page(pageParam);

        return JsonResult.buildSuccessResult(MybatisPlusPageUtils.pageJsonResultVo(result));
    }

    @GetMapping("listAll")
    public JsonResult<List<Sample2ScanVo>> listAll() {
        List<Sample2Scan> scanResult = sample2ScanService.list();
        List<Sample2ScanVo> result = scanResult.stream().map(scan -> Sample2ScanVo.builder().id(scan.getId()).name(scan.getName()).scanType(scan.getScanType()).scanTime(scan.getScanTime()).createTime(scan.getCreateTime()).modifyTime(scan.getModifyTime()).build()).collect(Collectors.toList());

        return JsonResult.buildSuccessResult(result);
    }


    //from sample
    @GetMapping("getByIdFromSample")
    public JsonResult<SampleScanBo> getByIdFromSample(@RequestParam(name = "id", required = false) Long id) {

        JsonResult<SampleScanBo> result = sampleServiceApi.getById(id);
        return result;
    }




}
