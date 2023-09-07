package com.vicky.binder;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DashboardBinder {

	private String title;
	private String body;
	private Date createDate;
}
