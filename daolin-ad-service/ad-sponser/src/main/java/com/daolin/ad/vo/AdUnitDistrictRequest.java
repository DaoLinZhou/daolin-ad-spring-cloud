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
public class AdUnitDistrictRequest {

    private List<UnitDistrict> unitDistricts;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnitDistrict{
        private Long unitId;
        private String province;
        private String city;
    }
}
