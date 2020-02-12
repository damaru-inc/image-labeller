package com.damaru.imagelabeller;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Vision {

	private static final Logger log = LoggerFactory.getLogger(Vision.class);

	public static List<ImageLabel> detectLabelsFromBase64(String base64) throws Exception, IOException {
		byte[] bytes = Base64.getDecoder().decode(base64);
		return detectLabels(bytes);
	}

	public static List<ImageLabel> detectLabels(byte[] bytes) throws Exception, IOException {
		ByteString imgBytes = ByteString.copyFrom(bytes);
		return detectLabels(imgBytes);
	}

	public static List<ImageLabel> detectLabelsFromPath(String filePath) throws Exception, IOException {
		ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));
		return detectLabels(imgBytes);
	}

	public static List<ImageLabel> detectLabels(ByteString imgBytes) throws Exception, IOException {
		List<ImageLabel> ret = new ArrayList<>();

		List<AnnotateImageRequest> requests = new ArrayList<>();

		Image img = Image.newBuilder().setContent(imgBytes).build();

		Feature feat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
		AnnotateImageRequest request =
				AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		requests.add(request);

		try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
			BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					ret.add(new ImageLabel(res.getError().getMessage(), 0.0f));
				} else {

					// For full list of available annotations, see http://g.co/cloud/vision/docs
					for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
						String desc = annotation.getDescription();
						float score = annotation.getScore();
						ret.add(new ImageLabel(desc, score));
						//annotation.getAllFields().forEach((k, v) -> sb.append(String.format("%s : %s\n", k, v.toString())));
					}
				}
			}
		}

		return ret;
	}

}
