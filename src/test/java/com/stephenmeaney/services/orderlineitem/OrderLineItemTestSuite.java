package com.stephenmeaney.services.orderlineitem;

import com.stephenmeaney.services.orderlineitem.api.OrderLineItemControllerTest;
import com.stephenmeaney.services.orderlineitem.data.repository.OrderLineItemRepositoryIntegrationTest;
import com.stephenmeaney.services.orderlineitem.data.repository.OrderLineItemRepositoryTest;
import com.stephenmeaney.services.orderlineitem.service.OrderLineItemServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses ({OrderLineItemControllerTest.class, OrderLineItemRepositoryTest.class, OrderLineItemRepositoryIntegrationTest.class, OrderLineItemServiceTest.class})
public class OrderLineItemTestSuite {
}
