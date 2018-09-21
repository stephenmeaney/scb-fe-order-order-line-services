package com.stephenmeaney.services.order;

import com.stephenmeaney.services.order.api.OrderControllerTest;
import com.stephenmeaney.services.order.data.repository.OrderRepositoryIntegrationTest;
import com.stephenmeaney.services.order.data.repository.OrderRepositoryTest;
import com.stephenmeaney.services.order.service.OrderServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses ({OrderControllerTest.class, OrderRepositoryTest.class, OrderRepositoryIntegrationTest.class, OrderServiceTest.class})
public class OrderTestSuite {

}
