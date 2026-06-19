package io.pragra.novbatch.httpclientfeb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductOrder {

    private  Integer id;
    private  String productName;
    private Double orderValue;
    private  Integer quantity;
}


// pojo - plain old java object
// DTO - data transfer object
/*
HTTP clients
1. Rest template - deprecated - used to consume the restful web services,
 synchronous & blocking
 */