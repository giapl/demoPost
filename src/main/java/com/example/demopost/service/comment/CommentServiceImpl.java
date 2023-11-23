package com.example.demopost.service.comment;

import com.example.demopost.data.enity.CommentQuestion;
import com.example.demopost.data.enity.Question;
import com.example.demopost.data.request.CommentQuestionRequest;
import com.example.demopost.data.response.CommentQuestionResponse;
import com.example.demopost.mapper.IComment;
import com.example.demopost.repository.ICommentQuestionRepository;
import com.example.demopost.repository.IQuestionRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final IComment comment;

    @Autowired
    public CommentServiceImpl(IQuestionRepository questionRepository,
                              ICommentQuestionRepository commentQuestionRepository,
        IComment comment) {
        this.questionRepository = questionRepository;
        this.commentQuestionRepository = commentQuestionRepository;
        this.comment = comment;
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
       CommentQuestion commentQuestion = comment.convertEntityComment(commentQuestionRequest);
        commentQuestion.setQuestionId(questionById.get().getId());
        commentQuestion.setContent(commentQuestionRequest.getContent());
        commentQuestion.setImageUrl(commentQuestionRequest.getImageUrl());
        commentQuestion.setDateTime(LocalDateTime.now());
        commentQuestion.setUpdateTime(LocalDateTime.now());
        // b2: save  CommentQuestion commentSaved = commentQuestionRepository.save();
        CommentQuestion commentQuestion1 = commentQuestionRepository.save(commentQuestion);
       // create commentResponse
        CommentQuestionResponse response = new CommentQuestionResponse();
        response.setId(commentQuestion1.getId());
        response.setContent(commentQuestion1.getContent());
        response.setImageUrl(commentQuestion1.getImageUrl());
        response.setDateTime(LocalDateTime.now());
        return response;
    }

}
