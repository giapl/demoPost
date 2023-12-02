package com.example.demopost.service.comment;

import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.request.CommentTopicRequest;
import com.example.demopost.data.response.CommentQuestionResponse;
import com.example.demopost.data.response.CommentTopicResponse;

/**
 * Created by DucDV on 27-10-2023
 * HN, VN.
 */
public interface ICommentService {
    CommentQuestionResponse createComment(CommentQuestionRequest commentQuestionRequest);

    CommentTopicResponse createCommentTopic(CommentTopicRequest commentTopicRequest);
}
