package com.daolin.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Daolin
 * @date 2019/04/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitKeywordRequest {

    private List<UnitKeyword> unitKeywords;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitKeyword{
        private Long unitId;
        private String keyword;
    }


}
