package com.damaru.imagelabeller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ImageController {

	private static final Logger log = LoggerFactory.getLogger(ImageController.class);

	@PostMapping(path="/labels", consumes="text/plain", produces = "application/json")
	public List<ImageLabel> getLabels(@RequestBody String base64Image) throws Exception {
		return Vision.detectLabelsFromBase64(base64Image);
	}
}
