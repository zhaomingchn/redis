package com.zhaomingchen.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhaomingchen.entity.User;

import zhaomingchen.utlis.MathTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class RedisTest {
	
	@Autowired
	private RedisTemplate rt;
	
	//1.使用JDK系列化方式保存5万个user随机对象到Redis，并计算耗时（18分）
	@Test
	public void RedisSting() {
		//开始的时间
		long start = System.currentTimeMillis();
		for (int i = 0;i < 50000;i++) {
		
			User user=new User();
			//(1)ID使用1-5万的顺序号。（2分）
			user.setId(i);
		//	(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
			user.setName(MathTest.getName(3));
			
			//(3)性别在女和男两个值中随机。（4分）
			user.setGender(MathTest.getSex());
			//(4)手机以13开头+9位随机数模拟。（4分）
			user.setPhone(MathTest.getMultiPhoneNo());
			//(5)邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟。（4分）
			user.setEmali(MathTest.geEtMail());
			//(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
			user.setBirthday(MathTest.getBirthday());
			// 将数据添加到redis数据库中
			rt.opsForValue().set("user"+i,user);
		}
		// 结束的时间
		long end = System.currentTimeMillis();
		System.out.println("key系列化器StringRedisSerializer");
		System.out.println("(2)value系列化器为JdkSerializationRedisSerializer");
		System.out.println("保存的数量我为50k");
		System.out.println("耗费的时间为"+(end-start));
	}
	
	
	
	//2.使5.使用JSON系列化方式保存5万个user随机对象到Redis，并计算耗时（18分）
	@Test
	public void RedisSting1() {
		//开始的时间
		long start = System.currentTimeMillis();
		for (int i = 0;i < 50000;i++) {
		
			User user=new User();
			//(1)ID使用1-5万的顺序号。（2分）
			user.setId(i);
		//	(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
			user.setName(MathTest.getName(3));
			
			//(3)性别在女和男两个值中随机。（4分）
			user.setGender(MathTest.getSex());
			//(4)手机以13开头+9位随机数模拟。（4分）
			user.setPhone(MathTest.getMultiPhoneNo());
			//(5)邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟。（4分）
			user.setEmali(MathTest.geEtMail());
			//(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
			user.setBirthday(MathTest.getBirthday());
			// 将数据添加到redis数据库中
			rt.opsForValue().set("user"+i,user);
		}
		// 结束的时间
		long end = System.currentTimeMillis();
		System.out.println("key系列化器StringRedisSerializer");
		System.out.println("(2)value系列化器为Jackson2JsonRedisSerializer");
		System.out.println("保存的数量我为50k");
		System.out.println("耗费的时间为"+(end-start));
	}
	
	
	
	//3.使5.使用JSON系列化方式保存5万个user随机对象到Redis，并计算耗时（18分）
		@Test
		public void redisHash() {
			//开始的时间
			long start = System.currentTimeMillis();
			for (int i = 0;i < 50000;i++) {
			
				User user=new User();
				//(1)ID使用1-5万的顺序号。（2分）
				user.setId(i);
			//	(2)姓名使用3个随机汉字模拟，可以使用以前自己编写的工具方法。（4分）
				user.setName(MathTest.getName(3));
				
				//(3)性别在女和男两个值中随机。（4分）
				user.setGender(MathTest.getSex());
				//(4)手机以13开头+9位随机数模拟。（4分）
				user.setPhone(MathTest.getMultiPhoneNo());
				//(5)邮箱以3-20个随机字母 + @qq.com  | @163.com | @sian.com | @gmail.com | @sohu.com | @hotmail.com | @foxmail.com模拟。（4分）
				user.setEmali(MathTest.geEtMail());
				//(6)生日要模拟18-70岁之间，即日期从1949年到2001年之间。（4分）
				user.setBirthday(MathTest.getBirthday());
				// 将数据添加到redis数据库中
				rt.opsForHash().put("user"+i,"user"+i,user.toString());
			}
			// 结束的时间
			long end = System.currentTimeMillis();
			System.out.println("hashkey系列化器StringRedisSerializer");
			System.out.println("hashvalue系列化器为StringRedisSerializer");
			System.out.println("保存的数量我为50k");
			System.out.println("耗费的时间为"+(end-start));
		}
	

}
