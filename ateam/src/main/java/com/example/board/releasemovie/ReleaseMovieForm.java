package com.example.board.releasemovie;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReleaseMovieForm {
    @NotEmpty(message="내용은 필수입니다")
    @Size(max=200)
    private String subject;
    
}


//<tr class="text-center">
//<th>번호</th>
//<th style="width:25%">영화제목</th>
//<th style="width:25%">제작</th>
//<th>감독</th>
//<th>상영예정일</th>
//</tr>