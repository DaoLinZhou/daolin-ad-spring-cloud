package com.daolin.ad.index.creativeunit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Daolin
 * @date 2019/05/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitObject {

    private Long adId;
    private Long unitId;

    // key : adId-unitId

}
