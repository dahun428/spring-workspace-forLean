package kr.co.jhta.view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class FileDownloadView extends AbstractView{

	
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//Model 객체에서 디렉토리 경로와 파일 명을 조회한다.
		String directory = (String) model.get("directory");
		String filename = (String) model.get("filename");
		String fileRealName = (String) model.get("fileRealName");
		
		//파일 객체를 생성한다.
		File file = new File(directory,filename);
		
		
		//응답 컨텐츠의 타입을 application/actect-stream 으로 설정한다.
		//application/octect-stream 은 알려지지 않은 파일의 타입을 지정할 때 이타입을 사용한다.
		// Content-Disposition 은 응답메시지의 헤더정보를 설정할 때 사용한다.
		// * 응답컨텐츠 타입, 응답 컨텐츠 길이, 응답컨텐츠와 관련된 부가정보 설정
		// Content-Disposition으로 다운로드 되는 파일의 이름을 브라우저에게 제공할 수 있다.
		// value 값 attachment는 항상  파일을 다운로드 하라는 것이다.
		// (브라우저에 표시하지 말고)
		// URLEncoder.encode(filename, "utf-8")) 는 파일이름에 한글이 포함되어 있을 경우
		// utf-8 방식으로 변환해서 파일명을 응답헤더 포함시킨다.
		setContentType("application/octet-stream");
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "attachment; filename=" +URLEncoder.encode(fileRealName, "utf-8"));
		//FileCopyUtils.copy(InputStream in , OutputStream out)
		// in 으로 읽어서 out으로 보낸다.
		// in 은 다운로드할 파일을 읽어오는 스트림이다.
		// out 은 브라우저로 컨텐츠를 출력하는 스트림이다.
		FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		
		
	}
	
	
}
