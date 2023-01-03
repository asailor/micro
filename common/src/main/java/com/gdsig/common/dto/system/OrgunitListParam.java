package com.gdsig.common.dto.system;

import com.gdsig.common.dto.ListParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author xs
 * @date 2022/12/1上午 10:21
 */

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OrgunitListParam extends ListParam {

    private Boolean status;

    private String name;

    private Integer parentId;
}
