package com.springbootModules.learning.CaffineCacheDemo.TopicFlow;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository){
        this.topicRepository = topicRepository;
    }

    public void addTopic(TopicRequest topic) {
        TopicEntity topicEntity=new TopicEntity(topic.getId(),topic.getName(),topic.getDescription());
        System.out.println("Saving into DB");
        topicRepository.save(topicEntity) ;
    }

    public void updateTopic(TopicRequest topic, Long id) {
        TopicEntity topicEntity=new TopicEntity(topic.getId(),topic.getName(),topic.getDescription());
        System.out.println("Updating into DB");
        topicRepository.save(topicEntity);
    }

    public void deleteTopic(Long id) {
        System.out.println("Deleting into DB");
        topicRepository.deleteById(id);
    }

    public TopicRequest getTopic(Long id)  {
        System.out.println("Fetching from DB");
        Optional<TopicEntity> topicRecord = topicRepository.findById(id);
        if(topicRecord.isPresent())
            return new TopicRequest(topicRecord.get().getId(),topicRecord.get().getName(),topicRecord.get().getDescription());
        throw new RuntimeException("Record Not Found");
    }
}
