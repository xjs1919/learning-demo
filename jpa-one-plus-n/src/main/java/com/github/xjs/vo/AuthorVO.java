package com.github.xjs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorVO {
    private Long id;
    private String name;
    private List<BookVO> books;

}
