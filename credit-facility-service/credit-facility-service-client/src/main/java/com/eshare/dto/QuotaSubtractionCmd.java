package com.eshare.dto;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 解冻额度
 * @Author Evan Leung
 **/
@Data
public class QuotaSubtractionCmd extends BaseQuotaAmountUpdateCmd {

}
