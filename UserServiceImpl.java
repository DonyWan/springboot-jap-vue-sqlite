package com.udbac.versionpublish.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.udbac.versionpublish.entity.User;
import com.udbac.versionpublish.repository.UserRepository;
import com.udbac.versionpublish.service.UserService;
import com.udbac.versionpublish.util.JwtUtil;
import com.udbac.versionpublish.util.MD5Util;
import com.udbac.versionpublish.util.ResponseData;

/**
 * @author dundun.wang
 * @date 2019/05/27
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	UserRepository userRepository;
	@Override
	public ResponseData getUserByUsername(User user) {
		ResponseData response = new ResponseData();
		// TODO Auto-generated method stub
		User u = userRepository.findByUsername(user.getUsername());
		if( null != u) {
			// 获取加密字符串
			String result = MD5Util.getMD5(user.getPassword());
			// 匹配用户名和密码
			if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(result)) {
				// 用户名和密码正确,生成token
				String token = JwtUtil.createJWT(u.getUsername());
				response.setCode(true);
				response.setMessage("登录成功!");
				response.setObject(token);
			}else {
				// 用户名或密码错误
				response.setCode(false);
				response.setObject(user);
				response.setMessage("用户名或密码错误!");
			}
		}else {
			response.setCode(false);
			response.setMessage("用户不存在!");
			response.setObject(user);
		}
		
		return response;
	}

}
