package com.gdsig.common.dto;

import lombok.*;

import javax.swing.*;

/**
 * 通用查询模型
 *
 * @author Administrator
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonListParam extends ListParam {

    private Boolean statusBool;
    private Integer statusInt;

    private String number;
    private String name;
    private String title;

    private Integer type;

}
