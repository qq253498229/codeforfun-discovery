package cn.codeforfun.client.data;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataContext implements DiscoveryClient {
    private Map<String, Set<cn.codeforfun.client.data.ServiceInstance>> instances = new HashMap<>();

    @Override
    public String description() {
        return null;
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        Set<cn.codeforfun.client.data.ServiceInstance> serviceInstances = instances.get(serviceId);
        if (ObjectUtils.isEmpty(serviceInstances)) {
            return new ArrayList<>();
        }
        return serviceInstances.stream().map(s -> new ServiceInstance() {
            @Override
            public String getServiceId() {
                return s.getName();
            }

            @Override
            public String getHost() {
                return s.getHost();
            }

            @Override
            public int getPort() {
                return s.getPort();
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public URI getUri() {
                return null;
            }

            @Override
            public Map<String, String> getMetadata() {
                return null;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getServices() {
        return new ArrayList<>(this.instances.keySet());
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
    }
}
