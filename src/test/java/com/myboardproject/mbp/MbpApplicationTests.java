package com.myboardproject.mbp;

import com.myboardproject.mbp.controller.dto.PostSaveRequestDto;
import com.myboardproject.mbp.service.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MbpApplicationTests {

	@Autowired private PostService postService;

	@Test
	public void 테스트_데이터_생성() {
		for (int i = 0; i < 300; i++) {
			String title = String.format("테스트 타이틀: %s", i);
			String content = String.format("테스트 본문: %s", i);
			PostSaveRequestDto requestDto = new PostSaveRequestDto(title, content, null);
			postService.savePost(requestDto, null);
		}
	}

}
