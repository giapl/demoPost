package com.example.demopost.service.comment;

import com.example.demopost.data.request.CommentLeverTwoRequest;
import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.request.CommentTopicRequest;
import com.example.demopost.data.response.CommentLeverTwoResponse;
import com.example.demopost.data.response.CommentQuestionResponse;
import com.example.demopost.data.response.CommentTopicResponse;

/**
 * Created by DucDV on 27-10-2023
 * HN, VN.
 */
public interface ICommentService {
    CommentQuestionResponse createComment(CommentQuestionRequest commentQuestionRequest); //create one comment question

    CommentTopicResponse createCommentTopic(CommentTopicRequest commentTopicRequest); //create one comment postTopic

    CommentLeverTwoResponse createCommentLeverTwo(CommentLeverTwoRequest commentLeverTwoRequest); // create comment one commentQuestion lv2
}
