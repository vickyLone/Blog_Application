package com.vicky.binder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentBinder {

	private String commenterName;
	private String commenterEmail;
	private String commentbody;
    private Integer id;
}
