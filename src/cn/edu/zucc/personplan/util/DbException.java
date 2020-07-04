package cn.edu.zucc.personplan.util;

public class DbException extends BaseException {
	public DbException(Throwable ex){
		super("Êý¾Ý¿â²Ù×÷´íÎó£º"+ex.getMessage());
	}
}
