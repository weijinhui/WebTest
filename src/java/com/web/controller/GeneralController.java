package com.web.controller;

import java.io.IOException;
import java.util.Map;

import com.web.form.ResultSetForm;
import com.web.global.EnumReturnCode.ReturnCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GeneralController {

	@Autowired
	private UserController userController;

	@RequestMapping("/entrance")
	public ResponseEntity<ResultSetForm> entrance(
            @RequestBody Map<String, Object> params,
            HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		ResultSetForm result = new ResultSetForm();
		String service = (String) params.get("serviceName");

		if (service == null) {
			result.setResult(false, ReturnCode.Error,
					"请指定服务名称。例如：serviceName:userLogin");
			return new ResponseEntity<ResultSetForm>(result, HttpStatus.OK);
		}

		// Choose controller who is match the service name.

		if (service.equals("login")) {
			return this.userController.login(params);
		} else {
			// If not find service...
			result.setResult(false, ReturnCode.Error, "未找到匹配的服务名，请检查服务名填写是否正确。");
		}

		return new ResponseEntity<ResultSetForm>(result, HttpStatus.OK);
	}
	
}
