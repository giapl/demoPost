package com.example.demopost.service.impl;

import com.example.demopost.data.enity.CommentQuestion;
import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.response.CommentQuestionResponse;
import com.example.demopost.repository.ICommentQuestionRepository;
import com.example.demopost.repository.IQuestionRepository;
import com.example.demopost.service.ICommentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by DucDV on 27-10-2023
 * HN, VN.
 */
@Service
public class CommentServiceImpl implements ICommentService {
    private final IQuestionRepository questionRepository;
    private final ICommentQuestionRepository commentQuestionRepository;

    public CommentServiceImpl(IQuestionRepository questionRepository,
                              ICommentQuestionRepository commentQuestionRepository) {
        this.questionRepository = questionRepository;
        this.commentQuestionRepository = commentQuestionRepository;
    }

    @Override
    @Transactional
    public CommentQuestionResponse createComment(CommentQuestionRequest commentQuestionRequest) {
        // kiem tra xem question co ton tai hay k
        Optional<Question> questionById = questionRepository.findById(commentQuestionRequest.getQuestionId());
        if (!questionById.isPresent()){
            throw new RuntimeException("question not have in system");
        }
        // b1: tạo ra 1 mapper : map commentQuestionRequest to comment entity
        // b2: save  CommentQuestion commentSaved = commentQuestionRepository.save();

        return null;
    }

}
