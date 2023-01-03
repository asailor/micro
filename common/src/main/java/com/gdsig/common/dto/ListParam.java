package com.gdsig.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author huihu
 * @date 2022-06-28 17:59
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class ListParam {
    public static final int DEF_PAGE = 1;
    public static final int DEF_LIMIT = 10;
    public static final int MAX_LIMIT = 1000;

    /**
     * 当前页
     */
    private int page = DEF_PAGE;
    /**
     * 每页记录数
     */
    private int limit = DEF_LIMIT;

    public void setPage(int page) {
        // 限制前端最小查询页
        this.page = Math.max(page, DEF_PAGE);
    }

    public void setLimit(int limit) {
        if (limit < 0) {
            this.limit = MAX_LIMIT;
            return;
        }
        // 限制前端最大查询数
        this.limit = Math.min(limit, MAX_LIMIT);
    }

    public int getOffset() {
        return (this.page - 1) * this.limit;
    }
}
