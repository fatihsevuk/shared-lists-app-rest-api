package com.fatih.sharedlist.model;

import java.util.List;

public class CommentSummaryModel extends BaseModel{
	
	private List<CommentDetailModel> commentDetailList;

	public List<CommentDetailModel> getCommentDetailList() {
		return commentDetailList;
	}

	public void setCommentDetailList(List<CommentDetailModel> commentDetailList) {
		this.commentDetailList = commentDetailList;
	}
	
	
	
	
	
}
