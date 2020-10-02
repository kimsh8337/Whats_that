package com.web.dictionary.controller;

import com.web.dictionary.dao.CommentDao;
import com.web.dictionary.dto.Comment;
import com.web.dictionary.model.BasicResponse;
import com.web.dictionary.service.ICommentService;
import com.web.dictionary.util.SHA256Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    ICommentService commentservice;

    @ApiOperation(value = "해당 게시물에 달린 댓글 불러오기")
    @GetMapping("/{postno}")
    public ResponseEntity<?> getComments(@PathVariable("postno") int postno) throws Exception {
        BasicResponse result = new BasicResponse();
        List<Comment> commentList = commentservice.getComments(postno);
          if(commentList.size() > 0 ){
                result.object = commentList;
                result.status = true;
              return new ResponseEntity(result, HttpStatus.OK);
          } else{
              return new ResponseEntity(result, HttpStatus.NO_CONTENT);
          }

    }
    @ApiOperation(value = "해당 게시물에 댓글 수정")
    @PutMapping("")
    public ResponseEntity<?> modifyComment(@RequestBody Comment comment) throws Exception {
    	BasicResponse result = new BasicResponse();
    	if(commentservice.modifyComment(comment) ){
    		result.status = true;
    		result.message ="success";
    		return new ResponseEntity(result, HttpStatus.OK);
    	} else{
    		result.status = false;
    		result.message ="fail";
    		return new ResponseEntity(result, HttpStatus.NO_CONTENT);
    	}
    	
    }
    
    @ApiOperation(value = "해당 댓글 지우기")
    @DeleteMapping("/{regno}")
    public ResponseEntity<?> deleteComment(@PathVariable("regno") int regno) throws Exception {
        BasicResponse result = new BasicResponse();
        int res = commentservice.deleteComment(regno);
        if( res == 1) { //성공
        	result.status = true;
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
        	result.status = false;
        	return new ResponseEntity(result, HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PostMapping("")
    public ResponseEntity<?> insertComment(Comment commnet) throws Exception {
        BasicResponse result = new BasicResponse();

        commentservice.insertComment(commnet);
        
        return new ResponseEntity(result, HttpStatus.OK);
    
    
    }
    


}
