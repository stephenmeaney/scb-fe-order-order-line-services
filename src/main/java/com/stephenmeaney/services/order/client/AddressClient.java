package com.stephenmeaney.services.order.client;

import com.stephenmeaney.services.order.client.domain.Address;
import com.stephenmeaney.services.order.client.domain.AddressSummary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient("accountaddressservices")
public interface AddressClient {

    @GetMapping("/api/v1/accounts/{accountId}/addresses/{addressId}")
    ResponseEntity<Address> getById(@PathVariable(name = "addressId") long addressId,
                                    @PathVariable(name = "accountId") long accountId);

    @GetMapping("/{accountId}/addresses")
    ResponseEntity<List<Address>> getAll(@PathVariable(name = "accountId") long accountId);


    @GetMapping("/api/v1/accounts/{accountId}/addresses/{addressId}/summary")
    ResponseEntity<AddressSummary> getSummary(@PathVariable(name = "addressId") long addressId, @PathVariable(name = "accountId") long accountId);
}
