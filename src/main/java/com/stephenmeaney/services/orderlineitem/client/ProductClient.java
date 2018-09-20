package com.stephenmeaney.services.orderlineitem.client;

import com.stephenmeaney.services.orderlineitem.client.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient("PRODUCTSERVICES")
@RequestMapping("/api/v1/products")
public interface ProductClient {

    @GetMapping("/{id}")
    Product getById(@PathVariable(name = "id") long id);
}
