package com.udbac.versionpublish;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.udbac.versionpublish.entity.Branch;
import com.udbac.versionpublish.entity.User;
import com.udbac.versionpublish.entity.Version;
import com.udbac.versionpublish.repository.BranchRepository;
import com.udbac.versionpublish.repository.UserRepository;
import com.udbac.versionpublish.repository.VersionRepository;
import com.udbac.versionpublish.util.MD5Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VersionPublishApplicationTests {

	@Resource
	VersionRepository vr;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BranchRepository branchRep;

//	@Test
	public void contextLoads() {
		Version v = new Version();
		v.setId(UUID.randomUUID().toString());
		v.setCreateTime("2019-04-23");
		v.setNotes("版本记录");
		v.setProvince("anhui");
		v.setUpdateTime("2019-04-25");
		v.setUser("wang");
//		v.setVersionDate("2019-04-23");
		v.setVersionNumber("1.2.3");
		vr.save(v);
		List<Version> vrs = vr.findAll();
		for (Version version : vrs) {
			System.out.println(version.getCreateTime());
		}
	}

//	@Test
	public void creatUser() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setUsername("dun");
		user.setPassword(MD5Util.getMD5("dun"));
		userRepository.save(user);
	}

//	@Test
	public void getUser() {
		List<User> user = userRepository.findByUsername("dun");
//		User u = userRepository.getByUsername("dun");
//		System.out.println("get:"+u.getPassword());
		System.out.println(user.get(0).getUsername());
	}
	@Test
	public void getBranch() {
	    Branch b = new Branch();
	    b.setDcsid("2222");
	    b.setName("渠道");
	    b.setProvince("安徽");
	    
	    List<Branch> bs = branchRep.findByNameAndDcsidAndProvince(b);
	    System.out.println(bs.size());
	}
	public static void main(String[] args) {
		
	}

}
