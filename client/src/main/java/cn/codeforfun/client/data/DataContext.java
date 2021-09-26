package cn.codeforfun.client.data;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataContext implements DiscoveryClient {
    private Map<String, Set<cn.codeforfun.client.data.ServiceInstance>> instances = new HashMap<>();

    @Override
    public String description() {
        return null;
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        return null;
    }

    @Override
    public List<String> getServices() {
        return null;
    }

    public void refreshServiceInstances(List<cn.codeforfun.client.data.ServiceInstance> serviceInstanceList) {
        for (cn.codeforfun.client.data.ServiceInstance instance : serviceInstanceList) {
            String key = instance.getName();
            if (this.instances.containsKey(key)) {
                this.instances.get(key).add(instance);
            } else {
                this.instances.put(key, new HashSet<>(Collections.singletonList(instance)));
            }
        }
        System.out.println(1);
    }
}
