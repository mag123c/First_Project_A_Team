package com.example.board.recommand;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommandForm {
 
    @NotEmpty(message="내용은 필수항목입니다.")
    private String content;
}