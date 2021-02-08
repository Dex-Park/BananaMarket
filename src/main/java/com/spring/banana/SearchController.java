package com.spring.banana;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {

	/**
	 * ���������� - ���׼���
	 * @return
	 */
	@RequestMapping(value="/searchResult.do", method=RequestMethod.GET)
	public String searchResult() {
		return "search/searchResult";
	}
	
}
