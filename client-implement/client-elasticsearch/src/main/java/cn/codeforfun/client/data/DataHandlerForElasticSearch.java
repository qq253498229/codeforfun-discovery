package cn.codeforfun.client.data;

import cn.hutool.core.date.DateUtil;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataHandlerForElasticSearch implements DataHandler {
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void registerService(ServiceInstance serviceInstance) {
        activeCurrentService(serviceInstance);
    }

    @Override
    public void activeService(ServiceInstance serviceInstance) {
        activeCurrentService(serviceInstance);
    }

    private void activeCurrentService(ServiceInstance serviceInstance) {
        ServiceInstanceForElasticsearch instance = new ServiceInstanceForElasticsearch();
        instance.setId(serviceInstance.getHost() + "-" + serviceInstance.getPort());
        instance.setName(serviceInstance.getName());
        instance.setHost(serviceInstance.getHost());
        instance.setPort(serviceInstance.getPort());
        instance.setRemark(serviceInstance.getRemark());
        instance.setLastActive(new Date());
        elasticsearchRestTemplate.save(instance);
    }

    @Override
    public List<ServiceInstance> findServiceInstanceList(Integer serviceActiveTimeout) {
        long time = DateUtil.offsetSecond(new Date(), -serviceActiveTimeout).getTime();
        Criteria criteria = new Criteria("lastActive").greaterThan(time);
        Query query = new CriteriaQuery(criteria);
        SearchHits<ServiceInstanceForElasticsearch> searchHits = elasticsearchRestTemplate.search(query, ServiceInstanceForElasticsearch.class);
        return searchHits.stream().map(s -> {
            ServiceInstanceForElasticsearch content = s.getContent();
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setName(content.getName());
            serviceInstance.setHost(content.getHost());
            serviceInstance.setPort(content.getPort());
            serviceInstance.setRemark(content.getRemark());
            serviceInstance.setLastActive(content.getLastActive());
            return serviceInstance;
        }).collect(Collectors.toList());
    }

    @Override
    public void deregisterService(ServiceInstance serviceInstance) {
        String id = serviceInstance.getHost() + "-" + serviceInstance.getPort();
        elasticsearchRestTemplate.delete(id, ServiceInstanceForElasticsearch.class);
    }
}
