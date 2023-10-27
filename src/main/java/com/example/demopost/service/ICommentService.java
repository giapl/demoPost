package com.example.demopost.service;

import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.response.CommentQuestionResponse;

/**
 * Created by DucDV on 27-10-2023
 * HN, VN.
 */
public interface ICommentService {
    CommentQuestionResponse createComment(CommentQuestionRequest commentQuestionRequest);
}
