package com.shipeng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shipeng.bean.User;
import com.shipeng.utils.RandomUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:redis.xml")
public class TestRedis {
	@Autowired
	RedisTemplate redisTemplate;
	/**
	 * 
	    * @Title: testJDK
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param     参数
	    * @return void    返回类型
	    * @throws
	 */
	@Test
	public void testJDK() {
		//创建一个user对象
		User user = new User();
		ArrayList<User> list = new ArrayList<User>();
		//循环十万次
		for (int i = 1; i <=100000; i++) {
			//设置id
			user.setId(i);
			user.setName(RandomUtil.getRandomChineseString(3));
			//设置name
			int number = RandomUtil.getRandomNumber();
			//设置sex
			if(number%2==0) {
				user.setSex("女");
			}else {
				user.setSex("男");
			}
			//设置phone
			int[] number2 = RandomUtil.getRandomNumber(9);
			for (int j = 0; j < number2.length; j++) {
				user.setPhone("13"+number2[j]);
			}
			//设置email
			String[] email= new  String[] {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com ","@hotmail.com","@foxmail.com"};
			user.setEmail(RandomUtil.getRandomString(RandomUtil.getRandomNumber(3, 20))+email[RandomUtil.getRandomNumber(0, 6)]);
			//设置birthday
			user.setBirthday("1999-10-14");
			list.add(user);
		}
		//设置开始时间
		long start = System.currentTimeMillis();
		redisTemplate.opsForList().leftPushAll("users", list.toArray());
		//设置结束时间
		long end = System.currentTimeMillis();
		System.out.println("序列化方式"+"JDK");
		System.out.println("保存数量10w条");
		System.out.println("所耗时间"+(end-start));
	}
	/**
	 * 
	 * 
	    * @Title: testJSON
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param     参数
	    * @return void    返回类型
	    * @throws
	 */
	@Test
	public void testJSON() {
		User user = new User();
		ArrayList<User> list = new ArrayList<User>();
		for (int i = 1; i <=100000; i++) {
			user.setId(i);
			user.setName(RandomUtil.getRandomChineseString(3));
			int number = RandomUtil.getRandomNumber();
			if(number%2==0) {
				user.setSex("女");
			}else {
				user.setSex("男");
			}
			int[] number2 = RandomUtil.getRandomNumber(9);
			for (int j = 0; j < number2.length; j++) {
				user.setPhone("13"+number2[j]);
			}
			String[] email= new  String[] {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com ","@hotmail.com","@foxmail.com"};
			user.setEmail(RandomUtil.getRandomString(RandomUtil.getRandomNumber(3, 20))+email[RandomUtil.getRandomNumber(0, 6)]);
			user.setBirthday("1999-10-14");
			list.add(user);
		}
		long start = System.currentTimeMillis();
		redisTemplate.opsForList().leftPushAll("users", list.toArray());
		long end = System.currentTimeMillis();
		System.out.println("序列化方式"+"JSON");
		System.out.println("保存数量10w条");
		System.out.println("所耗时间"+(end-start));
	}
	/**
	 * 
	    * @Title: testHash
	    * @Description: TODO(这里用一句话描述这个方法的作用)
	    * @param     参数
	    * @return void    返回类型
	    * @throws
	 */
	@Test
	public void testHash() {
		User user = new User();
		HashMap map = new HashMap();
		for (int i = 1; i <=100000; i++) {
			user.setId(i);
			user.setName(RandomUtil.getRandomChineseString(3));
			int number = RandomUtil.getRandomNumber();
			if(number%2==0) {
				user.setSex("女");
			}else {
				user.setSex("男");
			}
			int[] number2 = RandomUtil.getRandomNumber(9);
			for (int j = 0; j < number2.length; j++) {
				user.setPhone("13"+number2[j]);
			}
			String[] email= new  String[] {"@qq.com","@163.com","@sian.com","@gmail.com","@sohu.com ","@hotmail.com","@foxmail.com"};
			user.setEmail(RandomUtil.getRandomString(RandomUtil.getRandomNumber(3, 20))+email[RandomUtil.getRandomNumber(0, 6)]);
			user.setBirthday("1999-10-14");
			map.put("1"+i, user);
		}
		long start = System.currentTimeMillis();
		redisTemplate.opsForHash().putAll("myhash", map);
		long end = System.currentTimeMillis();
		System.out.println("序列化方式"+"String");
		System.out.println("保存数量10w条");
		System.out.println("所耗时间"+(end-start));
	}
}
