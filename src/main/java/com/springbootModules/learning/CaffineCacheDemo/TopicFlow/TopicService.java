package com.springbootModules.learning.CaffineCacheDemo.TopicFlow;

import com.springbootModules.learning.CaffineCacheDemo.TopicFlow.cache.TopicCache;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private TopicCache cache;

    public TopicService(TopicRepository topicRepository, TopicCache cache){
        this.topicRepository = topicRepository;
        this.cache = cache;
    }

    public void addTopic(TopicRequest topic) {
        TopicEntity topicEntity=new TopicEntity(topic.getId(),topic.getName(),topic.getDescription());
        System.out.println("Saving into DB");
        topicRepository.save(topicEntity) ;
        System.out.println("Saving into Cache "+topicEntity.id);
        cache.addToCache(topicEntity);
    }

    public void updateTopic(TopicRequest topic, Long id) {
        TopicEntity topicEntity=new TopicEntity(topic.getId(),topic.getName(),topic.getDescription());
        System.out.println("Updating into DB");
        topicRepository.save(topicEntity);
        System.out.println("Updating into Cache "+topicEntity.id);
        cache.UpdateCache(topicEntity);
    }

    public void deleteTopic(Long id) {
        System.out.println("Deleting into DB");
        topicRepository.deleteById(id);
        System.out.println("Removing From Cache "+id);
        cache.delFromCache(id);
    }

    public TopicEntity getTopic(Long id) {
        TopicEntity fromCache = cache.getFromCache(id);
        if (null == fromCache) {
            System.out.println("Checking from Cache...\n, Data Not Present");
            System.out.println("Fetching from DB");
            Optional<TopicEntity> topicRecord = topicRepository.findById(id);
            if (topicRecord.isPresent()) {
                System.out.println("Adding To Cache");
                cache.addToCache(topicRecord.get());
                return topicRecord.get();
            }
            else
                throw new RuntimeException("Record Not Found");
        }
        System.out.println("Returrning from cache");
        return fromCache;
    }
}
