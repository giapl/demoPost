package com.example.demopost.mapper;

import com.example.demopost.data.enity.CommentTopic;
import com.example.demopost.data.request.CommentTopicRequest;
import com.example.demopost.data.response.CommentTopicResponse;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICommentTopic {

  List<CommentTopicResponse> convertEntityCommentTopic(List<CommentTopic> commentTopic);

  CommentTopic convertEntity(CommentTopicRequest commentTopicRequest);

 CommentTopicResponse convertTopicResponse (CommentTopic commentTopic);
}
