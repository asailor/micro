package com.gdsig.common.dto.system;

import com.gdsig.common.dto.ListParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xs
 * @date 2022/12/8上午 9:24
 */

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FunctionListParam extends ListParam {

    private String name;

    private Integer menuId;
}
