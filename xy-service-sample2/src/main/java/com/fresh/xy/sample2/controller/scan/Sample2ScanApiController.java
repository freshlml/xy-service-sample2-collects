package com.fresh.xy.sample2.controller.scan;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fresh.common.enums.JsonResultEnum;
import com.fresh.common.exception.BizException;
import com.fresh.common.result.JsonResult;
import com.fresh.common.result.PageJsonResultVo;
import com.fresh.common.utils.AssertUtils;
import com.fresh.xy.common.enums.ScanTypeEnum;
import com.fresh.xy.mbp.utils.MybatisPlusPageUtils;
import com.fresh.xy.sample2.api.bo.Sample2ScanAddBo;
import com.fresh.xy.sample2.api.bo.Sample2ScanBo;
import com.fresh.xy.sample2.api.bo.Sample2ScanPageBo;
import com.fresh.xy.sample2.entity.scan.Sample2Scan;
import com.fresh.xy.sample2.service.scan.Sample2ScanService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对外提供Rpc服务
 */
@Slf4j
@RestController
@RequestMapping("/sample2ScanApi/")
public class Sample2ScanApiController {

    @Autowired
    private Sample2ScanService sample2ScanService;

    @GetMapping("getById")
    public JsonResult<Sample2ScanBo> getById(Long id) {
        //AssertUtils.ifNull(id, () -> "id不能为空", () -> JsonResultEnum.FAIL.getCode());
        Sample2Scan scan = sample2ScanService.getById(id);
        Sample2ScanBo scanBo = null;
        if(scan != null) {
            scanBo = Sample2ScanBo.builder().id(scan.getId()).name(scan.getName()).scanType(scan.getScanType())
                        .scanTime(scan.getScanTime())
                        .createTime(scan.getCreateTime())
                        .modifyTime(scan.getModifyTime())
                        .build();
        }
        return JsonResult.buildSuccessResult(scanBo);
    }

    @GetMapping("getByIds")
    public JsonResult<List<Sample2ScanBo>> getByIds(@RequestParam(name = "ids", required = false) List<Long> ids) {
        List<Sample2ScanBo> result = Lists.newArrayList();
        if(ids != null && !ids.isEmpty()) {
            List<Sample2Scan> scanResult = sample2ScanService.list(new QueryWrapper<Sample2Scan>().lambda().select(Sample2Scan::getId, Sample2Scan::getName, Sample2Scan::getScanTime, Sample2Scan::getScanType).in(Sample2Scan::getId, ids));
            result = scanResult.stream().map(scan -> Sample2ScanBo.builder().id(scan.getId()).name(scan.getName()).scanType(scan.getScanType()).scanTime(scan.getScanTime()).build()).collect(Collectors.toList());
        }
        return JsonResult.buildSuccessResult(result);
    }

    //Get请求的Enum中@JsonCreator
    @GetMapping("listByPojo")
    public JsonResult<PageJsonResultVo<Sample2ScanBo>> listByPojo(Sample2ScanPageBo scanPageBo) {
        AssertUtils.notNull(scanPageBo, () -> "查询参数不能为空", () -> JsonResultEnum.FAIL.getCode());
        AssertUtils.ifTrue(scanPageBo.getPageSize()<0, () -> "pageSize不能为负数", () -> JsonResultEnum.FAIL.getCode());
        log.info("请求参数: {}", scanPageBo);

        IPage<Sample2ScanBo> result = sample2ScanService.listByPojo(scanPageBo);
        return JsonResult.buildSuccessResult(MybatisPlusPageUtils.pageJsonResultVo(result));
    }

    //compare with listByPojo, Get请求将参数放在请求体，使用@RequestBody解析请求体参数
    @GetMapping("listByPojo2")
    public JsonResult<PageJsonResultVo<Sample2ScanBo>> listByPojo2(@RequestBody Sample2ScanPageBo scanPageBo) {
        AssertUtils.notNull(scanPageBo, () -> "查询参数不能为空", () -> JsonResultEnum.FAIL.getCode());
        AssertUtils.ifTrue(scanPageBo.getPageSize()<0, () -> "pageSize不能为负数", () -> JsonResultEnum.FAIL.getCode());

        IPage<Sample2ScanBo> result = sample2ScanService.listByPojo(scanPageBo);
        return JsonResult.buildSuccessResult(MybatisPlusPageUtils.pageJsonResultVo(result));
    }

    @GetMapping("listByParams")
    public JsonResult<PageJsonResultVo<Sample2ScanBo>> listByParams(@RequestParam(name = "id", required = false) Long id,
                                   @RequestParam(name = "scanType", required = false) ScanTypeEnum scanType,
                                   @RequestParam(name = "scanTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime scanTime,
                                   @RequestParam(name = "page", required = false) Long page,
                                   @RequestParam(name = "pageSize", required = false) Long pageSize) {

        Sample2ScanPageBo scanPageBo = Sample2ScanPageBo.builder().id(id).scanTime(scanTime).scanType(scanType).build();
        scanPageBo.setPage(page);
        scanPageBo.setPageSize(pageSize);

        IPage<Sample2ScanBo> result = sample2ScanService.listByPojo(scanPageBo);
        return JsonResult.buildSuccessResult(MybatisPlusPageUtils.pageJsonResultVo(result));
    }

    @PostMapping("save")
    public JsonResult<?> save(@RequestBody @Valid Sample2ScanAddBo scanAddBo) {
        Sample2Scan scan = Sample2Scan.builder().name(scanAddBo.getName()).scanType(scanAddBo.getScanType()).scanTime(scanAddBo.getScanTime()).build();
        sample2ScanService.save(scan);
        return JsonResult.buildSuccessResult("保存成功");
    }

    @PostMapping("save2")
    public JsonResult<?> save2(@RequestBody @Valid Sample2ScanAddBo scanAddBo) {
        Sample2Scan scan = Sample2Scan.builder().name(scanAddBo.getName()).scanType(scanAddBo.getScanType()).scanTime(scanAddBo.getScanTime()).build();
        sample2ScanService.save(scan);
        throw new BizException(() -> "失败", () -> "异常code");
    }
}
