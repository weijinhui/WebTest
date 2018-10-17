package com.web.global;

public class EnumReturnCode {

	public enum ReturnCode{
		/*通过括号赋值,而且必须有带参构造器和一属性跟方法，否则编译出错
         * 赋值必须是都赋值或都不赋值，不能一部分赋值一部分不赋值
         * 如果不赋值则不能写构造器，赋值编译也出错*/
		
		Success(200),	//成功
		Info(300),	//信息
		Warn(400),	//警告
		Error(500), 	//错误
		Error_Args(510),  //错误：缺少指定参数
		Error_Format(520),  //错误：数据格式错误
		Error_Validate(530); //错误：验证错误，例如用户名密码出错等
		
		private final int value;
		
		public int getValue(){
			return value;
		}
		
		ReturnCode(int value){
			this.value=value;
		}
	}

}

