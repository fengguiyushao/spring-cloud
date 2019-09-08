package springcloud.helloservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    public final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private Registration registration;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello")
    public String index() {
        List<ServiceInstance> instances = client.getInstances(registration.getServiceId());
        for (ServiceInstance instance : instances) {
            logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        }
        return "Hello World";
    }
}
