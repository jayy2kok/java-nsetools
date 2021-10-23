
package com.jayraj.nsetools.javansetools.models;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Stock {
    private String symbol;
    private String name;
    private String type;
    private String listingDate;
    private Integer paidUpValue;
    private Integer marketLot;
    private String isin;
    private Integer faceValue;
}
