package com.flouis.demo.vo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ZTree {

	private Long id;
	private Long parentId;
	private String name;
	private Boolean open;
	private List<ZTree> children;

}
