package cn.codeforfun.client.data;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DataContextReactive implements ReactiveDiscoveryClient {
    private Map<String, List<cn.codeforfun.client.data.ServiceInstance>> instances = new HashMap<>();

    @Override
    public String description() {
        return null;
    }

    @Override
    public Flux<ServiceInstance> getInstances(String serviceId) {
        List<cn.codeforfun.client.data.ServiceInstance> serviceInstances = instances.get(serviceId);
        if (ObjectUtils.isEmpty(serviceInstances)) {
            return Flux.empty();
        }
        ServiceInstance[] serviceInstanceList = serviceInstances.stream().map(s -> new ServiceInstance() {
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
        }).toArray(ServiceInstance[]::new);
        return Flux.just(serviceInstanceList);
    }

    @Override
    public Flux<String> getServices() {
        return Flux.just(this.instances.keySet().toArray(new String[0]));
    }

    public void refreshServiceInstances(List<cn.codeforfun.client.data.ServiceInstance> serviceInstanceList) {
        this.instances = serviceInstanceList.stream().collect(Collectors.groupingBy(cn.codeforfun.client.data.ServiceInstance::getName));
    }
}
